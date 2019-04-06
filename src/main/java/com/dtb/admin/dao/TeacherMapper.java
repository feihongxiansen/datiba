package com.dtb.admin.dao;

import com.dtb.entity.Teacher;
import com.dtb.entity.TeacherAssociation;
import com.dtb.entity.User;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lmx
 * @version 1.0.0
 * @create 2019/3/31-19:18
 */
@Repository("teacherAdminMapper")
public interface TeacherMapper {

    /**
     * 模糊查询教师认证申请列表
     *
     * @param vagueParam 模糊查询参数
     * @param queryParam 查询参数
     * @return com.github.pagehelper.Page<java.util.List < com.dtb.entity.TeacherAssociation>>
     * @author lmx
     * @date 2019/3/31 20:39
     */
    Page<TeacherAssociation> selectPageTeacherListVague(@Param("queryParam") Teacher queryParam, @Param("vagueParam") String vagueParam);

    /**
     * 根据id批量修改
     *
     * @param idList id数组
     * @param param  修改参数
     * @return java.lang.Integer
     * @author lmx
     * @date 2019/3/31 23:05
     */
    Integer updateBatchByIds(@Param("idList") List<Integer> idList, @Param("param") Teacher param);

    /**
     * 根据id查找
     *
     * @param id 主键
     * @return com.dtb.entity.TeacherAssociation
     * @author lmx
     * @date 2019/4/1 20:15
     */
    TeacherAssociation selectById(@Param("id") Integer id);

    /**
     * 根据审核申请查询id数组查询用户
     *
     * @param idList 主键
     * @return java.util.List<com.dtb.entity.User>
     * @author lmx
     * @date 2019/4/6 14:43
     */
    List<User> selectUserByTeacherIds(@Param("idList") List<Integer> idList);
}
