package com.dtb.home.controller;

import com.dtb.entity.Report;
import com.dtb.home.service.ReportService;
import com.dtb.utils.resulthandler.CommonErrorEnum;
import com.dtb.utils.resulthandler.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lmx
 * @version 1.0.0
 * @create 2019/4/21-22:21
 */
@RequestMapping("/home/report")
@Controller("reportController")
public class ReportController {

    @Autowired
    private ReportService reportService;

    /**
     * 添加举报
     *
     * @param report 参数
     * @return com.dtb.utils.resulthandler.ResponseBean
     * @author lmx
     * @date 2019/4/21 22:27
     */
    @RequestMapping("/addReport")
    @ResponseBody
    public ResponseBean addReport(Report report) {
        reportService.addReport(report);
        return new ResponseBean(true, CommonErrorEnum.SUCCESS_OPTION);
    }
}
