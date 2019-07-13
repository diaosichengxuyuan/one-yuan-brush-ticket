package com.diaosichengxuyuan.one.yuan.brush.ticket.web;

import com.diaosichengxuyuan.one.yuan.brush.ticket.service.core.executor.TaskExecutor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackages = "com.diaosichengxuyuan.one.yuan.brush.ticket")
@MapperScan(basePackages = "com.diaosichengxuyuan.one.yuan.brush.ticket.dao")
public class Application {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(Application.class, args);

        //由于服务器停止可能导致运行中的抢票线程停止，所以服务器重启时启动抢票线程
        TaskExecutor taskExecutor = (TaskExecutor)applicationContext.getBean("taskExecutor");
        taskExecutor.startAll();
    }

}

