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

    private final ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(10,
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
                taskTrainMapper.updateByPrimaryKeySelective(TaskTrainDO.builder().id(taskTrainDO.getId())
                    .status(TaskTrainStatus.STOPPED.getName()).build());
                return;
            }

            Runnable taskJob = new TaskJob(taskTrainDO.getId(), taskTrainDO.getStartSaleTime().getTime(), this,
                ticketTestService, taskTrainMapper, taskMapper, ticketMapper, midTaskPassengerMapper, passengerMapper,
                ticketDetailMapper);
            Future<?> future = scheduledExecutorService.schedule(taskJob,
                taskTrainDO.getStartSaleTime().getTime() - System.currentTimeMillis(), TimeUnit.MILLISECONDS);

            int threadId = threadIdIncreaseNumber.getAndIncrement();
            idToFutureMap.put(String.valueOf(threadId), future);

            log.info(String.format("提交抢票子任务，子任务：%s，threadId：%s", JSON.toJSONString(taskTrainDO), threadId));

            taskTrainMapper.updateByPrimaryKeySelective(TaskTrainDO.builder().id(taskTrainDO.getId()).status(
                TaskTrainStatus.NOT_SALE.getName()).threadId(String.valueOf(threadId)).build());
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

            Future future = idToFutureMap.remove(taskTrainDO.getThreadId());
            if(future == null) {
                return;
            }

            future.cancel(true);

            log.info(String.format("取消抢票子任务，子任务：%s", JSON.toJSONString(taskTrainDO)));

            taskTrainMapper.updateByPrimaryKeySelective(TaskTrainDO.builder().id(taskTrainDO.getId())
                .status(TaskTrainStatus.STOPPED.getName()).build());
        });
    }

    @Override
    public void startAll() {
        List<TaskDO> taskDOList = taskMapper.select(TaskDO.builder().status(TaskStatus.STARTED.getName()).build());
        if(CollectionUtils.isEmpty(taskDOList)) {
            return;
        }

        taskDOList.forEach(taskDO -> start(taskDO.getId()));
    }

}
