package com.xl.demo.domain.vo;

import com.xl.demo.domain.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author XL
 * 分页参数
 * @date 2022/1/14 14:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageParam<T> {

    @ApiModelProperty(value = "当前页",required = true)
    private Integer pageIndex;

    @ApiModelProperty(value = "每页大小",required = true)
    private Integer pageSize;

    @ApiModelProperty("查询条件")
    private T data;

}
