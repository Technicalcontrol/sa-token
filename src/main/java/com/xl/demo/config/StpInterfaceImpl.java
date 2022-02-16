package com.xl.demo.config;

import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import com.xl.demo.domain.Role;
import com.xl.demo.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author XL
 *  自定义权限验证接口扩展
 * @date 2022/1/12 10:22
 */
@Component
public class StpInterfaceImpl  implements StpInterface {

    @Autowired(required = false)
    private UserRoleMapper userRoleMapper;

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // 本list仅做模拟，实际项目中要根据具体业务逻辑来查询权限
        List<String> list = new ArrayList<String>();
        if(userRoleMapper.checkRoleIsAdmin(StpUtil.getLoginIdAsLong()).size()>0){
            list.add("*");
        }else{
            list.add("test-url");
            list.add("test-testDemo");
        }
        return list;
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        // 本list仅做模拟，实际项目中要根据具体业务逻辑来查询角色
        List<String> list = new ArrayList<String>();
        List<Role> roles = userRoleMapper.getRolesByUserId(StpUtil.getLoginIdAsLong());
        for (Role role:roles){
            if(!"1".equals(role.getRoleStatus())){
                list.add(role.getRoleKey());
            }
        }
        System.out.println(list);
        return list;
    }
}
