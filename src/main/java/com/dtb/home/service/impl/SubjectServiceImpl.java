package com.dtb.home.service.impl;

import com.dtb.entity.Subject;
import com.dtb.home.dao.SubjectMapper;
import com.dtb.home.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author：lmx
 * @Description：
 * @Date：Created on 16:52 2019/3/3.
 * @ModifyBy：
 */
@Service("subjectService")
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectMapper subjectMapper;

    @Override
    public List<Subject> findAll() {
        return subjectMapper.selectAll();
    }

    @Override
    public Subject findById(Integer id) {
        return subjectMapper.selectByPrimaryKey(id);
    }
}
