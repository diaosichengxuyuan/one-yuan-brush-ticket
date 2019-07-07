package com.diaosichengxuyuan.one.yuan.brush.ticket.web.remote.member.impl;

import com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.member.MemberDTO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.member.MemberService;
import com.diaosichengxuyuan.one.yuan.brush.ticket.web.remote.member.MemberRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author liuhaipeng
 * @date 2019/6/18
 */
@RestController("memberRemote")
@RequestMapping("/member")
public class MemberRemoteImpl implements MemberRemote {

    @Autowired
    private MemberService memberService;

    @RequestMapping(value = "/queryMember", method = RequestMethod.GET)
    @Override
    public MemberDTO queryMember() {
        User uer = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return memberService.queryMember(uer.getUsername());
    }

}
