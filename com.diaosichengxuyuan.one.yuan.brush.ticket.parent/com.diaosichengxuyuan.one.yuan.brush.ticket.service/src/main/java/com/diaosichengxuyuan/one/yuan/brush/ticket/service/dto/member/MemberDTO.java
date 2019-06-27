package com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.member;

import com.diaosichengxuyuan.one.yuan.brush.ticket.common.constant.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDTO extends BaseDTO {

    /**
     * 是否会员
     */
    private Boolean isMember;

    /**
     * 账号
     */
    private String accountId;

    /**
     * 会员有效期
     */
    private String periodValidity;

}