package com.dtb.admin.controller;

import com.dtb.admin.service.ReportService;
import com.dtb.entity.Report;
import com.dtb.utils.resulthandler.CommonErrorEnum;
import com.dtb.utils.resulthandler.ResponseBean;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lmx
 * @version 1.0.0
 * @create 2019/4/21-22:57
 */
@RequestMapping("/admin/report")
@Controller("reportAdminController")
public class ReportController {

    @Autowired
    private ReportService reportService;

    /**
     * 举报列表页面
     *
     * @return java.lang.String
     * @author lmx
     * @date 2019/4/21 23:17
     */
    @RequestMapping("/listPage")
    public String listPage() {
        return "admin/report/list";
    }

    /**
     * 多条件分页搜索
     *
     * @param pageNum  当前页码
     * @param pageSize 每页数据量
     * @param report   参数
     * @return com.dtb.utils.resulthandler.ResponseBean
     * @author lmx
     * @date 2019/4/21 23:20
     */
    @RequestMapping("/getPageReport/{pageNum}/{pageSize}")
    @ResponseBody
    public ResponseBean getPageReport(@PathVariable Integer pageNum,
                                      @PathVariable Integer pageSize,
                                      Report report) {
        PageHelper.startPage(pageNum, pageSize);
        Page<Report> pageList = reportService.getPageByParam(report);

        //分页信息
        Map<String, Object> pageInfo = new HashMap<>();
        pageInfo.put("pageNum", pageList.getPageNum());
        pageInfo.put("pageSize", pageList.getPageSize());
        pageInfo.put("total", pageList.getTotal());
        pageInfo.put("pages", pageList.getPages());

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("pageInfo", pageInfo);
        resultMap.put("reportList", pageList);
        return new ResponseBean<Map<String, Object>>(true, resultMap, CommonErrorEnum.SUCCESS_REQUEST);
    }

    /**
     * 根据id修改
     *
     * @param report 参数
     * @return com.dtb.utils.resulthandler.ResponseBean
     * @author lmx
     * @date 2019/4/21 23:44
     */
    @RequestMapping("/updateById")
    @ResponseBody
    public ResponseBean updateById(Report report) {
        reportService.updateById(report);
        return new ResponseBean(true, CommonErrorEnum.SUCCESS_OPTION);
    }

}
