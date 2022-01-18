package com.xl.demo.service.impl;

import com.xl.demo.domain.Role;
import com.xl.demo.mapper.RoleMapper;
import com.xl.demo.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色信息表 服务实现类
 * </p>
 *
 * @author XL
 * @since 2022-01-12
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
