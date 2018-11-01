package com.xifdf.dao.impl;

import com.xifdf.bean.User;
import com.xifdf.dao.UserDao;

public class UserDaoImpl implements UserDao {

    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }
}
