package com.dtb.home.service;

import com.dtb.entity.Teacher;
import com.dtb.entity.TeacherAssociation;

/**
 * @Author：lmx
 * @Description：教师认证相关类
 * @Date：Created on 16:23 2019/3/17.
 * @ModifyBy：
 */
public interface TeacherService {

    /**
     * @auther lmx
     * @date 2019/3/17 16:24
     * @descript 添加教师认证信息
     * @param teacher 教师实体类
     * @return int 返回受影响行数
     */
    int addTeacherAuth(Teacher teacher);

    /**
     * @auther lmx
     * @date 2019/3/17 16:54
     * @descript 根据用户ID查询申请认证状态
     * @param userId
     * @return com.dtb.entity.TeacherAssociation
     */
    TeacherAssociation findByUserId(Integer userId);
}
