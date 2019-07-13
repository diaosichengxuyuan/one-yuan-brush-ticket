package com.diaosichengxuyuan.one.yuan.brush.ticket.service.core.thread;

import com.diaosichengxuyuan.one.yuan.brush.ticket.common.constant.TaskStatus;
import com.diaosichengxuyuan.one.yuan.brush.ticket.common.constant.TaskTrainStatus;
import com.diaosichengxuyuan.one.yuan.brush.ticket.common.constant.TicketStatus;
import com.diaosichengxuyuan.one.yuan.brush.ticket.common.util.DateUtil;
import com.diaosichengxuyuan.one.yuan.brush.ticket.dao.passenger.PassengerMapper;
import com.diaosichengxuyuan.one.yuan.brush.ticket.dao.passenger.entity.PassengerDO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.dao.task.MidTaskPassengerMapper;
import com.diaosichengxuyuan.one.yuan.brush.ticket.dao.task.TaskMapper;
import com.diaosichengxuyuan.one.yuan.brush.ticket.dao.task.TaskTrainMapper;
import com.diaosichengxuyuan.one.yuan.brush.ticket.dao.task.entity.MidTaskPassengerDO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.dao.task.entity.TaskDO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.dao.task.entity.TaskTrainDO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.dao.test.TicketTestReqDTO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.dao.test.TicketTestService;
import com.diaosichengxuyuan.one.yuan.brush.ticket.dao.ticket.TicketDetailMapper;
import com.diaosichengxuyuan.one.yuan.brush.ticket.dao.ticket.TicketMapper;
import com.diaosichengxuyuan.one.yuan.brush.ticket.dao.ticket.entity.TicketDO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.dao.ticket.entity.TicketDetailDO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.core.executor.TaskExecutor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 抢票任务执行逻辑
 *
 * @author liuhaipeng
 * @date 2019/7/11
 */
@Slf4j
public class TaskJob implements Runnable, Delayed {

    private final Long taskTrainId;

    private final long delay;

    private final TaskExecutor taskExecutor;

    private final TicketTestService ticketTestService;

    private final TaskTrainMapper taskTrainMapper;

    private final TaskMapper taskMapper;

    private final TicketMapper ticketMapper;

    private final MidTaskPassengerMapper midTaskPassengerMapper;

    private final PassengerMapper passengerMapper;

    private final TicketDetailMapper ticketDetailMapper;

    public TaskJob(Long taskTrainId, long delay, TaskExecutor taskExecutor, TicketTestService ticketTestService,
                   TaskTrainMapper taskTrainMapper, TaskMapper taskMapper, TicketMapper ticketMapper,
                   MidTaskPassengerMapper midTaskPassengerMapper, PassengerMapper passengerMapper,
                   TicketDetailMapper ticketDetailMapper) {
        this.taskTrainId = taskTrainId;
        this.delay = delay;
        this.taskExecutor = taskExecutor;
        this.ticketTestService = ticketTestService;
        this.taskTrainMapper = taskTrainMapper;
        this.taskMapper = taskMapper;
        this.ticketMapper = ticketMapper;
        this.midTaskPassengerMapper = midTaskPassengerMapper;
        this.passengerMapper = passengerMapper;
        this.ticketDetailMapper = ticketDetailMapper;
    }

    @Override
    public void run() {
        try {
            //设置状态为抢票中
            taskTrainMapper.updateByPrimaryKeySelective(TaskTrainDO.builder().id(taskTrainId)
                .status(TaskTrainStatus.ON_SALE.getName()).build());

            TaskTrainDO taskTrainDO = taskTrainMapper.selectByPrimaryKey(taskTrainId);
            if(taskTrainDO == null) {
                log.error(String.format("抢票出现异常，抢票任务状态已更新，但是子任务丢失了，taskTrainId：%s", taskTrainId));
                return;
            }

            TaskDO taskDO = taskMapper.selectByPrimaryKey(taskTrainDO.getTaskId());
            if(taskDO == null) {
                log.error(String.format("抢票出现异常，抢票任务状态已更新，但是主任务丢失了，taskTrainId：%s", taskTrainId));
                return;
            }

            //开始抢票
            brushTicket(taskDO, taskTrainDO);
        } catch(Throwable e) {
            log.error("线程执行中出现异常", e);
        }
    }

    private void brushTicket(TaskDO taskDO, TaskTrainDO taskTrainDO) {
        //被通知中断，结束
        if(Thread.currentThread().isInterrupted()) {
            log.info(String.format("线程中断结束，线程：%s", Thread.currentThread().getName()));
            return;
        }

        boolean brushTicketResult;
        try {
            brushTicketResult = ticketTestService.brushTicket(TicketTestReqDTO.builder().date(taskDO.getStartDate())
                .train(taskTrainDO.getTrain()).build());
        } catch(Exception e1) {
            log.error(String.format("抢票过程中出现异常，taskTrainId：%s", taskTrainId), e1);

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch(InterruptedException e) {
                //如果抛出中断异常，中断标志位已经被重置为false，所以要重新调用interrupt()方法使标志位变为true
                Thread.currentThread().interrupt();
                return;
            } catch(Exception e2) {
                log.error(String.format("抢票等待中出现异常，taskTrainId：%s", taskTrainId), e2);
            }

            //未知异常，继续抢
            brushTicket(taskDO, taskTrainDO);
            return;
        }

        //抢票成功
        if(brushTicketResult) {
            //保存数据
            saveDataWhenSuccessful(taskDO, taskTrainDO);
        }
        //没有余票
        else {
            //等待直到有票
            waitUntilHasTicket(taskDO.getStartDate(), taskTrainDO.getTrain());
            //重新去抢
            brushTicket(taskDO, taskTrainDO);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    void saveDataWhenSuccessful(TaskDO taskDO, TaskTrainDO taskTrainDO) {
        //插入抢票数据到ticket表
        TicketDO ticketDO = TicketDO.builder().createTime(new Date()).modifyTime(new Date()).accountId(
            taskDO.getAccountId()).date(taskDO.getStartDate()).week((byte)1).train(taskTrainDO.getTrain()).startPlace(
            taskDO.getStartPlace()).endPlace(taskDO.getEndPlace()).startTime(new Date()).endTime(new Date()).endPayTime(
            DateUtil.addDays(new Date(), 0, 0, 30)).status(TicketStatus.NOT_PAY.getName()).taskTrainId(
            taskTrainDO.getId()).build();
        ticketMapper.insert(ticketDO);

        List<MidTaskPassengerDO> midTaskPassengerDOList = midTaskPassengerMapper.select(
            MidTaskPassengerDO.builder().taskId(taskDO.getId()).build());
        if(CollectionUtils.isNotEmpty(midTaskPassengerDOList)) {
            midTaskPassengerDOList.forEach(midTaskPassengerDO -> {
                PassengerDO passengerDO = passengerMapper.selectByPrimaryKey(midTaskPassengerDO.getPassengerId());
                if(passengerDO == null) {
                    log.error(String.format("插入抢票结果时异常，passengerId：%s", midTaskPassengerDO.getPassengerId()));
                    return;
                }

                //插入抢票数据到ticket_detail表
                ticketDetailMapper.insertSelective(TicketDetailDO.builder().createTime(new Date()).modifyTime(
                    new Date()).ticketId(ticketDO.getId()).name(passengerDO.getName()).attribute(
                    passengerDO.getAttribute()).cardType(passengerDO.getCardType()).cardNumber(
                    passengerDO.getCardNumber()).seat(taskDO.getSeat()).carriage("04").seatNumber("11C").price("553.0元")
                    .build());
            });
        }

        //停止主任务下的所有子任务
        taskExecutor.stop(taskDO.getId());

        //将主任务更新为停止
        taskMapper.updateByPrimaryKeySelective(TaskDO.builder().id(taskDO.getId()).status(TaskStatus.FINISHED.getName())
            .build());
    }

    private void waitUntilHasTicket(Date date, String train) {
        //设置状态为余票监控中
        taskTrainMapper.updateByPrimaryKeySelective(TaskTrainDO.builder().id(taskTrainId)
            .status(TaskTrainStatus.ON_MONITOR.getName()).build());

        //不停的查询余票
        int remainingCount = ticketTestService.queryRemainingCount(TicketTestReqDTO.builder().date(date).train(train)
            .build());
        while(remainingCount == 0) {
            if(Thread.currentThread().isInterrupted()) {
                return;
            }

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch(InterruptedException e) {
                //如果抛出中断异常，中断标志位已经被重置为false，所以要重新调用interrupt()方法使标志位变为true
                Thread.currentThread().interrupt();
                return;
            } catch(Exception e) {
                log.error(String.format("抢票等待中出现异常，taskTrainId：%s", taskTrainId));
            }

            remainingCount = ticketTestService.queryRemainingCount(TicketTestReqDTO.builder().date(date).train(train)
                .build());
        }

        //设置状态为抢票中
        taskTrainMapper.updateByPrimaryKeySelective(TaskTrainDO.builder().id(taskTrainId)
            .status(TaskTrainStatus.ON_SALE.getName()).build());
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(delay - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return delay > o.getDelay(null) ? 1 : delay < o.getDelay(null) ? -1 : 0;
    }

}
