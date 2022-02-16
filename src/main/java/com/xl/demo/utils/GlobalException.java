package com.xl.demo.utils;

import cn.dev33.satoken.exception.DisableLoginException;
import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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

        // 不同异常返回不同状态码
        ResultJson<Object> aj = null;
        // 日志打印错误异常
        String msg = "错误异常";
        // 如果是未登录异常
        if (e instanceof NotLoginException) {
            NotLoginException ee = (NotLoginException) e;
            aj = ResultJson.fail(ee.getMessage(),null);
            msg = ee.getMessage();
        }
        // 如果是角色异常
        else if(e instanceof NotRoleException) {
            NotRoleException ee = (NotRoleException) e;
            aj = ResultJson.fail("无此角色：" + ee.getRole(),null);
            msg = "无此角色：" + ee.getRole();
        }
        // 如果是权限异常
        else if(e instanceof NotPermissionException) {
            NotPermissionException ee = (NotPermissionException) e;
            aj = ResultJson.fail("无此权限：" + ee.getCode(),null);
            msg = "无此权限：" + ee.getCode();
        }
        // 如果是被封禁异常
        else if(e instanceof DisableLoginException) {
            DisableLoginException ee = (DisableLoginException) e;
            aj = ResultJson.fail("账号被封禁：" + ee.getDisableTime() + "秒后解封",null);
            msg = "账号被封禁：" + ee.getDisableTime() + "秒后解封";
        }
        // 参数校验异常
        else if(e instanceof MethodArgumentNotValidException){
            MethodArgumentNotValidException ee = (MethodArgumentNotValidException)e;
            msg = ee.getBindingResult().getAllErrors().get(0).getDefaultMessage();
            aj = ResultJson.fail(msg);
        }
        // 其他异常, 输出：666 + 异常信息
        else {
            // 打印堆栈，以供调试
            e.printStackTrace();
            aj = ResultJson.error(e.getMessage());
            msg = "系统异常";
        }
        log.info("IP地址："+MyUtils.getIpAddress(request)+",异常信息："+ msg);
        // 返回给前端
        return aj;
    }
}
