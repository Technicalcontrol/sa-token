package com.xl.demo.domain;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 菜单信息表
 * </p>
 *
 * @author XL
 * @since 2022-01-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     */
    @TableId(value = "menu_id", type = IdType.AUTO)
    @ApiModelProperty("菜单ID")
    @ExcelProperty("菜单ID")
    private Long menuId;

    /**
     * 菜单名称
     */
    @TableField("menu_name")
    @ApiModelProperty("菜单名称")
    @ExcelProperty("菜单名称")
    private String menuName;

    /**
     * 父菜单ID
     */
    @TableField("parent_id")
    @ApiModelProperty("父菜单ID")
    @ExcelProperty("父菜单ID")
    private Long parentId;

    /**
     * 显示顺序
     */
    @TableField("order_num")
    @ApiModelProperty("显示顺序")
    @ExcelProperty("显示顺序")
    private Integer orderNum;

    /**
     * 路由地址
     */
    @TableField("path")
    @ApiModelProperty("路由地址")
    @ExcelProperty("路由地址")
    private String path;

    /**
     * 组件路径
     */
    @TableField("component")
    @ApiModelProperty("组件路径")
    @ExcelProperty("组件路径")
    private String component;

    /**
     * 路由参数
     */
    @TableField("query")
    @ApiModelProperty("路由参数")
    @ExcelProperty("路由参数")
    private String query;

    /**
     * 是否为外链（0是 1否）
     */
    @TableField("is_frame")
    @ApiModelProperty("是否为外链（0是 1否）")
    @ExcelProperty("是否为外链（0是 1否）")
    private Integer isFrame;

    /**
     * 是否缓存（0缓存 1不缓存）
     */
    @TableField("is_cache")
    @ApiModelProperty("是否缓存（0缓存 1不缓存）")
    @ExcelProperty("是否缓存（0缓存 1不缓存）")
    private Integer isCache;

    /**
     * 菜单类型（M目录 C菜单 F按钮）
     */
    @TableField("menu_type")
    @ApiModelProperty("菜单类型（M目录 C菜单 F按钮）")
    @ExcelProperty("菜单类型（M目录 C菜单 F按钮）")
    private String menuType;

    /**
     * 菜单状态（0显示 1隐藏）
     */
    @TableField("visible")
    @ApiModelProperty("菜单状态（0显示 1隐藏）")
    @ExcelProperty("菜单状态（0显示 1隐藏")
    private String visible;

    /**
     * 菜单状态（0正常 1停用）
     */
    @TableField("status")
    @ApiModelProperty("菜单状态（0正常 1停用）")
    @ExcelProperty("菜单状态（0正常 1停用）")
    private String status;

    /**
     * 权限标识
     */

    @TableField("perms")
    @ApiModelProperty("权限标识")
    @ExcelProperty("权限标识")
    private String perms;

    /**
     * 菜单图标
     */
    @TableField("icon")
    @ApiModelProperty("菜单图标")
    @ExcelProperty("菜单图标")
    private String icon;

    /**
     * 创建者
     */
    @TableField("create_by")
    @ApiModelProperty("创建者")
    @ExcelProperty("创建者")
    private String createBy;

    /**
     * 创建时间
     */
    @TableField("create_time")
    @ApiModelProperty("创建时间")
    @ExcelProperty("创建时间")
    private Date createTime;

    /**
     * 更新者
     */
    @TableField("update_by")
    @ApiModelProperty("更新者")
    @ExcelProperty("更新者")
    private String updateBy;

    /**
     * 更新时间
     */
    @TableField("update_time")
    @ApiModelProperty("更新时间")
    @ExcelProperty("更新时间")
    private Date updateTime;

    /**
     * 备注
     */
    @TableField("remark")
    @ApiModelProperty("备注")
    @ExcelProperty("备注")
    private String remark;

    @TableField(exist = false)
    @ExcelIgnore
    private List<Menu> menus;

}
