package com.xl.demo.utils;

import cn.dev33.satoken.exception.DisableLoginException;
import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author XL
 * 全局异常处理,全局异常拦截（拦截项目中的所有异常）
 * @date 2022/1/11 16:26
 */
@Slf4j
@ControllerAdvice
public class GlobalException {

    @ResponseBody
    @ExceptionHandler
    public ResultJson<Object> handlerException(Exception e, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // 打印堆栈，以供调试
        e.printStackTrace();

        // 不同异常返回不同状态码
        ResultJson<Object> aj = null;
        // 如果是未登录异常
        if (e instanceof NotLoginException) {
            NotLoginException ee = (NotLoginException) e;
            aj = ResultJson.fail(ee.getMessage(),null);
        }
        // 如果是角色异常
        else if(e instanceof NotRoleException) {
            NotRoleException ee = (NotRoleException) e;
            aj = ResultJson.fail("无此角色：" + ee.getRole(),null);
        }
        // 如果是权限异常
        else if(e instanceof NotPermissionException) {
            NotPermissionException ee = (NotPermissionException) e;
            aj = ResultJson.fail("无此权限：" + ee.getCode(),null);
        }
        // 如果是被封禁异常
        else if(e instanceof DisableLoginException) {
            DisableLoginException ee = (DisableLoginException) e;
            aj = ResultJson.fail("账号被封禁：" + ee.getDisableTime() + "秒后解封",null);
        }
        // 普通异常, 输出：500 + 异常信息
        else {
            aj = ResultJson.error(e.getMessage());
        }
        log.info("IP地址："+MyUtils.getIpAddress(request)+",错误信息："+aj.getMessage());
        // 返回给前端
        return aj;
    }
}
