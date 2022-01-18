package com.xl.demo.service;

import com.xl.demo.domain.LoginInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xl.demo.domain.vo.LoginParam;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author XL
 * @since 2022-01-13
 */
public interface LoginInfoService extends IService<LoginInfo> {

    /**
     *  登录操作
     * @param loginParam 登录信息
     * @return Object
     */
    Object login(LoginParam loginParam);

    /**
     *  登出操作
     */
    void logout();


}
