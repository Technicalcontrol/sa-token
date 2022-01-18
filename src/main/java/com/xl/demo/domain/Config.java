package com.xl.demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 系统配置信息表
 * </p>
 *
 * @author XL
 * @since 2022-01-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Config implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 参数主键
     */
    @ApiModelProperty("参数主键")
    @TableId(value = "config_id", type = IdType.AUTO)
    private Long configId;

    /**
     * 参数名称
     */
    @ApiModelProperty("参数名称")
    @TableField("config_name")
    private String configName;

    /**
     * 参数键名
     */
    @TableField("config_key")
    @ApiModelProperty("参数键名")
    private String configKey;

    /**
     * 参数键值
     */
    @ApiModelProperty("参数键值")
    @TableField("config_value")
    private String value;

    /**
     * 系统内置（Y是 N否）
     */
    @ApiModelProperty("系统内置（Y是 N否）")
    @TableField("config_type")
    private String type;

    /**
     * 创建者
     */
    @ApiModelProperty("创建者")
    @TableField("config_create_by")
    private String createBy;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    @TableField("config_create_time")
    private Data time;

    /**
     * 更新者
     */
    @ApiModelProperty("更新者")
    @TableField("config_update_by")
    private String updateBy;

    /**
     * 更新时间
     */
    @TableField("config_update_time")
    @ApiModelProperty("更新时间")
    private Date updateTime;

    /**
     * 备注
     */
    @TableField("config_remark")
    @ApiModelProperty("备注")
    private String configRemark;


}
