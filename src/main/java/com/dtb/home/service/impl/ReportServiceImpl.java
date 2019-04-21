package com.dtb.home.service.impl;

import com.dtb.entity.Report;
import com.dtb.home.dao.ReportMapper;
import com.dtb.home.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lmx
 * @version 1.0.0
 * @create 2019/4/21-22:23
 */
@Service("reportService")
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportMapper reportMapper;

    @Override
    public Integer addReport(Report report) {
        return reportMapper.insertSelective(report);
    }
}
