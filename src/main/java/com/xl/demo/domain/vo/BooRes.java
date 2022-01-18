package com.xl.demo.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author XL
 * @date 2022/1/14 13:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BooRes {

    @ApiModelProperty("操作结果")
    private Boolean res;

    @ApiModelProperty("提示语句")
    private String msg;
}
