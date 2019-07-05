package com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskReqDTO {

    private Long id;

    /**
     * 前端无需传入
     */
    private String accountId;

    @NotEmpty
    private String startPlace;

    @NotEmpty
    private String endPlace;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @NotNull
    private Date startDate;

    @NotEmpty
    private String seat;

    @NotEmpty
    private String phone;

    @NotNull
    private List<TaskTrainReqDTO> taskTrainReqDTOList;

    @NotNull
    private List<Long> passengerIdList;

}
