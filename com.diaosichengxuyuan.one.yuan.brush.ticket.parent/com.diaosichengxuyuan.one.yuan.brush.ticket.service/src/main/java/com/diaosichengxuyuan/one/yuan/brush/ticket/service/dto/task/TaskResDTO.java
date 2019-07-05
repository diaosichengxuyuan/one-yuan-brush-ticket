package com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.task;

import com.diaosichengxuyuan.one.yuan.brush.ticket.common.constant.BaseDTO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.passenger.PassengerDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskResDTO extends BaseDTO {

    private Long id;

    private String startPlace;

    private String endPlace;

    private String startDate;

    private String status;

    private String seat;

    private String phone;

    private List<TaskTrainResDTO> taskTrainResDTOList;

    private List<PassengerDTO> passengerDTOList;

}
