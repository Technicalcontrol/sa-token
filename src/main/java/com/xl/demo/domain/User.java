package com.xl.demo.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.xl.demo.utils.UserSexConverter;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author XL
 * @since 2022-01-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    @TableId(value = "user_id", type = IdType.AUTO)
    @ExcelProperty({"用户信息","用户ID"})
    private Long userId;

    /**
     * 用户账号
     */
    @ApiModelProperty(value = "用户账号")
    @ExcelProperty({"用户信息","用户账号"})
    @NotBlank(message = "用户名不能为空")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9_]{4,15}$",message = "字母开头5-16字母数字下划线")
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    @ExcelProperty("密码")
    @NotBlank(message = "密码不能为空")
    @Length(min = 5,max = 32,message = "密码长度为5-32位")
    private String password;

    /**
     * 用户状态
     */
    @ApiModelProperty(value = "用户状态")
    @ExcelProperty("用户状态")
    private String status;

    /**
     * 性别
     */
    @ApiModelProperty(value = "性别")
    @ExcelProperty(value = "性别",converter = UserSexConverter.class)
    @Pattern(regexp = "[01]",message = "无效性别标识")
    private String sex;

    /**
     * 出生日期
     */
    @ApiModelProperty(value = "出生日期")
    @ExcelProperty("出生日期")
    private LocalDateTime birthday;

    /**
     * 创建日期
     */
    @ApiModelProperty(value = "创建日期")
    @TableField("create_time")
    @ExcelProperty("创建日期")
    private LocalDateTime createTime;

    /**
     * 创建者
     */
    @ApiModelProperty(value = "创建者")
    @TableField("create_by")
    @ExcelProperty("创建者")
    private String createBy;


}
