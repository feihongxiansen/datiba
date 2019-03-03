package com.dtb.home.dao;

import com.dtb.entity.Grade;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GradeMapper {

    /**
     * @auther: lmx
     * @date: 2019/3/3 16:57
     * @descript: 查询所有年级信息
     * @param:
     * @return: java.util.List<com.dtb.entity.Grade>
     */
    List<Grade> selectAll();

    int deleteByPrimaryKey(Integer id);

    int insert(Grade record);

    int insertSelective(Grade record);

    /**
     * @auther: lmx
     * @date: 2019/3/3 16:57
     * @descript: 根据id查询年级信息
     * @param: id 年级id
     * @return: com.dtb.entity.Grade
     */
    Grade selectByPrimaryKey(@Param("id") Integer id);

    int updateByPrimaryKeySelective(Grade record);

    int updateByPrimaryKey(Grade record);
}