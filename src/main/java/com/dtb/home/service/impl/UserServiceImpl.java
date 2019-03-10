package com.dtb.home.service.impl;

import com.dtb.entity.User;
import com.dtb.home.dao.UserMapper;
import com.dtb.home.service.UserService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author：lmx
 * @Description：
 * @Date：Created on 14:34 2019/2/25.
 * @ModifyBy：
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByPhone(String phone) {
        return userMapper.selectByPhone(phone);
    }

    @Override
    public User findByEmail(String email) {
        return userMapper.selectByEmail(email);
    }

    @Override
    public int createUser(User user) {
        return userMapper.insertSelective(user);
    }

    @Override
    public User findById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByIdSelective(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public List<User> findUserList() {
        return userMapper.selectUserList();
    }

    @Override
    public Page<User> findUserListToLimit(Byte userType) {
        return userMapper.selectUserListToLimit(userType);
    }
}
