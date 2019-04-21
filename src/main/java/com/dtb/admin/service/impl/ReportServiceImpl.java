package com.dtb.admin.service.impl;

import com.dtb.admin.dao.ReportMapper;
import com.dtb.admin.service.ReportService;
import com.dtb.entity.Report;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lmx
 * @version 1.0.0
 * @create 2019/4/21-22:59
 */
@Service("reportAdminService")
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportMapper reportMapper;

    @Override
    public Integer updateById(Report param) {
        return reportMapper.updateById(param);
    }

    @Override
    public Page<Report> getPageByParam(Report param) {
        return reportMapper.selectPageByParam(param);
    }
}
