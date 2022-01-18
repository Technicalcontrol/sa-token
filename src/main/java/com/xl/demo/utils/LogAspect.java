package com.xl.demo.utils;

import com.xl.demo.annotation.Log;
import com.xl.demo.domain.OperationLog;
import com.xl.demo.mapper.OperationLogMapper;
import com.xl.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author XL
 * 自定义log注解处理
 * @date 2022/1/14 10:59
 */
@Component
@Aspect
@Slf4j
public class LogAspect {

    @Autowired(required = false)
    private OperationLogMapper operationLogMapper;

    @Autowired
    private UserService userService;

    @Pointcut("@annotation(com.xl.demo.annotation.Log)")
    private void pointcut(){

    }

    @Before("pointcut() && @annotation(logger)")
    public void advice(JoinPoint joinPoint,Log logger) {
        OperationLog operationLog = new OperationLog();
        operationLog.setOperationName(userService.getUserName());
        operationLog.setOperationTime(new Date());
        operationLog.setOperationTitle(logger.title());
        operationLog.setOperationType(logger.type());
        operationLogMapper.insert(operationLog);
        //log.info("["+ joinPoint.getSignature().getName() +"] ["+logger.type()+"] "+logger.title());
    }

}
