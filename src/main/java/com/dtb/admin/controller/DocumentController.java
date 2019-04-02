package com.dtb.admin.controller;

import com.dtb.admin.service.DocumentService;
import com.dtb.entity.Documents;
import com.dtb.entity.DocumentsAssociation;
import com.dtb.home.service.GradeService;
import com.dtb.home.service.SubjectService;
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
 * @create 2019/4/1-21:41
 */
@Controller("documentAdminController")
@RequestMapping("/admin/document")
public class DocumentController {

    @Autowired
    private DocumentService documentService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private GradeService gradeService;

    /**
     * 资料列表页面渲染
     *
     * @return java.lang.String
     * @author lmx
     * @date 2019/4/1 21:44
     */
    @RequestMapping("/listPage")
    public String listPage(Model model) {
        model.addAttribute("subjectList", subjectService.findAll());
        model.addAttribute("gradeList", gradeService.findAll());
        return "/admin/document/list";
    }

    /**
     * 多条件分页查询
     *
     * @param documents  查询参数
     * @param pageNum    当前页码
     * @param pageSize   每页显示数据
     * @param vagueParam 模糊查询参数
     * @return com.dtb.utils.resulthandler.ResponseBean
     * @author lmx
     * @date 2019/4/1 22:25
     */
    @RequestMapping("/listPageDocumentVague/{pageNum}/{pageSize}")
    @ResponseBody
    public ResponseBean listPageDocumentVague(Documents documents,
                                              @PathVariable Integer pageNum,
                                              @PathVariable Integer pageSize,
                                              @RequestParam String vagueParam) {
        PageHelper.startPage(pageNum, pageSize);
        Page<DocumentsAssociation> pageList = documentService.findPageDocument(documents, vagueParam);

        Map<String, Object> pageInfo = new HashMap<String, Object>();
        pageInfo.put("pageSize", pageList.getPageSize());
        pageInfo.put("pageNum", pageList.getPageNum());
        pageInfo.put("pages", pageList.getPages());
        pageInfo.put("total", pageList.getTotal());

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("documentList", pageList);
        resultMap.put("pageInfo", pageInfo);

        return new ResponseBean<>(true, resultMap, CommonErrorEnum.SUCCESS_REQUEST);
    }

    /**
     * 根据id批量更新
     *
     * @param idList id数组
     * @param param  更新参数
     * @return com.dtb.utils.resulthandler.ResponseBean
     * @author lmx
     * @date 2019/4/1 23:05
     */
    @RequestMapping("/updateBatchByIds")
    @ResponseBody
    public ResponseBean updateBatchByIds(@RequestParam List<Integer> idList, Documents param) {
        documentService.updateBatchByIds(idList, param);
        return new ResponseBean(true, CommonErrorEnum.SUCCESS_OPTION);
    }
}
