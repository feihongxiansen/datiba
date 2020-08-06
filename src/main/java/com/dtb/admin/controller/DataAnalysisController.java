package com.dtb.admin.controller;

import com.dtb.admin.service.DataAnalysisService;
import com.dtb.entity.AnswersWithBLOBs;
import com.dtb.entity.QuestionsWithBLOBs;
import com.dtb.entity.User;
import com.dtb.utils.resulthandler.CommonErrorEnum;
import com.dtb.utils.resulthandler.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author lmx
 * @version 1.0.0
 * @create 2019/4/5-11:07
 */
@Controller("dataAnalysisAdminController")
@RequestMapping("/admin/dataAnalysis")
public class DataAnalysisController {

    @Autowired
    private DataAnalysisService dataAnalysisService;

    /**
     * @param groupType 查询规则：ALL-所有，TODAY-今天，YESTERDAY-昨天，THISWEEK-本周，THISMONTH-本月
     * @return com.dtb.utils.resulthandler.ResponseBean
     * @author lmx
     * @date 2019/4/5 12:31
     */
    @RequestMapping("/overallStatistics")
    @ResponseBody
    public ResponseBean overallStatistics(@RequestParam String groupType) {
        Map<String, Object> resultMap = dataAnalysisService.overallStatistics(groupType);
        return new ResponseBean<>(true, resultMap, CommonErrorEnum.SUCCESS_REQUEST);
    }

    /**
     * 问题统计页面渲染
     *
     * @return java.lang.String
     * @author lmx
     * @date 14:14
     */
    @RequestMapping("/questionPage")
    public String questionPage() {
        return "admin/echarts/question";
    }

    /**
     * 用户统计页面渲染
     *
     * @return java.lang.String
     * @author lmx
     * @date 14:14
     */
    @RequestMapping("/userPage")
    public String userPage() {
        return "admin/echarts/user";
    }

    /**
     * 查询统计提问相关的数据
     *
     * @param startDate 开始日期
     * @param endDate   截止日期
     * @param groupType 统计条件：DAY-按天统计，WEEK-安周统计，MONTH-按月统计，QUARTER-按季度统计，YEAR-按年统计
     * @return com.dtb.utils.resulthandler.ResponseBean
     * @author lmx
     * @date 2019/4/5 14:25
     */
    @RequestMapping(value = "/getQuestionCount", method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean getQuestionCount(@RequestParam(required = false) Date startDate,
                                         @RequestParam(required = false) Date endDate,
                                         @RequestParam(required = false) String groupType,
                                         QuestionsWithBLOBs param) {
        List<Map<String, Object>> resultMap =
                dataAnalysisService.selectQuestionByStateCount(startDate, endDate, groupType, param);
        return new ResponseBean<>(true, resultMap, CommonErrorEnum.SUCCESS_REQUEST);
    }

    /**
     * 查询统计解答相关的数据
     *
     * @param startDate 开始日期
     * @param endDate   截止日期
     * @param groupType 统计条件：DAY-按天统计，WEEK-安周统计，MONTH-按月统计，QUARTER-按季度统计，YEAR-按年统计
     * @return com.dtb.utils.resulthandler.ResponseBean
     * @author lmx
     * @date 2019/4/5 14:25
     */
    @RequestMapping(value = "/getAnswerCount")
    @ResponseBody
    public ResponseBean getAnswerCount(@RequestParam(required = false) Date startDate,
                                       @RequestParam(required = false) Date endDate,
                                       @RequestParam(required = false) String groupType,
                                       AnswersWithBLOBs param) {
        List<Map<String, Object>> resultMap =
                dataAnalysisService.selectAnswerByStateCount(startDate, endDate, groupType, param);
        return new ResponseBean<>(true, resultMap, CommonErrorEnum.SUCCESS_REQUEST);
    }

    /**
     * 统计用户相关数据
     *
     * @param user 查询参数
     * @return com.dtb.utils.resulthandler.ResponseBean
     * @author lmx
     * @date 2019/4/5 22:41
     */
    @RequestMapping("/getUserCount")
    @ResponseBody
    public ResponseBean getUserCount(User user) {
        List<Map<String, Object>> resultMap = dataAnalysisService.selectUserCount(user);
        return new ResponseBean<>(true, resultMap, CommonErrorEnum.SUCCESS_REQUEST);
    }
}
