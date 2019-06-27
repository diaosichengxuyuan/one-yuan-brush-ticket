package com.diaosichengxuyuan.one.yuan.brush.ticket.web.login.handler;

import com.alibaba.fastjson.JSON;
import com.diaosichengxuyuan.one.yuan.brush.ticket.common.constant.BaseDTO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.common.constant.StatusCode;
import com.diaosichengxuyuan.one.yuan.brush.ticket.web.login.config.CorsConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 自定义鉴权失败处理器
 *
 * @author liuhaipeng
 * @date 2019/6/17
 */
@Slf4j
@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Autowired
    private CorsConfig corsConfig;

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                        AuthenticationException e) throws IOException, ServletException {
        log.error("用户登录失败", e);

        //设置允许跨域
        corsConfig.setAccessControlAllowHeader(httpServletResponse);

        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();
        BaseDTO baseDTO = new BaseDTO();
        baseDTO.setStatusCode(StatusCode.FAILURE.getCode());
        baseDTO.setMessage(e.getMessage());
        out.write(JSON.toJSONString(baseDTO));
        out.flush();
        out.close();
    }

}
