package com.xl.demo.controller;


import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.xl.demo.annotation.Log;
import com.xl.demo.domain.User;
import com.xl.demo.domain.vo.LoginParam;
import com.xl.demo.service.LoginInfoService;
import com.xl.demo.utils.ResultJson;
import eu.bitwalker.useragentutils.UserAgent;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author XL
 * @since 2022-01-13
 */
@Api(tags = "登录集合")
@RestController
public class LoginInfoController {

    @Autowired
    private LoginInfoService loginInfoService;

    @ApiImplicitParam(name = "loginParam",dataType = "LoginParam",value = "登录信息",required = true)
    @ApiOperationSupport(author = "Xl")
    @ApiOperation("登录操作")
    @PostMapping("/login")
    public ResultJson<Object> login(@RequestBody LoginParam loginParam){
        return ResultJson.success(loginInfoService.login(loginParam));
    }


    @ApiOperationSupport(author = "Xl")
    @ApiOperation("退出登录")
    @GetMapping("/logout")
    public ResultJson<Object> logout(){
        loginInfoService.logout();
        return ResultJson.success(true);
    }

}

