package com.xifdf.dao.impl;

import com.xifdf.bean.User;
import com.xifdf.dao.UserDao;

public class UserDaoImpl implements UserDao {

    private User user = new User(1, "admin", "testpassword");


    public User getUser() {
        return this.user;
    }
}
