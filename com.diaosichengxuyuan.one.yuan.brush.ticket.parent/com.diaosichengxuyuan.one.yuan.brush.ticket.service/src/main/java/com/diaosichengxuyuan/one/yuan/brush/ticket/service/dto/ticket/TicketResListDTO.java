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
public class TicketResListDTO extends BaseDTO {

    private List<TicketResDTO> ticketList;

}
