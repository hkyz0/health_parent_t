package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.MenuDao;
import com.itheima.dao.PermissionDao;
import com.itheima.dao.RoleDao;
import com.itheima.dao.UserDao;
import com.itheima.pojo.Menu;
import com.itheima.pojo.Permission;
import com.itheima.pojo.Role;
import com.itheima.pojo.User;
import com.itheima.service.MenuService;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 用户服务
 */
@Service(interfaceClass = MenuService.class)
@Transactional
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    public List<Menu> findMenuByUserName(String username) {
        //查询出所有的菜单
        List<Menu> lstMenu = menuDao.findMenuByUserName(username);
        List<Menu> newLstMenu = new ArrayList<>();
        for(Menu menu : lstMenu){
            //判断是否根节点，如果是根节点，我们给他设置属性和子节点
            if(menu.getParentMenuId() == null){
                Menu newMenu = new Menu();
                newMenu.setPath(menu.getPath());
                newMenu.setIcon(menu.getIcon());
                newMenu.setLinkUrl(menu.getLinkUrl());
                newMenu.setName(menu.getName());
                newMenu.setChildren(getChildMenu(lstMenu,menu));
                newLstMenu.add(newMenu);
            }
        }
        return newLstMenu;
    }

    private List<Menu> getChildMenu(List<Menu> lstMenu,Menu menu){
        List<Menu> lstChildMenu = new ArrayList<>();
        for (Menu m : lstMenu){
            //判断是否为根节点的下级节点
            if(m.getParentMenuId() == menu.getId()){
                Menu newChildMenu = new Menu();
                newChildMenu.setPath(m.getPath());
                newChildMenu.setIcon(m.getIcon());
                newChildMenu.setLinkUrl(m.getLinkUrl());
                newChildMenu.setName(m.getName());
                //采用递归方式，不断的往下循环出他的孙子节点
                newChildMenu.setChildren(getChildMenu(lstMenu,m));
                lstChildMenu.add(newChildMenu);
            }
        }
        return lstChildMenu;
    }
}
