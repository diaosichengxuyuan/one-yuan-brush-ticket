package com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.passenger;

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
public class PassengerListDTO extends BaseDTO {

    private List<PassengerDTO> passengerList;

}
