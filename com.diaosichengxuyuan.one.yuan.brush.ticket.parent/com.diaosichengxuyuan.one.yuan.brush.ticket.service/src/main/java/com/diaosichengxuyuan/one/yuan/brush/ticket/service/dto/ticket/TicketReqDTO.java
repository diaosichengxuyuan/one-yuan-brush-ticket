package com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.ticket;

import com.diaosichengxuyuan.one.yuan.brush.ticket.common.constant.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketReqDTO {

    @NotEmpty
    private String startPlace;

    @NotEmpty
    private String endPlace;

    @NotNull
    private Date startDate;

    @NotNull
    private Boolean isStudent;

    @NotNull
    private Boolean isHighSpeed;

}
