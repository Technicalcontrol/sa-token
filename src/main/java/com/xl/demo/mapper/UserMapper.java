package com.xl.demo.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.xl.demo.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author XL
 * @since 2022-01-12
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     *  根据用户ID删除用户
     * @param userIds 用户ID集
     * @return int
     */
    int deleteUsers(@Param("userIds") List<Long> userIds);

    /**
     * 动态获取数据
     * @return List
     */
    @DS("ds_1")
    List<User> getUsers();

}
