package com.dtb.home.dao;

import com.dtb.entity.Teacher;

public interface TeacherMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKeyWithBLOBs(Teacher record);

    int updateByPrimaryKey(Teacher record);
}