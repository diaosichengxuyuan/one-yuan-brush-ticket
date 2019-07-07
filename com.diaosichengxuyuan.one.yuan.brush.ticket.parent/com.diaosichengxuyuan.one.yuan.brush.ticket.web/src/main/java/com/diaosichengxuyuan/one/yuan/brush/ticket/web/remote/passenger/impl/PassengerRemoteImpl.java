package com.diaosichengxuyuan.one.yuan.brush.ticket.web.remote.passenger.impl;

import com.diaosichengxuyuan.one.yuan.brush.ticket.service.dto.passenger.PassengerListDTO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.service.passenger.PassengerService;
import com.diaosichengxuyuan.one.yuan.brush.ticket.web.remote.passenger.PassengerRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuhaipeng
 * @date 2019/7/3
 */
@RestController("passengerRemote")
@RequestMapping("/passenger")
public class PassengerRemoteImpl implements PassengerRemote {

    @Autowired
    private PassengerService passengerService;

    @RequestMapping(value = "/queryPassengers", method = RequestMethod.GET)
    @Override
    public PassengerListDTO queryPassengers() {
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return passengerService.queryPassengers(user.getUsername());
    }

}
