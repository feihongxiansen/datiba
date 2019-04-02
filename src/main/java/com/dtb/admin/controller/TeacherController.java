package com.dtb.admin.controller;

import com.dtb.admin.service.TeacherService;
import com.dtb.entity.Teacher;
import com.dtb.entity.TeacherAssociation;
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
 * @create 2019/3/31-19:17
 */
@RequestMapping("/admin/teacher")
@Controller("teacherAdminController")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    /**
     * 教师认证申请列表页面渲染
     *
     * @return java.lang.String
     * @author lmx
     * @date 2019/3/31 20:31
     */
    @RequestMapping("/listPage")
    public String listPage() {
        return "admin/teacher/list";
    }

    /**
     * 模糊分页查询教师认证申请列表
     *
     * @param pageNum  当前页码
     * @param pageSize 每页显示条数
     * @param teacher  查询参数
     * @return com.dtb.utils.resulthandler.ResponseBean
     * @author lmx
     * @date 2019/3/31 21:20
     */
    @RequestMapping("/getPageTeacherList/{pageNum}/{pageSize}")
    @ResponseBody
    public ResponseBean getPageTeacherList(@PathVariable Integer pageNum,
                                           @PathVariable Integer pageSize,
                                           @RequestParam String vagueParam,
                                           Teacher teacher) {
        if (teacher == null) {
            return new ResponseBean(false, CommonErrorEnum.BAD_REQUEST);
        }
        PageHelper.startPage(pageNum, pageSize);
        Page<TeacherAssociation> pageList = teacherService.selectPageTeacherListVague(teacher, vagueParam);
        Map<String, Object> pageInfo = new HashMap<>();
        pageInfo.put("pageNum", pageList.getPageNum());
        pageInfo.put("pageSize", pageList.getPageSize());
        pageInfo.put("total", pageList.getTotal());
        pageInfo.put("pages", pageList.getPages());

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("pageInfo", pageInfo);
        resultMap.put("teacherList", pageList);
        return new ResponseBean<Map<String, Object>>(true, resultMap, CommonErrorEnum.SUCCESS_REQUEST);
    }

    /**
     * 根据id批量修改
     *
     * @param idList id数组
     * @param param  修改参数
     * @return com.dtb.utils.resulthandler.ResponseBean
     * @author lmx
     * @date 2019/3/31 23:11
     */
    @RequestMapping("/updateBatchByIds")
    @ResponseBody
    public ResponseBean updateBatchByIds(@RequestParam List<Integer> idList,
                                         Teacher param) {
        int result = teacherService.updateBatchByIds(idList, param);
        return new ResponseBean(true, CommonErrorEnum.SUCCESS_OPTION);
    }

    @RequestMapping("/editPage/{id}")
    public String editPage(@PathVariable Integer id,
                           Model model) {
        TeacherAssociation teacher = teacherService.selectById(id);
        model.addAttribute("teacher", teacher);
        return "/admin/teacher/edit";
    }
}
