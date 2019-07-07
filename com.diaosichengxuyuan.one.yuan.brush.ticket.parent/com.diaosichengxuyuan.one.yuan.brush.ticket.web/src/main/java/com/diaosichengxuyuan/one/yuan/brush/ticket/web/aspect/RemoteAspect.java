package com.diaosichengxuyuan.one.yuan.brush.ticket.web.aspect;

import com.diaosichengxuyuan.one.yuan.brush.ticket.common.constant.BaseDTO;
import com.diaosichengxuyuan.one.yuan.brush.ticket.common.constant.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * remote方法切面
 *
 * @author liuhaipeng
 * @date 2019/6/27
 */
@Component
@Slf4j
@Aspect
@Order(0)
public class RemoteAspect {

    @Pointcut("execution(* com.diaosichengxuyuan.one.yuan.brush.ticket.web.remote..*.*(..))")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object log(ProceedingJoinPoint joinPoint) {
        log.info(String.format("切面进入方法[%s]", joinPoint.getSignature().toString()));
        try {
            Object result = joinPoint.proceed();
            log.info(String.format("切面正常出方法[%s]", joinPoint.getSignature().toString()));
            return result;
        } catch(Throwable throwable) {
            log.error(String.format("切面异常出方法[%s]", joinPoint.getSignature().toString()), throwable);
            BaseDTO baseDTO = new BaseDTO();
            baseDTO.setStatusCode(StatusCode.FAILURE.getCode());
            baseDTO.setMessage("服务端未知异常");
            return baseDTO;
        }

    }

}
