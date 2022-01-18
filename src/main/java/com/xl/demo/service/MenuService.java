package com.xl.demo.service;

import com.xl.demo.domain.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xl.demo.domain.vo.TreeList;

import java.util.List;

/**
 * <p>
 * 菜单信息表 服务类
 * </p>
 *
 * @author XL
 * @since 2022-01-18
 */
public interface MenuService extends IService<Menu> {

    /**
     *  获取所有菜单
     * @return List
     */
    List<Menu> getMenuAll();

    /**
     *  获取树形结构目录树
     * @return List
     */
    List<TreeList> getTreeMenu();

}
