package com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.ticket;

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
public class AcquiredTicketDetailResDTO {

    private String name;

    private String attribute;

    private String cardType;

    private String cardNumber;

    private String seat;

    private String carriage;

    private String seatNumber;

    private String price;

}