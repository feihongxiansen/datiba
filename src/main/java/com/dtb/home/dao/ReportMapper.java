package com.dtb.home.dao;

import com.dtb.entity.Report;

public interface ReportMapper {

    /**
     * 添加举报
     *
     * @param record 添加举报
     * @return java.lang.Integer
     * @author lmx
     * @date 2019/4/21 22:24
     */
    Integer insertSelective(Report record);

    Report selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Report record);
}