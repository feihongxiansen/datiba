package com.dtb.admin.service.impl;

import com.dtb.admin.dao.TeacherMapper;
import com.dtb.admin.service.TeacherService;
import com.dtb.entity.Teacher;
import com.dtb.entity.TeacherAssociation;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lmx
 * @version 1.0.0
 * @create 2019/3/31-19:19
 */
@Service("teacherAdminService")
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public Page<TeacherAssociation> selectPageTeacherListVague(Teacher queryParam, String vagueParam) {
        Page<TeacherAssociation> pageList = teacherMapper.selectPageTeacherListVague(queryParam, vagueParam);
        for (TeacherAssociation teacher : pageList.getResult()) {
            String photoStr = teacher.getPaperworkPhotos();
            String[] photoArray = photoStr == null || photoStr == "" ? null : photoStr.split(",");
            teacher.setPaperworkPhotoArray(photoArray);
        }
        return pageList;
    }

    @Override
    public Integer updateBatchByIds(List<Integer> idList, Teacher param) {
        return teacherMapper.updateBatchByIds(idList, param);
    }

    @Override
    public TeacherAssociation selectById(Integer id) {
        TeacherAssociation teacher = teacherMapper.selectById(id);
        String photoStr = teacher.getPaperworkPhotos();
        String[] photoArray = photoStr == null || photoStr == "" ? null : photoStr.split(",");
        teacher.setPaperworkPhotoArray(photoArray);
        return teacher;
    }

}
