package com.dtb.home.dao;

import com.dtb.entity.Teacher;
import com.dtb.entity.TeacherAssociation;
import org.apache.ibatis.annotations.Param;

public interface TeacherMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Teacher record);

    /**
     * @auther lmx
     * @date 2019/3/17 16:24
     * @descript 添加教师认证信息
     * @param record 教师实体类
     * @return int 返回受影响行数
     */
    int insertSelective(Teacher record);

    /**
     * @auther lmx
     * @date 2019/3/17 16:56
     * @descript 根据用户id查询申请认证状态
     * @param userId
     * @return com.dtb.entity.TeacherAssociation
     */
    TeacherAssociation selectByUserId(@Param("userId") Integer userId);

    /**
     * @auther lmx
     * @date 2019/3/17 17:06
     * @descript 根据主键查询
     * @param id 申请认证主键
     * @return com.dtb.entity.TeacherAssociation
     */
    TeacherAssociation selectByPrimaryKey(@Param("id") Integer id);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKeyWithBLOBs(Teacher record);

    int updateByPrimaryKey(Teacher record);
}