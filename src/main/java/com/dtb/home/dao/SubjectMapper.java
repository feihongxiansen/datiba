package com.dtb.home.dao;

import com.dtb.entity.Subject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SubjectMapper {

    /**
     * @auther: lmx
     * @date: 2019/3/3 16:47
     * @descript: 查询所有学科分类
     * @param:
     * @return: java.util.List<com.dtb.entity.Subject>
     */
    List<Subject> selectAll();

    int deleteByPrimaryKey(@Param("id") Integer id);

    int insert(Subject record);

    int insertSelective(Subject record);

    /**
     * @auther: lmx
     * @date: 2019/3/3 16:50
     * @descript: 根据主键id查询学科类别
     * @param: id 学科id
     * @return: com.dtb.entity.Subject
     */
    Subject selectByPrimaryKey(@Param("id") Integer id);

    int updateByPrimaryKeySelective(Subject record);

    int updateByPrimaryKey(Subject record);
}