package com.diaosichengxuyuan.one.yuan.brush.ticket.common.validation;

import org.apache.commons.collections4.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 校验器
 *
 * @author liuhaipeng
 * @date 2018/8/24
 */
public class ValidationUtil {

    /**
     * 校验对象参数是否合法
     *
     * @param object
     * @throws IllegalArgumentException
     */
    public static void validate(Object object) {
        Validator validator = Validation.buildDefaultValidatorFactory().usingContext().getValidator();
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object);
        if(CollectionUtils.isNotEmpty(constraintViolations)) {
            String message = constraintViolations.stream().map(c -> c.getPropertyPath() + " " + c.getMessage()).collect(
                Collectors.toList()).toString();
            throw new IllegalArgumentException(message);
        }
    }
}