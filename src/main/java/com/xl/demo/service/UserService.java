package com.xl.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xl.demo.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xl.demo.domain.vo.BooRes;
import com.xl.demo.domain.vo.PageParam;

import java.util.List;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author XL
 * @since 2022-01-12
 */
public interface UserService extends IService<User> {

    /**
     *  获取当前用户名
     * @return String
     */
    String getUserName();

    /**
     *  添加用户
     * @param user 用户
     * @return BooRes
     */
    BooRes addUser(User user);

    /**
     *  删除用户
     * @param userIds 用户ID集
     * @return BooRes
     */
    BooRes delUser(Long[] userIds);

    /**
     *  更新用户
     * @param user 用户
     * @return BooRes
     */
    BooRes updateUser(User user);

    /**
     * 分页查询用户
     * @param param 参数
     * @return IPage
     */
    IPage<User> getUsersByPage(PageParam<User> param);

    /**
     * 根据用户ID查询详情信息
     * @param userId 用户ID
     * @return User
     */
    User getUserById(Long userId);

    /**
     * 动态获取数据
     * @return List
     */
    List<User> getUsersAll();

}
