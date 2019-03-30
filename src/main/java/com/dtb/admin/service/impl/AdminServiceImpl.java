package com.dtb.admin.service.impl;

import com.dtb.admin.dao.AdminMapper;
import com.dtb.admin.service.AdminService;
import com.dtb.entity.Admin;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lmx
 * @version 1.0.0
 * @create 2019/3/30-13:57
 */
@Service("adminAdminService")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin findOne(Admin admin) {
        return adminMapper.selectOne(admin);
    }

    @Override
    public Page<List<Admin>> findPageAdminList(Admin admin) {
        if (null == admin) {
            admin = new Admin();
        }
        return adminMapper.selectPageAdminList(admin);
    }

    @Override
    public Page<List<Admin>> findPageAdminListByVague(Boolean loginState, String param) {
        return adminMapper.selectPageAdminListByVague(loginState, param);
    }

    @Override
    public Integer updateByIdSelective(Admin admin) {
        return adminMapper.updateByPrimaryKeySelective(admin);
    }

    @Override
    public Admin findById(Integer id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer addAdmin(Admin admin) {
        return adminMapper.insertSelective(admin);
    }
}
