package com.dtb.home.service;

import com.dtb.entity.Grade;

import java.util.List;

/**
 * @Author：lmx
 * @Description：
 * @Date：Created on 16:55 2019/3/3.
 * @ModifyBy：
 */
public interface GradeService {

    /**
     * @auther: lmx
     * @date: 2019/3/3 16:59
     * @descript: 查询所有年级信息
     * @param:
     * @return: java.util.List<com.dtb.entity.Grade>
     */
    List<Grade> findAll();

    /**
     * @auther: lmx
     * @date: 2019/3/3 17:00
     * @descript: 根据id查询年级信息
     * @param: id 年级id
     * @return: com.dtb.entity.Grade
     */
    Grade findById(Integer id);
}
