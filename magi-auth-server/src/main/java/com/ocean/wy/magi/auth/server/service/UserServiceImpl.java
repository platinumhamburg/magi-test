package com.ocean.wy.magi.auth.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ocean.wy.magi.auth.server.dao.UserDao;
import com.ocean.wy.magi.auth.server.entity.User;

import java.util.*;

/**
 * <p>User: Ocean.wy
 * <p>Date: 16-1-28
 * <p>Version: 1.0
 */
@Service

public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private PasswordHelper passwordHelper;
    @Autowired
    private RoleService roleService;

    /**
     * 创建用户
     * @param user
     */
    public User createUser(User user) {
        //加密密码
        passwordHelper.encryptPassword(user);
        return userDao.createUser(user);
    }

    

    /**
     * 修改密码
     * @param userId
     * @param newPassword
     */
    public void changePassword(Long userId, String newPassword) {
        User user =userDao.findOne(userId);
        user.setPassword(newPassword);
        passwordHelper.encryptPassword(user);
        userDao.updateUser(user);
    }


    


    public User updateUser(User user) {
        return userDao.updateUser(user);
    }


    public void deleteUser(Long userId) {
        userDao.deleteUser(userId);
    }
    
    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }



	public User findOne(Long userId) {
		// TODO Auto-generated method stub
		return userDao.findOne(userId);
	}



	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userDao.findAll();
	}


}
