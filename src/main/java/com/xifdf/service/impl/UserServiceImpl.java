package com.xifdf.service.impl;

import com.xifdf.bean.User;
import com.xifdf.dao.UserDao;
import com.xifdf.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean login(User user) {
        User ackuser = userDao.getUser();
        if(user.getUsername().equals("admin") && user.getPassword().equals("testpassword")) {
            return true;
        }
        else return false;
    }
}
