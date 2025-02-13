package com.diaosichengxuyuan.one.yuan.brush.ticket.service.passenger.impl;

import com.diaosichengxuyuan.one.yuan.brush.ticket.common.util.AnonymousUtil;
import com.diaosichengxuyuan.one.yuan.brush.ticket.common.util.MapperUtil;
import com.diaosichengxuyuan.one.yuan.brush.ticket.dao.passenger.PassengerMapper;
import com.diaosichengxuyuan.one.yuan.brush.ticket.dao.passenger.entity.PassengerDO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.passenger.PassengerDTO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.passenger.PassengerListDTO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.passenger.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liuhaipeng
 * @date 2019/7/3
 */
@Service("passengerService")
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    private PassengerMapper passengerMapper;

    @Override
    public PassengerListDTO queryPassengers(String accountId) {
        List<PassengerDO> passengerDOList = passengerMapper.select(PassengerDO.builder().accountId(accountId).build());
        List<PassengerDTO> passengerDTOList = MapperUtil.mapAsList(passengerDOList, PassengerDO.class,
            PassengerDTO.class);

        passengerDTOList.forEach(
            passengerDTO -> passengerDTO.setCardNumber(AnonymousUtil.anonymize(passengerDTO.getCardNumber())));

        return PassengerListDTO.builder().passengerList(passengerDTOList).build();
    }

}