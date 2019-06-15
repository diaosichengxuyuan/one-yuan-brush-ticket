package com.diaosichengxuyuan.one.yuan.brush.ticket.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackages = "com.diaosichengxuyuan.one.yuan.brush.ticket")
@MapperScan(basePackages = "com.diaosichengxuyuan.one.yuan.brush.ticket.dao")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

