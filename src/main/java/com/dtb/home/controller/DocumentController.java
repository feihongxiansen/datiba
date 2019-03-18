package com.dtb.home.controller;

import com.dtb.entity.Documents;
import com.dtb.home.service.DocumentService;
import com.dtb.home.service.GradeService;
import com.dtb.home.service.SubjectService;
import com.dtb.utils.FileUploadUtil;
import com.dtb.utils.resulthandler.CommonErrorEnum;
import com.dtb.utils.resulthandler.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author：lmx
 * @Description：文档操作相关控制器
 * @Date：Created on 21:16 2019/3/17.
 * @ModifyBy：
 */
@Controller("documentController")
@RequestMapping("home/document")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private GradeService gradeService;


    /**
     * @auther lmx
     * @date 2019/3/17 21:33
     * @descript 多条件搜索文档数据，分页显示
     * @param documents 搜索条件
     * @param pageNum 当前页码
     * @param pageSize 每页显示数量
     * @return java.lang.String
     */
    public String list(Documents documents,
                       @PathVariable("pageNum") Integer pageNum,
                       @PathVariable("pageSize") Integer pageSize){

        return "home/document-list";
    }

    /**
     * @auther lmx
     * @date 2019/3/17 21:37
     * @descript 添加文档页面渲染
     * @param model 页面模型
     * @return java.lang.String
     */
    @RequestMapping("add")
    public String add(Model model){
        model.addAttribute("gradeList",gradeService.findAll());
        model.addAttribute("subjectList",subjectService.findAll());
        return "home/document-add";
    }

    /**
     * @auther lmx
     * @date 2019/3/18 23:50
     * @descript 用户上传文档
     * @param document 文档实体类
     * @param file 文件
     * @return com.dtb.utils.resulthandler.ResponseBean<com.dtb.utils.resulthandler.CommonErrorEnum>
     */
    @RequestMapping("upload")
    @ResponseBody
    public ResponseBean<CommonErrorEnum> upload(Documents document,
                                                @RequestParam("file")MultipartFile file) throws Exception{
        String uploadPath = "/static/upload/document/user-upload";
        String rootPath = ResourceUtils.getURL("classpath:").getPath()+uploadPath;
        String filePath = FileUploadUtil.upload(file,rootPath,"document_"+document.getDocumentType()+"_");
        filePath = uploadPath + "/" + filePath;
        document.setFilePath(filePath);

        int affectLine = documentService.addDocument(document);
        if (affectLine>0){
            return new ResponseBean(true,CommonErrorEnum.FILEUPLOAD_SUCCESS);
        }
        return new ResponseBean(false,CommonErrorEnum.FAILED_QUESTION);
    }
}
