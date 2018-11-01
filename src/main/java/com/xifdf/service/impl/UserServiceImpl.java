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
        if(user.getUsername().equals(ackuser.getUsername())
        && user.getPassword().equals(ackuser.getPassword())) {
            return true;
        }
        else return false;
    }
}
