package com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.task;

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
public class TaskResListDTO extends BaseDTO {

    private List<TaskResDTO> taskResDTOList;

}
