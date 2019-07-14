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
public class TaskTrainResDTO {

    private String train;

    private Date startSaleTime;

    private Date endSaleTime;

    private String status;

}
