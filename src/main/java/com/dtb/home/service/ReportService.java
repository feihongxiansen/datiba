package com.dtb.home.service;

import com.dtb.entity.Report;

/**
 * @author lmx
 * @version 1.0.0
 * @create 2019/4/21-22:22
 */
public interface ReportService {

    /**
     * 添加举报
     *
     * @param report 参数
     * @return java.lang.Integer
     * @author lmx
     * @date 2019/4/21 22:23
     */
    Integer addReport(Report report);
}
