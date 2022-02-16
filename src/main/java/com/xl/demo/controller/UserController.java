package com.xl.demo.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.xl.demo.annotation.Log;
import com.xl.demo.domain.User;
import com.xl.demo.domain.vo.BooRes;
import com.xl.demo.domain.vo.PageParam;
import com.xl.demo.service.UserService;
import com.xl.demo.utils.ResultJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author XL
 * @since 2022-01-12
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户模块")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiImplicitParam(name = "user",dataType = "User",value = "用户信息",required = true)
    @ApiOperationSupport(author = "Xl")
    @ApiOperation("新增用户")
    @Log(title = "新增用户",type = "新增")
    @PostMapping("/add")
    public ResultJson<BooRes> addUser(@Validated @RequestBody User user){
        StpUtil.checkPermission("user-add");
        return ResultJson.success(userService.addUser(user));
    }

    @ApiImplicitParam(name = "userIds",dataType = "Long",value = "用户ID",required = true)
    @ApiOperation("删除用户")
    @Log(title = "删除用户" , type = "删除")
    @DeleteMapping("/{userIds}")
    public ResultJson<BooRes> delUsers(@PathVariable Long[] userIds){
        StpUtil.checkPermission("user-del");
        return ResultJson.success(userService.delUser(userIds));
    }

    @ApiOperation("查询用户")
    @Log(title = "查询用户",type = "其他")
    @GetMapping("/list")
    public ResultJson<IPage<User>> getUsersByPage(Integer pageIndex,Integer pageSize,User user){
        StpUtil.checkPermission("user-list");
        PageParam<User> param = new PageParam<>();
        param.setPageIndex(pageIndex);
        param.setPageSize(pageSize);
        param.setData(user);
        return ResultJson.success(userService.getUsersByPage(param));
    }

    @ApiOperation("查看用户详情")
    @Log(title = "查看用户详情",type = "其他")
    @GetMapping("/{userId}")
    public ResultJson<User> getUserById(@PathVariable @ApiParam(value = "用户ID",required = true) Long userId){
        StpUtil.checkPermission("user-detail");
        return ResultJson.success(userService.getUserById(userId));
    }

    @ApiOperation("踢人下线")
    @Log(title = "踢人下线",type = "其他")
    @GetMapping("/out/{userId}")
    public ResultJson<Boolean> kickOut(@PathVariable @ApiParam(value = "用户ID",required = true)Long userId){
        StpUtil.checkPermission("user-out");
        StpUtil.kickout(userId);
        return ResultJson.success(true);
    }






}

