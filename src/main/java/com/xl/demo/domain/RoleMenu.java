package com.xl.demo.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author XL
 * 角色菜单信息
 * @date 2022/1/18 15:01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("role_menu")
public class RoleMenu implements Serializable {
    
    @TableField("role_id")
    @ApiModelProperty("角色ID")
    private Long roleId;
    @TableField("menu_id")
    @ApiModelProperty("菜单ID")
    private Long menuId;
}
