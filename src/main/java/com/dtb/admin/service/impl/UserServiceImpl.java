package com.dtb.admin.service.impl;

import com.dtb.admin.dao.UserMapper;
import com.dtb.admin.service.UserService;
import com.dtb.entity.User;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lmx
 * @version 1.0.0
 * @create 2019/3/31-9:48
 */
@Service("userAdminService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Page<User> selectPageUserList(User queryParam) {
        return userMapper.selectPageUserList(queryParam);
    }

    @Override
    public Page<User> selectPageUserListVague(User queryParam, String vagueParam) {
        return userMapper.selectPageUserListVague(queryParam, vagueParam);
    }

    @Override
    public Integer updateByIdSelective(User param) {
        return userMapper.updateByIdSelective(param);
    }

    @Override
    public User findById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public Integer updateBatchByIds(List<Integer> idList, User param) {
        return userMapper.updateBatchByIds(idList, param);
    }
}
