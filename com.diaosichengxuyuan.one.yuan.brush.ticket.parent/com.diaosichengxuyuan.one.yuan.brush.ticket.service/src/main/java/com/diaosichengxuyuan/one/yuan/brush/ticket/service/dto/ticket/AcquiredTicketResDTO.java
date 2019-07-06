package com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.ticket;

import com.diaosichengxuyuan.one.yuan.brush.ticket.common.constant.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AcquiredTicketResDTO extends BaseDTO {

    private Long id;

    private String date;

    private String week;

    private String train;

    private String startPlace;

    private String endPlace;

    private String startTime;

    private String endTime;

    private String status;

    private List<AcquiredTicketDetailResDTO> acquiredTicketDetailResDTOList;

}
