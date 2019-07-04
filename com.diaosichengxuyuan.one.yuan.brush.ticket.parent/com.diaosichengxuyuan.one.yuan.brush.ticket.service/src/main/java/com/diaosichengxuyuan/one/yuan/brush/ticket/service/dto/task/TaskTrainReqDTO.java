package com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskTrainReqDTO {

    @NotEmpty
    private String train;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private Date startSaleTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private Date endSaleTime;

}
