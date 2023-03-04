package com.xl.demo.service.impl;

import com.xl.demo.domain.Menu;
import com.xl.demo.domain.vo.TreeList;
import com.xl.demo.mapper.MenuMapper;
import com.xl.demo.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xl.demo.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 菜单信息表 服务实现类
 * </p>
 *
 * @author XL
 * @since 2022-01-18
 */
@Service
@Slf4j
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired(required = false)
    private MenuMapper menuMapper;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public List<Menu> getMenuAll() {
        List<Menu> menus = new ArrayList<>();
        List<Menu> nodes = menuMapper.selectList(null);
        for (Menu menu:nodes){
            if(menu.getParentId()==0){
                menus.add(getChildrenMenu(menu,nodes));
            }
        }
        return menus;
    }

    @Override
    public List<TreeList> getTreeMenu() {
        List<TreeList> menus = null;
        try{
            if(redisUtils.hasKey("menu")){
                menus = redisUtils.getCacheList("menu");
            }else{
                List<TreeList> list  = getTreeMenuFromData();
                redisUtils.setCacheList("menu",list);
                menus = list;
            }
        }catch (RedisConnectionFailureException e){
            log.info("没有可用的redis！");
            menus = getTreeMenuFromData();
            return menus;
        }
        return menus;
    }

    private List<TreeList> getTreeMenuFromData(){
        List<Menu> menus = menuMapper.selectList(null);
        List<TreeList> trees = changeList(menus);
        List<TreeList> res = new ArrayList<>();
        for(TreeList tree:trees){
            if(tree.getParentId() == 0){
                res.add(getChildren(tree,trees));
            }
        }
        return res;
    }

    /**
     * 转换转换菜单结构
     */
    private List<TreeList> changeList(List<Menu> menus){
        List<TreeList> trees = new ArrayList<>();
        for (Menu menu:menus){
            TreeList treeList = new TreeList();
            treeList.setId(menu.getMenuId());
            treeList.setLabel(menu.getMenuName());
            treeList.setParentId(menu.getParentId());
            trees.add(treeList);
        }
        if(trees.size()<=0){
            trees = null;
        }
        return trees;
    }

    /**
     * 获取子菜单
     */
    private TreeList getChildren(TreeList node,List<TreeList> nodes){
        for(TreeList tree: nodes){
            if(tree.getParentId()==node.getId()){
                if(node.getChildren()==null){
                    node.setChildren(new ArrayList<TreeList>());
                }
                node.getChildren().add(getChildren(tree,nodes));
            }
        }
        return node;
    }

    private Menu getChildrenMenu(Menu node,List<Menu> nodes){
        for (Menu menu:nodes){
            if(menu.getParentId()==node.getMenuId()){
                if(node.getMenus()==null){
                    node.setMenus(new ArrayList<Menu>());
                }
                node.getMenus().add(getChildrenMenu(menu,nodes));
            }
        }
        return node;
    }



}
