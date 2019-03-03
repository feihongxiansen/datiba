package com.dtb.home.controller;

import com.dtb.home.service.QAService;
import com.dtb.utils.resulthandler.CommonErrorEnum;
import com.dtb.utils.resulthandler.ResponseBean;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author：lmx
 * @Description：问题搜索和解答相关业务类
 * @Date：Created on 16:31 2019/3/3.
 * @ModifyBy：
 */
@Controller("qAController")
@RequestMapping("home/question")
public class QAController {

    @Autowired
    private QAService qaService;

    /**
     * @auther: lmx
     * @date: 2019/3/3 18:26
     * @descript: 获取问题列表
     * @param: pageNum 分页参数，当前页码
     * @param: pageSize 分页参数，每页显示数量
     * @param: gradeId 年级查询条件，没有则查询全部年级
     * @param: subjectId 学科查询条件，没有则查询全部学科
     * @param: solveState 解决状态查询条件，没有则查询全部状态
     * @param: questionSummary 题目搜索内容
     * @param: needIntegral 是否需要积分，true需要，false不需要，没有则全部查询
     * @return: com.dtb.utils.resulthandler.ResponseBean<com.dtb.utils.resulthandler.CommonErrorEnum>
     */
    @RequestMapping("getQuestionList")
    @ResponseBody
    public ResponseBean<CommonErrorEnum>
        getQuestionList(@RequestParam(value = "pageNum",required = true,defaultValue = "0") Integer pageNum,
                        @RequestParam(value = "pageSize",required = true,defaultValue = "10") Integer pageSize,
                        @RequestParam(value = "gradeId",required = false)Integer gradeId,
                        @RequestParam(value = "subjectId",required = false)Integer subjectId,
                        @RequestParam(value = "solveState",required = false)Boolean solveState,
                        @RequestParam(value = "questionSummary",required = false)String questionSummary,
                        @RequestParam(value = "needIntegral",required = false)Boolean needIntegral){

        //启用分页查询
        PageHelper.startPage(pageNum,pageSize);


        return new ResponseBean(true,qaService.findQuestionList(),CommonErrorEnum.SUCCESS_REQUEST);
    }



}
