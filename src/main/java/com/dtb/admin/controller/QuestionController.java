package com.dtb.admin.controller;

import com.dtb.admin.service.AnswerService;
import com.dtb.admin.service.QuestionService;
import com.dtb.entity.QuestionsAssociation;
import com.dtb.entity.QuestionsWithBLOBs;
import com.dtb.home.service.GradeService;
import com.dtb.home.service.SubjectService;
import com.dtb.home.service.UserService;
import com.dtb.utils.resulthandler.CommonErrorEnum;
import com.dtb.utils.resulthandler.ResponseBean;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lmx
 * @version 1.0.0
 * @create 2019/4/2-19:23
 */
@Controller("questionAdminController")
@RequestMapping("/admin/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private GradeService gradeService;
    @Autowired
    private UserService userService;
    @Autowired
    private AnswerService answerService;

    /**
     * 问题列表页面渲染
     *
     * @param model 视图容器
     * @return java.lang.String
     * @author lmx
     * @date 2019/4/2 20:04
     */
    @RequestMapping("/listPage")
    public String listPage(Model model) {
        model.addAttribute("subjectList", subjectService.findAll());
        model.addAttribute("gradeList", gradeService.findAll());
        model.addAttribute("userList", userService.findUserList());
        return "admin/question/list";
    }

    /**
     * 多条件模糊分页查询
     *
     * @param question   搜索参数
     * @param vagueParam 模糊搜索参数
     * @param pageNum    当前页码
     * @param pageSize   每页显示数据量
     * @return com.dtb.utils.resulthandler.ResponseBean
     * @author lmx
     * @date 2019/4/2 20:04
     */
    @RequestMapping("getPageQuestionVague/{pageNum}/{pageSize}")
    @ResponseBody
    public ResponseBean getPageQuestionVague(QuestionsWithBLOBs question,
                                             @RequestParam String vagueParam,
                                             @PathVariable Integer pageNum,
                                             @PathVariable Integer pageSize) {
        if (question == null) {
            return new ResponseBean(false, CommonErrorEnum.BAD_REQUEST);
        }
        PageHelper.startPage(pageNum, pageSize);
        Page<QuestionsAssociation> pageList = questionService.selectPageQuestionVague(question, vagueParam);
        Map<String, Object> pageInfo = new HashMap<>();
        pageInfo.put("pageNum", pageList.getPageNum());
        pageInfo.put("pageSize", pageList.getPageSize());
        pageInfo.put("total", pageList.getTotal());
        pageInfo.put("pages", pageList.getPages());

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("pageInfo", pageInfo);
        resultMap.put("questionList", pageList);
        return new ResponseBean<Map<String, Object>>(true, resultMap, CommonErrorEnum.SUCCESS_REQUEST);
    }

    /**
     * 根据id数组批量修改
     *
     * @param idList id数组
     * @param param  修改参数
     * @return com.dtb.utils.resulthandler.ResponseBean
     * @author lmx
     * @date 2019/4/2 21:32
     */
    @RequestMapping("/updateBatchByIds")
    @ResponseBody
    public ResponseBean updateBatchByIds(@RequestParam List<Integer> idList,
                                         QuestionsWithBLOBs param) {
        int result = questionService.updateBatchByIds(idList, param);
        return new ResponseBean(true, CommonErrorEnum.SUCCESS_OPTION);
    }

    /**
     * 问题答案列表页面渲染
     *
     * @return java.lang.String
     * @author lmx
     * @date 2019/4/2 22:18
     */
    @RequestMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        model.addAttribute("question", this.findAnswerListById(id).getData());
        return "admin/question/detail";
    }

    /**
     * 根据问题id查询答案列表
     *
     * @param id 问题id
     * @return com.dtb.utils.resulthandler.ResponseBean
     * @author lmx
     * @date 2019/4/2 22:16
     */
    @RequestMapping("/findAnswerListById/{id}")
    @ResponseBody
    public ResponseBean findAnswerListById(@PathVariable Integer id) {
        QuestionsAssociation question = questionService.findAnswerListById(id);
        return new ResponseBean<QuestionsAssociation>(true, question, CommonErrorEnum.SUCCESS_REQUEST);
    }

    /**
     * 根据id删除答案
     *
     * @param id 答案id
     * @return com.dtb.utils.resulthandler.ResponseBean
     * @author lmx
     * @date 2019/4/2 23:07
     */
    @RequestMapping("/deleteAnswerById/{id}")
    @ResponseBody
    public ResponseBean deleteAnswerById(@PathVariable Integer id) {
        answerService.softDeleteById(id);
        return new ResponseBean(true, CommonErrorEnum.SUCCESS_OPTION);
    }
}
