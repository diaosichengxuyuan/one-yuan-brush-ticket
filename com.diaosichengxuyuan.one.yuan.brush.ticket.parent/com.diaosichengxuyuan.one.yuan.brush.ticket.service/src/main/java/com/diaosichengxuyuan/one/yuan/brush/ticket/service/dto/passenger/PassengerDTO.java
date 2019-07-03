package com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.passenger;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "passenger")
public class PassengerDTO {

    private Long id;

    private String name;

    private String attribute;

    private String idCard;

}