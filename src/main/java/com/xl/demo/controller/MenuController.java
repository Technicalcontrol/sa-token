package com.xl.demo.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import com.xl.demo.annotation.Log;
import com.xl.demo.domain.Menu;
import com.xl.demo.domain.vo.TreeList;
import com.xl.demo.service.MenuService;
import com.xl.demo.utils.ResultJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 菜单信息表 前端控制器
 * </p>
 *
 * @author XL
 * @since 2022-01-18
 */
@RestController
@RequestMapping("/menu")
@Api(tags = "菜单管理")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @Log(title = "获取菜单",type = "查询菜单")
    @ApiOperation(("获取菜单"))
    @GetMapping("/all")
    @SaCheckPermission("menu-all")
    public ResultJson<List<Menu>> getMenus(){
        //StpUtil.checkPermission("menu-all");
        return ResultJson.success(menuService.getMenuAll());
    }

    @Log(title = "树形菜单",type = "查询菜单")
    @ApiOperation(("树形菜单"))
    @GetMapping("/tree")
    public ResultJson<List<TreeList>> getMenuTree(){
        StpUtil.checkPermission("menu-tree");
        return ResultJson.success(menuService.getTreeMenu());
    }

}

