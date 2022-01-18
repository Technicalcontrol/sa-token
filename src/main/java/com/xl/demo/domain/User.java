package com.xl.demo.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@Accessors(chain = true)
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
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    @ExcelProperty("密码")
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
    @ExcelProperty("性别")
    private String sex;

    /**
     * 出生日期
     */
    @ApiModelProperty(value = "出生日期")
    @ExcelProperty("出生日期")
    private Date birthday;

    /**
     * 创建日期
     */
    @ApiModelProperty(value = "创建日期")
    @TableField("create_time")
    @ExcelProperty("创建日期")
    private Date createTime;

    /**
     * 创建者
     */
    @ApiModelProperty(value = "创建者")
    @TableField("create_by")
    @ExcelProperty("创建者")
    private String createBy;


}
