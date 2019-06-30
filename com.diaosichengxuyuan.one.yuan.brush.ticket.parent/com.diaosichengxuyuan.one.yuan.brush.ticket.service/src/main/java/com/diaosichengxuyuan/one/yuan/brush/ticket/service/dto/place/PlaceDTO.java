package com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.place;

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
public class PlaceDTO extends BaseDTO {

    private List<String> hotPlaces;

    private List<String> places1;

    private List<String> places2;

    private List<String> places3;

    private List<String> places4;

    private List<String> places5;

}
