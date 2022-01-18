package com.xl.demo.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author XL
 * 登录参数
 * @date 2022/1/13 9:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginParam {

    @ApiModelProperty(value = "用户名",required = true)
    private String username;

    @ApiModelProperty(value = "密码",required = true)
    private String password;

    @ApiModelProperty("验证码")
    private String code;


}
