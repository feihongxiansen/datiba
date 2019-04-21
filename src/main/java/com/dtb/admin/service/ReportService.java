package com.dtb.admin.service;

import com.dtb.entity.Report;
import com.github.pagehelper.Page;

/**
 * @author lmx
 * @version 1.0.0
 * @create 2019/4/21-22:59
 */
public interface ReportService {

    /**
     * 根据id修改
     *
     * @param param 参数
     * @return java.lang.Integer
     * @author lmx
     * @date 2019/4/21 23:04
     */
    Integer updateById(Report param);

    /**
     * 多条件查询
     *
     * @param param 参数
     * @return com.github.pagehelper.Page<com.dtb.entity.Report>
     * @author lmx
     * @date 2019/4/21 23:05
     */
    Page<Report> getPageByParam(Report param);
}
