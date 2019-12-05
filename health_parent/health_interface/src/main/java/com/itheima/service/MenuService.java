package com.itheima.service;

import com.itheima.pojo.Menu;
import com.itheima.pojo.User;

import java.util.List;

public interface MenuService {
    public List<Menu> findMenuByUserName(String username);
}
