package com.xl.demo.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xl.demo.domain.Config;
import com.xl.demo.domain.LoginInfo;
import com.xl.demo.domain.User;
import com.xl.demo.domain.vo.LoginParam;
import com.xl.demo.mapper.ConfigMapper;
import com.xl.demo.mapper.LoginInfoMapper;
import com.xl.demo.mapper.UserMapper;
import com.xl.demo.service.LoginInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xl.demo.utils.MyUtils;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author XL
 * @since 2022-01-13
 */
@Service
public class LoginInfoServiceImpl extends ServiceImpl<LoginInfoMapper, LoginInfo> implements LoginInfoService {

    @Autowired(required = false)
    private ConfigMapper configMapper;

    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired(required = false)
    private LoginInfoMapper loginInfoMapper;

    @Override
    public String login(LoginParam loginParam) {
        HttpServletRequest request = MyUtils.getRequest();
        Config config = configMapper.selectOne(new QueryWrapper<Config>().eq("config_key","system.account.captchaOnOff"));
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("username",loginParam.getUsername()));
        if(config!=null&&"true".equalsIgnoreCase(config.getValue())){
            if("".equals(loginParam.getCode())){
                if(user!=null&&user.getPassword().equals(SaSecureUtil.md5(loginParam.getPassword()))){
                    StpUtil.login(user.getUserId());
                    recommendLoginInfo(loginParam.getUsername(),true,"登录成功",request);
                }else{
                    recommendLoginInfo(loginParam.getUsername(),false,"用户名或密码错误",request);
                }
            }else{
                recommendLoginInfo(loginParam.getUsername(),false,"验证码错误",request);
            }
        }else{
            if(user!=null&&user.getPassword().equals(SaSecureUtil.md5(loginParam.getPassword()))){
                StpUtil.login(user.getUserId());
                recommendLoginInfo(loginParam.getUsername(),true,"登录成功",request);
            }else{
                recommendLoginInfo(loginParam.getUsername(),false,"用户名或密码错误",request);
            }
        }
        return StpUtil.getTokenInfo().getTokenValue();
    }

    @Override
    public void logout() {
        recommendLoginInfo(getUsername(),true,"退出登录",MyUtils.getRequest());
        // 注销登录
        StpUtil.logout();
    }

    /**
     * 记录登录日志
     */
    public void recommendLoginInfo(String username,boolean flag,String msg,HttpServletRequest request){
        LoginInfo loginInfo = new LoginInfo();
        // 获取客户端IP
        String ip = MyUtils.getIpAddress(request);
        // 获取地理位置1
        String location = MyUtils.getLocationByIp(ip);
        // 获取客户端操作信息
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));

        String status = flag ? "1":"0";
        loginInfo.setInfoStatus(status);
        loginInfo.setIp(ip);
        loginInfo.setInfoTime(new Date());
        loginInfo.setBrowser(userAgent.getBrowser().getName());
        loginInfo.setMsg(msg);
        loginInfo.setIpLocation(location);
        loginInfo.setOs(userAgent.getOperatingSystem().getName());
        loginInfo.setPersonName(username);

        loginInfoMapper.insert(loginInfo);
    }


    /**
     * 获取登录用户名
     */
    public String getUsername(){
        User user = userMapper.selectById(StpUtil.getLoginIdAsLong());
        return user.getUsername();
    }


}
