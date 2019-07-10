package com.diaosichengxuyuan.one.yuan.brush.ticket.web.login.handler;

import com.alibaba.fastjson.JSON;
import com.diaosichengxuyuan.one.yuan.brush.ticket.common.constant.BaseDTO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.common.util.DateUtil;
import com.diaosichengxuyuan.one.yuan.brush.ticket.common.util.HttpUtil;
import com.diaosichengxuyuan.one.yuan.brush.ticket.dao.member.MemberMapper;
import com.diaosichengxuyuan.one.yuan.brush.ticket.dao.member.entity.MemberDO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.data.DataService;
import com.diaosichengxuyuan.one.yuan.brush.ticket.web.login.config.CorsConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * 自定义鉴权成功处理器
 *
 * @author liuhaipeng
 * @date 2019/6/17
 */
@Slf4j
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private CorsConfig corsConfig;

    @Autowired
    private DataService dataService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {

        User user = (User)authentication.getPrincipal();

        log.info(String.format("用户登录成功，用户名：%s", user.getUsername()));

        //更新会员数据
        insertOrUpdateMember(user.getUsername());

        //设置允许跨域
        corsConfig.setAccessControlAllowHeader(httpServletResponse);

        PrintWriter out = httpServletResponse.getWriter();
        BaseDTO baseDTO = new BaseDTO();
        baseDTO.setMessage("登录成功");
        out.write(JSON.toJSONString(baseDTO));
        out.flush();
        out.close();
    }

    private void insertOrUpdateMember(String username) {
        MemberDO memberDO = memberMapper.selectOne(MemberDO.builder().accountId(username).build());
        if(memberDO == null) {
            memberMapper.insertSelective(MemberDO.builder().createTime(new Date()).modifyTime(new Date()).accountId(
                username).periodValidity(DateUtil.getLastTimeOfTheYear(new Date())).build());
            //插入数据表
            dataService.increaseMemberNumber();
        } else {
            memberMapper.updateByPrimaryKeySelective(MemberDO.builder().id(memberDO.getId()).modifyTime(new Date())
                .periodValidity(DateUtil.getLastTimeOfTheYear(new Date())).build());
        }
    }

}
