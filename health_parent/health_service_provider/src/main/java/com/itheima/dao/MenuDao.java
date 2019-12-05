package com.itheima.dao;

import com.itheima.pojo.Menu;
import com.itheima.pojo.User;

import java.util.List;

public interface MenuDao {
    public List<Menu> findMenuByUserName(String username);
}
