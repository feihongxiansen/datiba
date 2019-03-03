package com.dtb.home.service.impl;

import com.dtb.entity.Grade;
import com.dtb.home.dao.GradeMapper;
import com.dtb.home.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author：lmx
 * @Description：
 * @Date：Created on 16:55 2019/3/3.
 * @ModifyBy：
 */
@Service("gradeService")
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeMapper gradeMapper;

    @Override
    public List<Grade> findAll() {
        return gradeMapper.selectAll();
    }

    @Override
    public Grade findById(Integer id) {
        return gradeMapper.selectByPrimaryKey(id);
    }
}
