package com.dtb.home.controller;

import com.dtb.entity.Questions;
import com.dtb.entity.QuestionsAssociation;
import com.dtb.home.service.QAService;
import com.dtb.utils.resulthandler.CommonErrorEnum;
import com.dtb.utils.resulthandler.ResponseBean;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

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
     * @param: questionSummary 题目搜索内容
     * @param: needIntegral 是否需要积分
     * @return: com.dtb.utils.resulthandler.ResponseBean<com.dtb.utils.resulthandler.CommonErrorEnum>
     */
    @RequestMapping("getQuestionList")
    @ResponseBody
    public ResponseBean<CommonErrorEnum>
        getQuestionList(@RequestParam(value = "pageNum",required = true,defaultValue = "0") Integer pageNum,
                        @RequestParam(value = "pageSize",required = true,defaultValue = "10") Integer pageSize,
                        @RequestParam(value = "gradeId",required = false)Integer gradeId,
                        @RequestParam(value = "subjectId",required = false)Integer subjectId,
                        @RequestParam(value = "questionSummary",required = false)String questionSummary,
                        @RequestParam(value = "needIntegral",required = false)Boolean needIntegral){

        //启用分页查询
        PageHelper.startPage(pageNum,pageSize);
        Page<Questions> page = qaService.findQuestionList(gradeId,subjectId,questionSummary,needIntegral);

        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("list",page);
        Map<String,Object> pageMap = new HashMap<String,Object>();
        pageMap.put("total",page.getTotal());
        pageMap.put("pageNum",page.getPageNum());
        pageMap.put("pageSize",page.getPageSize());
        pageMap.put("pages",page.getPages());
        resultMap.put("pageInfo",pageMap);

        System.out.println(resultMap);

        System.out.println("pageNum:"+pageNum+"-pageSize:"+pageSize+"-gradeId:"+gradeId+
                "-subjectId:"+subjectId+"-needIntegral:"+needIntegral+"-questionSummary:"+questionSummary);
        return new ResponseBean(true,resultMap,CommonErrorEnum.SUCCESS_REQUEST);
    }


    /**
     * @auther: lmx
     * @date: 2019/3/6 23:32
     * @descript: 根据问题id获取答案列表
     * @param: questionId
     * @param: pageNum
     * @param: pageSize
     * @return: com.dtb.utils.resulthandler.ResponseBean<com.dtb.utils.resulthandler.CommonErrorEnum>
     */
    @RequestMapping("getAnswerList")
    @ResponseBody
    public ResponseBean<CommonErrorEnum> getAnswerList(@RequestParam("questionId") Integer questionId,
                                                       @RequestParam(value = "pageNum",required = false,defaultValue = "0") Integer pageNum,
                                                       @RequestParam(value = "pageSize",required = false,defaultValue = "10") Integer pageSize){

        PageHelper.startPage(pageNum,pageSize);

        Page<QuestionsAssociation> pageInfo = qaService.findAnswerList(questionId);

        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("list",pageInfo);
        Map<String,Object> pageMap = new HashMap<String,Object>();
        pageMap.put("total",pageInfo.getTotal());
        pageMap.put("pageNum",pageInfo.getPageNum());
        pageMap.put("pageSize",pageInfo.getPageSize());
        pageMap.put("pages",pageInfo.getPages());
        resultMap.put("pageInfo",pageMap);

        return new ResponseBean(true,resultMap,CommonErrorEnum.SUCCESS_REQUEST);
    }



}
