package com.dtb.home.service.impl;

import com.dtb.entity.Teacher;
import com.dtb.entity.TeacherAssociation;
import com.dtb.home.dao.TeacherMapper;
import com.dtb.home.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author：lmx
 * @Description：
 * @Date：Created on 16:23 2019/3/17.
 * @ModifyBy：
 */
@Service("teacherService")
public class TeacherServiceImpl implements TeacherService{

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public int addTeacherAuth(Teacher teacher) {
        return teacherMapper.insertSelective(teacher);
    }

    @Override
    public TeacherAssociation findByUserId(Integer userId) {

        TeacherAssociation teacher = teacherMapper.selectByUserId(userId);

        //把审核图片字符串转为数组返回
        if (teacher!=null && teacher.getPaperworkPhotos()!=null){
            teacher.setPaperworkPhotoArray(teacher.getPaperworkPhotos().split(","));
        }

        return teacher;
    }
}
