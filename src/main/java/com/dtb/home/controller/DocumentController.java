package com.dtb.home.controller;

import com.dtb.entity.*;
import com.dtb.home.service.DocumentService;
import com.dtb.home.service.GradeService;
import com.dtb.home.service.SubjectService;
import com.dtb.home.service.UserService;
import com.dtb.utils.FileUploadUtil;
import com.dtb.utils.resulthandler.CommonErrorEnum;
import com.dtb.utils.resulthandler.ResponseBean;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private UserService userService;


    /**
     * @param model 页面容器
     * @return java.lang.String
     * @auther lmx
     * @date 2019/3/17 21:33
     * @descript 文档列表页渲染
     */
    @RequestMapping("list")
    public String list(Model model) {
        model.addAttribute("gradeList", gradeService.findAll());
        model.addAttribute("subjectList", subjectService.findAll());
        return "home/document-list";
    }

    /**
     * @param model 页面模型
     * @return java.lang.String
     * @auther lmx
     * @date 2019/3/17 21:37
     * @descript 添加文档页面渲染
     */
    @RequestMapping("add")
    public String add(Model model) {
        model.addAttribute("gradeList", gradeService.findAll());
        model.addAttribute("subjectList", subjectService.findAll());
        return "home/document-add";
    }

    /**
     * @param document 文档实体类
     * @param file     文件
     * @return com.dtb.utils.resulthandler.ResponseBean<com.dtb.utils.resulthandler.CommonErrorEnum>
     * @auther lmx
     * @date 2019/3/18 23:50
     * @descript 用户上传文档
     */
    @RequestMapping("upload")
    @ResponseBody
    public ResponseBean<CommonErrorEnum> upload(Documents document,
                                                @RequestParam("file") MultipartFile file) throws Exception {
        String uploadPath = "/static/upload/document/user-upload";
        String rootPath = ResourceUtils.getURL("classpath:").getPath() + uploadPath;
        String filePath = FileUploadUtil.upload(file, rootPath, "document_" + document.getDocumentType() + "_");
        filePath = uploadPath + "/" + filePath;
        document.setFilePath(filePath);

        int affectLine = documentService.addDocument(document);
        if (affectLine > 0) {
            return new ResponseBean(true, CommonErrorEnum.FILEUPLOAD_SUCCESS);
        }
        return new ResponseBean(false, CommonErrorEnum.FAILED_QUESTION);
    }

    /**
     * @param documents 搜索条件
     * @param pageNum   当前页码
     * @param pageSize  每页数据
     * @return com.dtb.utils.resulthandler.ResponseBean<com.dtb.utils.resulthandler.CommonErrorEnum>
     * @auther lmx
     * @date 2019/3/19 22:29
     * @descript 多条件搜索文档
     */
    @RequestMapping("searchListToLimit")
    @ResponseBody
    public ResponseBean<CommonErrorEnum> searchDocumentList(Documents documents,
                                                            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        byte checkStatus = 1;//查询条件，通过审核的文件
        PageHelper.startPage(pageNum, pageSize);
        documents.setCheckState(checkStatus);
        Page<DocumentsAssociation> pageList = documentService.findDocumentListToLimit(documents);

        Map<String, Object> pageInfo = new HashMap<String, Object>();
        pageInfo.put("pageSize", pageList.getPageSize());
        pageInfo.put("pageNum", pageList.getPageNum());
        pageInfo.put("pages", pageList.getPages());
        pageInfo.put("total", pageList.getTotal());

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("documentList", pageList);
        resultMap.put("pageInfo", pageInfo);

        return new ResponseBean(true, resultMap, CommonErrorEnum.SUCCESS_REQUEST);
    }

    /**
     * @param documentId 文档id
     * @param model      视图容器
     * @return java.lang.String
     * @auther lmx
     * @date 2019/3/21 20:56
     * @descript 文档详情查看
     */
    @RequestMapping("detial/{documentId}")
    public String documentDetial(@PathVariable("documentId") Integer documentId, Model model) {
        Documents documents = new Documents();
        documents.setId(documentId);
        byte checkStatus = 1;//查询条件，通过审核的文件
        PageHelper.startPage(1, 1);
        documents.setCheckState(checkStatus);
        Page<DocumentsAssociation> page = documentService.findDocumentListToLimit(documents);
        model.addAttribute("document", page);
        return "home/document-detial";
    }

    /**
     * @param documentId 文档id
     * @param session    会话session
     * @return com.dtb.utils.resulthandler.ResponseBean<com.dtb.utils.resulthandler.CommonErrorEnum>
     * @auther lmx
     * @date 2019/3/21 21:07
     * @descript 下载前检测，如果首次下载，需要扣除积分，如果下载过，不需要扣除
     */
    @RequestMapping("downloadCheck/{documentId}")
    @ResponseBody
    public ResponseBean<CommonErrorEnum> downloadCheck(@PathVariable("documentId") Integer documentId,
                                                       HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return new ResponseBean(false, CommonErrorEnum.FAILED_AUTH);
        }
        DocumentComments documentComments = documentService.findByUserIdAndDocumentId(user.getId(), documentId);
        if (documentComments == null) {
            return new ResponseBean(true, "FIRST", CommonErrorEnum.FIRST_DOWNLOAD);
        } else {
            //下载次数自加一
            documentService.downloadCountAdd(documentId);
            return new ResponseBean(true, "DOWNLOADED", CommonErrorEnum.SUCCESS_REQUEST);
        }
    }

    /**
     * @param addId      增加积分的用户id，也是文档上传者
     * @param lessId     扣除积分的用户id，也是文档下载者
     * @param integral   积分数
     * @param documentId 资料id
     * @param session    会话session
     * @return com.dtb.utils.resulthandler.ResponseBean<com.dtb.utils.resulthandler.CommonErrorEnum>
     * @auther lmx
     * @date 2019/3/21 22:26
     * @descript 首次下载文档，对积分进行扣除/增加
     */
    @RequestMapping("changeIntegral")
    @ResponseBody
    public ResponseBean<CommonErrorEnum> changeIntegral(@RequestParam("addId") Integer addId,
                                                        @RequestParam("lessId") Integer lessId,
                                                        @RequestParam("integral") Integer integral,
                                                        @RequestParam("documentId") Integer documentId,
                                                        HttpSession session) {
        User oldUser = (User) session.getAttribute("user");
        if (oldUser.getId() != lessId) {
            return new ResponseBean(false, "-1", "不能操作别人的积分");
        }
        userService.updateIntegralById(-integral, lessId);
        userService.updateIntegralById(integral, addId);
        //下载次数自加一
        documentService.downloadCountAdd(documentId);

        //如果不为空就是用户之前下载过此文档
        if (documentService.findByUserIdAndDocumentId(lessId, documentId) != null) {
            return new ResponseBean(true, CommonErrorEnum.SUCCESS_OPTION);
        }
        //否则就是首次下载
        DocumentComments documentComment = new DocumentComments();
        documentComment.setUserId(lessId);
        documentComment.setDocumentId(documentId);
        documentService.addDocumentComment(documentComment);
        //扣除积分后更新session
        session.setAttribute("user", userService.findById(oldUser.getId()));
        return new ResponseBean(true, CommonErrorEnum.SUCCESS_OPTION);
    }

    /**
     * 我的文档
     *
     * @return java.lang.String
     * @author lmx
     * @date 2019/3/25 0:04
     */
    @RequestMapping("myDocumentList")
    public String myDocumentList(Model model) {
        model.addAttribute("gradeList", gradeService.findAll());
        model.addAttribute("subjectList", subjectService.findAll());
        return "home/mydocument-list";
    }

    /**
     * 获取用户积分明细
     *
     * @param userId 用户id
     * @return com.dtb.utils.resulthandler.ResponseBean<java.util.List < com.dtb.entity.DocumentCommentsAssociation>>
     * @author lmx
     * @date 2019/3/26 22:20
     */
    @RequestMapping("myUploadAndDownload/{userId}")
    @ResponseBody
    public ResponseBean<List<DocumentCommentsAssociation>>
    getMyUploadAndDownload(@PathVariable("userId") Integer userId) {
        return new ResponseBean<>(true,
                documentService.getUploadAndDownloadListByUserId(userId),
                CommonErrorEnum.SUCCESS_REQUEST);
    }

    /**
     * 根据用户id查询下载记录
     *
     * @param userId 用户id
     * @return com.dtb.utils.resulthandler.ResponseBean<java.util.List < com.dtb.entity.DocumentCommentsAssociation>>
     * @author lmx
     * @date 2019/3/26 23:48
     */
    @RequestMapping("myDownloadList/{userId}")
    @ResponseBody
    public ResponseBean<List<DocumentCommentsAssociation>>
    getDownloadListByUserId(@PathVariable("userId") Integer userId) {
        return new ResponseBean<>(true, documentService.getDownloadListByUserId(userId));
    }
}
