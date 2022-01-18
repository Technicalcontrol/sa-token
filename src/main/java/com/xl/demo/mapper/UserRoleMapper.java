package com.xl.demo.mapper;

import com.xl.demo.domain.Role;
import com.xl.demo.domain.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户角色关联表 Mapper 接口
 * </p>
 *
 * @author XL
 * @since 2022-01-12
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     *  根据用户ID获取所有角色
     * @param userId 用户ID
     * @return List
     */
    List<Role> getRolesByUserId(@Param("userId") Long userId);


    /**
     *  核查是否为管理员
     * @param userId 用户ID
     * @return List
     */
    List<Role> checkRoleIsAdmin(@Param("userId") Long userId);

}
