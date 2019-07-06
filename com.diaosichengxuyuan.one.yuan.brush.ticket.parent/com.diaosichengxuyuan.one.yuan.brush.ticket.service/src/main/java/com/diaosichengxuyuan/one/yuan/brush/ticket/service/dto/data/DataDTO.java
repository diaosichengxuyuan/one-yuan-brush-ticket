package com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.data;

import com.diaosichengxuyuan.one.yuan.brush.ticket.common.constant.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DataDTO extends BaseDTO {

    private Long taskNum;

    private Long userNum;

    private Long memberNum;

}