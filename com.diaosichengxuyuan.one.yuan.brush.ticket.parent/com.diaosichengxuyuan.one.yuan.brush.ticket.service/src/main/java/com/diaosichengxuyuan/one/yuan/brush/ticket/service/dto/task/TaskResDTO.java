package com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskResDTO {

    private Long id;

    private String startPlace;

    private String endPlace;

    private String startDate;

    private String status;

}
