package com.diaosichengxuyuan.one.yuan.brush.ticket.web.member;

import com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.member.MemberDTO;

/**
 * 会员接口
 *
 * @author liuhaipeng
 * @date 2019/6/18
 */
public interface MemberRemote {

    /**
     * 查询会员信息
     * @return 会员信息
     */
    MemberDTO queryMember();

}
