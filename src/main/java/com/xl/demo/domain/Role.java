package com.xl.demo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * 角色信息表
 * </p>
 *
 * @author XL
 * @since 2022-01-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    @ApiModelProperty("角色ID")
    @TableId(value = "role_id", type = IdType.AUTO)
    private Long roleId;

    /**
     * 角色名称
     */
    @ApiModelProperty("角色ID")
    @TableField("role_name")
    private String roleName;

    /**
     * 角色字符串
     */
    @ApiModelProperty("角色字符串")
    @TableField("role_key")
    private String roleKey;

    /**
     * 显示顺序
     */
    @ApiModelProperty("显示顺序")
    @TableField("role_sort")
    private Integer roleSort;

    /**
     * 角色状态（0正常，1停用）
     */
    @ApiModelProperty("角色状态（0正常，1停用）")
    @TableField("role_status")
    private String roleStatus;

    /**
     * 删除标志（0正常，1删除）
     */
    @ApiModelProperty("删除标志（0正常，1删除）")
    @TableField("role_del_flag")
    private String roleDelFlag;

    /**
     * 角色创建时间
     */
    @ApiModelProperty("角色创建时间")
    @TableField("role_create_time")
    private Date roleCreateTime;

    /**
     * 创建者
     */
    @ApiModelProperty("创建者")
    @TableField("role_create_by")
    private String roleCreateBy;

    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    @TableField("role_update_time")
    private Date roleUpdateTime;

    /**
     * 更新者
     */
    @ApiModelProperty("更新者")
    @TableField("role_update_by")
    private String roleUpdateBy;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    @TableField("role_remark")
    private String roleRemark;


}
