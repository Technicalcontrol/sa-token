package com.xl.demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author XL
 * @since 2022-01-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("登录日志")
public class LoginInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 访问ID
     */
    @ApiModelProperty("访问ID")
    @TableId(value = "info_id", type = IdType.AUTO)
    private Long infoId;

    /**
     * 访问用户
     */
    @ApiModelProperty("访问用户")
    @TableField("person_name")
    private String personName;

    /**
     * 登录IP地址
     */
    @ApiModelProperty("登录IP地址")
    private String ip;

    /**
     * ip访问地址
     */
    @ApiModelProperty("ip访问地址")
    @TableField("ip_location")
    private String ipLocation;

    /**
     * 浏览器类型
     */
    @ApiModelProperty("浏览器类型")
    private String browser;

    /**
     * 操作系统
     */
    @ApiModelProperty("操作系统")
    private String os;

    /**
     * 访问状态
     */
    @ApiModelProperty("访问状态")
    @TableField("info_status")
    private String infoStatus;

    /**
     * 提示信息
     */
    @ApiModelProperty("提示信息")
    private String msg;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    @TableField("info_time")
    private Date infoTime;


}
