package com.dtb.home.controller;

import com.dtb.entity.Subject;
import com.dtb.home.service.SubjectService;
import com.dtb.utils.resulthandler.CommonErrorEnum;
import com.dtb.utils.resulthandler.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author：lmx
 * @Description：学科操作相关控制器
 * @Date：Created on 20:38 2019/3/4.
 * @ModifyBy：
 */
@Controller("subjectController")
@RequestMapping("home/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    /**
     * @auther: lmx
     * @date: 2019/3/4 20:43
     * @descript: 获取学科信息列表
     * @param:
     * @return: com.dtb.utils.resulthandler.ResponseBean<com.dtb.utils.resulthandler.CommonErrorEnum>
     */
    @RequestMapping("getSubjectList")
    @ResponseBody
    public ResponseBean<CommonErrorEnum> getSubjectList(){

        List<Subject> subjectList = subjectService.findAll();

        return new ResponseBean(true,subjectList,CommonErrorEnum.SUCCESS_REQUEST);
    }

}
