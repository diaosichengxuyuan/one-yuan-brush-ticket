package com.diaosichengxuyuan.one.yuan.brush.ticket.web.login.handler;

import com.alibaba.fastjson.JSON;
import com.diaosichengxuyuan.one.yuan.brush.ticket.common.constant.BaseDTO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.web.login.config.CorsConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 自定义退出成功处理器
 *
 * @author liuhaipeng
 * @date 2019/6/19
 */
@Slf4j
@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    @Autowired
    private CorsConfig corsConfig;

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Authentication authentication) throws IOException, ServletException {
        if(authentication != null) {
            User user = (User)authentication.getPrincipal();
            log.info(String.format("用户退出成功，用户名：%s", user.getUsername()));
        }

        //设置允许跨域
        corsConfig.setAccessControlAllowHeader(httpServletResponse);

        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();
        BaseDTO baseDTO = new BaseDTO();
        baseDTO.setMessage("退出成功");
        out.write(JSON.toJSONString(baseDTO));
        out.flush();
        out.close();
    }

}
