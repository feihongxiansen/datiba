package com.dtb.admin.controller;

import com.dtb.admin.service.FeedbackService;
import com.dtb.entity.FeedbackAssociation;
import com.dtb.entity.FeedbackWithBLOBs;
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
 * @create 2019/4/3-21:05
 */
@Controller("feedbackAdminController")
@RequestMapping("/admin/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    /**
     * 反馈列表页面
     *
     * @return java.lang.String
     * @author lmx
     * @date 2019/4/3 21:19
     */
    @RequestMapping("/listPage")
    public String listPage() {
        return "admin/feedback/list";
    }

    /**
     * 多条件分页模糊搜索
     *
     * @param pageNum    当前页码
     * @param pageSize   每页显示数据总数
     * @param vagueParam 模糊搜索条件
     * @param feedback   搜索条件
     * @return com.dtb.utils.resulthandler.ResponseBean
     * @author lmx
     * @date 2019/4/3 22:45
     */
    @RequestMapping("/getPageFeedbackList/{pageNum}/{pageSize}")
    @ResponseBody
    public ResponseBean getPageFeedbackList(@PathVariable Integer pageNum,
                                            @PathVariable Integer pageSize,
                                            @RequestParam String vagueParam,
                                            FeedbackWithBLOBs feedback) {
        if (feedback == null) {
            return new ResponseBean(false, CommonErrorEnum.BAD_REQUEST);
        }
        PageHelper.startPage(pageNum, pageSize);
        Page<FeedbackAssociation> pageList = feedbackService.selectPageFeedbackListVague(feedback, vagueParam);
        Map<String, Object> pageInfo = new HashMap<>();
        pageInfo.put("pageNum", pageList.getPageNum());
        pageInfo.put("pageSize", pageList.getPageSize());
        pageInfo.put("total", pageList.getTotal());
        pageInfo.put("pages", pageList.getPages());

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("pageInfo", pageInfo);
        resultMap.put("feedbackList", pageList);
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
                                         FeedbackWithBLOBs param) {
        int result = feedbackService.updateBatchByIds(idList, param);
        return new ResponseBean(true, CommonErrorEnum.SUCCESS_OPTION);
    }

    /**
     * 查看反馈详情
     *
     * @param id 主键
     * @return java.lang.String
     * @author lmx
     * @date 2019/4/4 20:02
     */
    @RequestMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        FeedbackAssociation feedback = feedbackService.findAssociationById(id);
        model.addAttribute("feedback", feedback);
        return "admin/feedback/detail";
    }

}
