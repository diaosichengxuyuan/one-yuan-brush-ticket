package com.diaosichengxuyuan.one.yuan.brush.ticket.service.core.executor;

import com.alibaba.fastjson.JSON;
import com.diaosichengxuyuan.one.yuan.brush.ticket.common.constant.TaskStatus;
import com.diaosichengxuyuan.one.yuan.brush.ticket.common.constant.TaskTrainStatus;
import com.diaosichengxuyuan.one.yuan.brush.ticket.dao.passenger.PassengerMapper;
import com.diaosichengxuyuan.one.yuan.brush.ticket.dao.task.MidTaskPassengerMapper;
import com.diaosichengxuyuan.one.yuan.brush.ticket.dao.task.TaskMapper;
import com.diaosichengxuyuan.one.yuan.brush.ticket.dao.task.TaskTrainMapper;
import com.diaosichengxuyuan.one.yuan.brush.ticket.dao.task.entity.TaskDO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.dao.task.entity.TaskTrainDO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.dao.test.TicketTestService;
import com.diaosichengxuyuan.one.yuan.brush.ticket.dao.ticket.TicketDetailMapper;
import com.diaosichengxuyuan.one.yuan.brush.ticket.dao.ticket.TicketMapper;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.core.thread.TaskJob;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.core.thread.TaskThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author liuhaipeng
 * @date 2019/7/11
 */
@Component("taskExecutor")
@Slf4j
public class DefaultTaskExecutor implements TaskExecutor {

    @Autowired
    private TicketTestService ticketTestService;

    @Autowired
    private TaskTrainMapper taskTrainMapper;

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private TicketMapper ticketMapper;

    @Autowired
    private MidTaskPassengerMapper midTaskPassengerMapper;

    @Autowired
    private PassengerMapper passengerMapper;

    @Autowired
    private TicketDetailMapper ticketDetailMapper;

    private final ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(100,
        TaskThreadFactory.getInstance());

    private final AtomicInteger threadIdIncreaseNumber = new AtomicInteger(1);

    private final ConcurrentHashMap<String, Future> idToFutureMap = new ConcurrentHashMap<>();

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void start(Long taskId) {
        List<TaskTrainDO> taskTrainDOList = taskTrainMapper.select(TaskTrainDO.builder().taskId(taskId).build());
        if(CollectionUtils.isEmpty(taskTrainDOList)) {
            return;
        }

        Date now = new Date();

        taskTrainDOList.forEach(taskTrainDO -> {
            if(now.after(taskTrainDO.getEndSaleTime())) {
                taskTrainMapper.updateByPrimaryKeySelective(TaskTrainDO.builder().id(taskTrainDO.getId()).modifyTime(
                    new Date()).status(TaskTrainStatus.STOPPED.getName()).build());
                return;
            }

            Runnable taskJob = new TaskJob(taskTrainDO.getId(), taskTrainDO.getStartSaleTime().getTime(), this,
                ticketTestService, taskTrainMapper, taskMapper, ticketMapper, midTaskPassengerMapper, passengerMapper,
                ticketDetailMapper);

            long diffTime = taskTrainDO.getStartSaleTime().getTime() - System.currentTimeMillis();
            Future<?> future = scheduledExecutorService.schedule(taskJob, diffTime, TimeUnit.MILLISECONDS);

            int threadId = threadIdIncreaseNumber.getAndIncrement();
            idToFutureMap.put(String.valueOf(threadId), future);

            log.info(String.format("提交抢票子任务，子任务：%s，threadId：%s", JSON.toJSONString(taskTrainDO), threadId));

            taskTrainMapper.updateByPrimaryKeySelective(TaskTrainDO.builder().id(taskTrainDO.getId()).modifyTime(
                new Date()).threadId(String.valueOf(threadId)).build());

            //对于需要延迟等待超过5秒的子任务，将状态改为未开售，对于小于5秒的就不管了，因为线程开始执行时立即设置状态为抢票中
            if(diffTime > 5000) {
                taskTrainMapper.updateByPrimaryKeySelective(TaskTrainDO.builder().id(taskTrainDO.getId()).modifyTime(
                    new Date()).status(TaskTrainStatus.NOT_SALE.getName()).build());
            }
        });
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void stop(Long taskId) {
        List<TaskTrainDO> taskTrainDOList = taskTrainMapper.select(TaskTrainDO.builder().taskId(taskId).build());
        if(CollectionUtils.isEmpty(taskTrainDOList)) {
            return;
        }

        taskTrainDOList.forEach(taskTrainDO -> {
            if(StringUtils.isEmpty(taskTrainDO.getThreadId())) {
                return;
            }

            Future future = idToFutureMap.get(taskTrainDO.getThreadId());
            if(future == null) {
                return;
            }

            try {
                future.cancel(true);
            } catch(Exception e) {
                log.error(String.format("取消抢票子任务失败，等待超期取消，子任务：%s", JSON.toJSONString(taskTrainDO)));
                return;
            }

            idToFutureMap.remove(taskTrainDO.getThreadId());
            log.info(String.format("取消抢票子任务，子任务：%s", JSON.toJSONString(taskTrainDO)));

            taskTrainMapper.updateStatusByPrimaryKey(taskTrainDO.getId(), TaskTrainStatus.STOPPED.getName());
        });
    }

    @Override
    public void startAll() {
        //暂未考虑分页
        List<TaskDO> taskDOList = taskMapper.select(TaskDO.builder().status(TaskStatus.STARTED.getName()).build());
        if(CollectionUtils.isEmpty(taskDOList)) {
            return;
        }

        taskDOList.forEach(taskDO -> start(taskDO.getId()));
    }

    @Override
    public void stopSubAtFixRate() {
        Runnable runnable = () -> {
            while(true) {
                try {
                    //每隔150s扫描一次，初次延时150等待应用初始化完成
                    TimeUnit.SECONDS.sleep(150);

                    //开始停止所有超期子任务，暂未考虑分页
                    List<TaskTrainDO> taskTrainDOList = taskTrainMapper.selectAllByEndSaleTime();
                    if(CollectionUtils.isNotEmpty(taskTrainDOList)) {
                        taskTrainDOList.forEach(taskTrainDO -> {
                            if(StringUtils.isEmpty(taskTrainDO.getThreadId())) {
                                return;
                            }

                            Future future = idToFutureMap.get(taskTrainDO.getThreadId());
                            if(future == null) {
                                return;
                            }

                            try {
                                future.cancel(true);
                            } catch(Exception e) {
                                log.error(String.format("取消超期抢票子任务失败，等待下次取消，子任务：%s", JSON.toJSONString(taskTrainDO)));
                                return;
                            }

                            idToFutureMap.remove(taskTrainDO.getThreadId());
                            log.info(String.format("取消超期抢票子任务，子任务：%s", JSON.toJSONString(taskTrainDO)));

                            taskTrainMapper.updateStatusByPrimaryKey(taskTrainDO.getId(),
                                TaskTrainStatus.STOPPED.getName());
                        });
                    }

                    //如果一个主任务下的子任务都已经超期并是停止状态，则修改主任务状态为已结束，暂未考虑分页
                    List<TaskDO> taskDOList = taskMapper.selectAllByStatus(TaskStatus.FINISHED.getName());
                    if(CollectionUtils.isEmpty(taskDOList)) {
                        continue;
                    }

                    taskDOList.forEach(taskDO -> {
                        List<TaskTrainDO> trainDOList = taskTrainMapper.select(
                            TaskTrainDO.builder().taskId(taskDO.getId()).build());
                        //如果没有子任务(理论上不会出现)
                        if(CollectionUtils.isEmpty(trainDOList)) {
                            //将主任务状态改为已结束
                            taskMapper.updateByPrimaryKeySelective(TaskDO.builder().id(taskDO.getId()).modifyTime(
                                new Date()).status(TaskStatus.FINISHED.getName()).build());
                            return;
                        }

                        //过滤掉超期任务
                        List<TaskTrainDO> trainList = trainDOList.stream().filter(
                            taskTrainDO -> !(TaskTrainStatus.STOPPED.getName().equals(taskTrainDO.getStatus())
                                && taskTrainDO.getEndSaleTime().before(new Date()))).collect(Collectors.toList());

                        //如果过滤掉的都是超期任务
                        if(CollectionUtils.isEmpty(trainList)) {
                            //将主任务状态改为已结束
                            taskMapper.updateByPrimaryKeySelective(TaskDO.builder().id(taskDO.getId()).modifyTime(
                                new Date()).status(TaskStatus.FINISHED.getName()).build());
                            //对于某些子任务上thread_id为清理的，再次清理一下
                            trainDOList.forEach(taskTrainDO -> taskTrainMapper
                                .updateStatusByPrimaryKey(taskTrainDO.getId(), TaskTrainStatus.STOPPED.getName()));
                        }
                    });
                } catch(Throwable e) {
                    log.error("线程执行中出现异常", e);
                }
            }
        };

        new Thread(Thread.currentThread().getThreadGroup(), runnable, "one-yuan-brush-ticket-task-stop-thread").start();
    }

}
