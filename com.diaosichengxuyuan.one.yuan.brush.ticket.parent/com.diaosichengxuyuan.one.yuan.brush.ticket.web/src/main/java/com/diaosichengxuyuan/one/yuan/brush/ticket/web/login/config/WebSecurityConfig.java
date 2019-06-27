package com.diaosichengxuyuan.one.yuan.brush.ticket.web.login.config;

import com.diaosichengxuyuan.one.yuan.brush.ticket.web.login.filter.CustomUsernamePasswordAuthenticationFilter;
import com.diaosichengxuyuan.one.yuan.brush.ticket.web.login.handler.CustomAuthenticationFailureHandler;
import com.diaosichengxuyuan.one.yuan.brush.ticket.web.login.handler.CustomAuthenticationSuccessHandler;
import com.diaosichengxuyuan.one.yuan.brush.ticket.web.login.handler.CustomLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * 自定义鉴权配置
 *
 * @author liuhaipeng
 * @date 2019/6/17
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationSuccessHandler successHandler;

    @Autowired
    private CustomAuthenticationFailureHandler failureHandler;

    @Autowired
    private CustomLogoutSuccessHandler logoutSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //添加自定义的登录filter，配置在UsernamePasswordAuthenticationFilter之前，这样就不需要走到UsernamePasswordAuthenticationFilter处理了
        http.addFilterBefore(customUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        http.authorizeRequests().anyRequest().authenticated().and().formLogin().loginProcessingUrl("/login")
            .successHandler(successHandler).failureHandler(failureHandler).and().logout().logoutSuccessHandler(
            logoutSuccessHandler).and().csrf().disable();

    }

    @Bean
    public CustomUsernamePasswordAuthenticationFilter customUsernamePasswordAuthenticationFilter() throws Exception {
        CustomUsernamePasswordAuthenticationFilter authenticationFilter
            = new CustomUsernamePasswordAuthenticationFilter();
        //与UsernamePasswordAuthenticationFilter中不同，new AntPathRequestMatcher("/login", "POST")，我们取消了"POST"类型校验，
        //这样就会匹配所有类型，匹配过程中，非POST类型直接报错，POST类型校验用户名和密码
        authenticationFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login"));
        authenticationFilter.setAuthenticationManager(authenticationManager());
        //自定义Filter会导致原来configure中的successHandler和failureHandler失效，所以要重新设置
        authenticationFilter.setAuthenticationSuccessHandler(successHandler);
        authenticationFilter.setAuthenticationFailureHandler(failureHandler);
        return authenticationFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
       return new BCryptPasswordEncoder();
    }

}