package com.dtb.home.controller;

import com.dtb.entity.Grade;
import com.dtb.home.service.GradeService;
import com.dtb.utils.resulthandler.CommonErrorEnum;
import com.dtb.utils.resulthandler.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author：lmx
 * @Description：年级操作相关类
 * @Date：Created on 20:32 2019/3/4.
 * @ModifyBy：
 */
@Controller("gradeController")
@RequestMapping("/home/grade")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    /**
     * @auther: lmx
     * @date: 2019/3/4 20:38
     * @descript: 获取年级列表
     * @param:
     * @return: com.dtb.utils.resulthandler.ResponseBean<com.dtb.utils.resulthandler.CommonErrorEnum>
     */
    @RequestMapping("/getGradeList")
    @ResponseBody
    public ResponseBean<CommonErrorEnum> getGradeList(){

        List<Grade> gradeList = gradeService.findAll();
        return new ResponseBean(true,gradeList,CommonErrorEnum.SUCCESS_REQUEST);
    }
}
