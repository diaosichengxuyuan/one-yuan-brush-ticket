package com.diaosichengxuyuan.one.yuan.brush.ticket.common.page;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * 分页对象
 *
 * @author liuhaipeng
 * @date 2018/8/26
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Page {

    @NotNull
    @Range(min = 0)
    private Integer num;

    @NotNull
    @Range(min = 0, max = 100)
    private Integer size;
}
