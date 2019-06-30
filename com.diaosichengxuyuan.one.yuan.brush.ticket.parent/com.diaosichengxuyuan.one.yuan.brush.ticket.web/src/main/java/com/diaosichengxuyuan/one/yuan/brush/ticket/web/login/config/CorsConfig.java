package com.diaosichengxuyuan.one.yuan.brush.ticket.web.login.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletResponse;

/**
 * cors配置
 *
 * @author liuhaipeng
 * @date 2019/6/26
 */
@Configuration
public class CorsConfig {

    @Value("${access.control.allow.origin}")
    private String accessControlAllowOrigin;

    /**
     * 设置header中允许跨域的参数
     *
     * @param response
     */
    public void setAccessControlAllowHeader(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", accessControlAllowOrigin);
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers",
            "Origin, X-Requested-With, Content-Type, Accept, Connection, User-Agent, Cookie");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Content-Type", "application/json;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache, must-revalidate");
    }

}
