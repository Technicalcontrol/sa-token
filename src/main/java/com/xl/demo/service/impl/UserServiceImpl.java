package com.xl.demo.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaFoxUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xl.demo.domain.User;
import com.xl.demo.domain.vo.BooRes;
import com.xl.demo.domain.vo.PageParam;
import com.xl.demo.mapper.UserMapper;
import com.xl.demo.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xl.demo.utils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author XL
 * @since 2022-01-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired(required = false)
    private UserMapper userMapper;

    @Override
    public String getUserName() {
        String username = null;
        User user = userMapper.selectById(StpUtil.getLoginIdAsLong());
        if(user!=null){
            username = user.getUsername();
        }
        return username;
    }

    @Override
    public BooRes addUser(User user) {
        BooRes booRes = new BooRes();
        booRes.setRes(false);
        if(user!=null){
            User user1 = userMapper.selectOne(new QueryWrapper<User>().eq("username",user.getUsername()));
            if(user1==null){
                user.setPassword(SaSecureUtil.md5(user.getPassword()));
                user.setCreateBy(getUserName());
                user.setCreateTime(new Date());
                int res = userMapper.insert(user);
                if(res > 0){
                    booRes.setRes(true);
                    booRes.setMsg("添加成功");
                }else{
                    booRes.setMsg("添加失败");
                }
            }else{
                booRes.setMsg("用户名参数错误");
            }
        }else{
            booRes.setMsg("用户参数为空");
        }
        return booRes;
    }

    @Override
    public BooRes delUser(List<Long> userIds) {
        BooRes booRes = new BooRes();
        booRes.setRes(false);
        if(userIds!=null && userIds.size()>0){
            int res = userMapper.deleteUsers(userIds);
            if(res > 0){
                booRes.setRes(true);
                booRes.setMsg("删除成功");
            }else{
                booRes.setMsg("删除失败");
            }
        }else{
            booRes.setMsg("参数错误");
        }
        return booRes;
    }

    @Override
    public BooRes updateUser(User user) {
        BooRes booRes = new BooRes();
        booRes.setRes(false);
        if(user!=null){
            if(user.getUserId()!=null&&user.getUserId()>0){
                int res = userMapper.updateById(user);
                if(res > 0){
                    booRes.setRes(true);
                    booRes.setMsg("用户更新成功");
                }else{
                    booRes.setMsg("用户更新失败");
                }
            }else{
                booRes.setMsg("用户ID参数错误");
            }
        }else{
            booRes.setMsg("用户参数为空");
        }
        return booRes;
    }

    @Override
    public IPage<User> getUsersByPage(PageParam<User> param) {
        IPage<User> page = null;
        if(MyUtils.checkPageParam(param)){
            Page<User>  page1 = new Page<>(param.getPageIndex(),param.getPageSize());
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            User user = param.getData();
            if( user!=null){
                if(!SaFoxUtil.isEmpty( user.getUsername())){
                    wrapper.like("username", user.getUsername());
                }
                if(!SaFoxUtil.isEmpty( user.getCreateBy())){
                    wrapper.like("create_by", user.getCreateBy());
                }
                if(!SaFoxUtil.isEmpty( user.getSex())){
                    wrapper.eq("sex", user.getSex());
                }
            }
            page = userMapper.selectPage(page1,wrapper);
        }
        return page;
    }

    @Override
    public User getUserById(Long userId) {
        if(userId!=null&&userId>0){
            return userMapper.selectById(userId);
        }
        return null;
    }

    @Override
    public List<User> getUsersAll() {
        return userMapper.getUsers();
    }
}
