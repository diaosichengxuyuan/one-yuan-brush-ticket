package com.diaosichengxuyuan.one.yuan.brush.ticket.web.login.filter;

import com.diaosichengxuyuan.one.yuan.brush.ticket.web.login.config.CorsConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * cors过滤器
 *
 * @author liuhaipeng
 * @date 2019/6/26
 */
@Slf4j
@Component
public class CustomCorsFilter implements Filter {

    @Autowired
    private CorsConfig corsConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
        throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse)servletResponse;
        corsConfig.setAccessControlAllowHeader(httpResponse);
        log.info("增加cors header");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

}
