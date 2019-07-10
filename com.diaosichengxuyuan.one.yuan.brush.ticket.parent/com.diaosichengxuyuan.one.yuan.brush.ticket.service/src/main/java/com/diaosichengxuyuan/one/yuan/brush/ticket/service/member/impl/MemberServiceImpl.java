package com.diaosichengxuyuan.one.yuan.brush.ticket.service.member.impl;

import com.diaosichengxuyuan.one.yuan.brush.ticket.common.util.AnonymousUtil;
import com.diaosichengxuyuan.one.yuan.brush.ticket.common.util.DateUtil;
import com.diaosichengxuyuan.one.yuan.brush.ticket.dao.member.MemberMapper;
import com.diaosichengxuyuan.one.yuan.brush.ticket.dao.member.entity.MemberDO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.member.MemberDTO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author liuhaipeng
 * @date 2019/6/18
 */
@Service("memberService")
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public MemberDTO queryMember(String accountId) {
        MemberDO memberDO = memberMapper.selectOne(MemberDO.builder().accountId(accountId).build());
        if(memberDO == null) {
            return MemberDTO.builder().accountId(AnonymousUtil.anonymize(accountId)).isMember(false).build();
        }

        return MemberDTO.builder().accountId(AnonymousUtil.anonymize(memberDO.getAccountId())).periodValidity(
            DateUtil.formatDate(memberDO.getPeriodValidity(), "yyyy-MM-dd HH:mm:ss")).isMember(
            memberDO.getPeriodValidity().after(new Date())).build();
    }

}
