package com.dtb.home.controller;

import com.dtb.entity.Teacher;
import com.dtb.entity.TeacherAssociation;
import com.dtb.entity.User;
import com.dtb.home.service.SubjectService;
import com.dtb.home.service.TeacherService;
import com.dtb.utils.FileUploadUtil;
import com.dtb.utils.resulthandler.CommonErrorEnum;
import com.dtb.utils.resulthandler.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

/**
 * @Author：lmx
 * @Description：
 * @Date：Created on 14:44 2019/3/17.
 * @ModifyBy：教师认证相关控制器
 */
@RequestMapping("/home/teacher")
@Controller("teacherController")
public class TeacherController {

    @Autowired
    private SubjectService subjectService;
    @Autowired
    private TeacherService teacherService;
    @Value("${com.dtb.file.baseFilePath}")
    private String baseFilePath;

    /**
     * @param model   视图容器
     * @param session 会话session
     * @return java.lang.String
     * @auther lmx
     * @date 2019/3/17 17:09
     * @descript 教师认证页面视图
     */
    @RequestMapping("/auth")
    public String teacherAuth(Model model, HttpSession session) {
        model.addAttribute("subjectList", subjectService.findAll());
        User user = (User) session.getAttribute("user");
        TeacherAssociation teacher = this.isApplyed(user.getId());
        //未申请跳转到申请页面，申请过到进度页面
        if (teacher == null) {
            return "/home/teacher-auth";
        } else {
            model.addAttribute("applyInfo", teacher);
            return "/home/teacher-schedule";
        }

    }

    /**
     * @param userId 用户id
     * @return TeacherAssociation
     * @auther lmx
     * @date 2019/3/17 16:52
     * @descript 判断当前用户是否已经申请过认证
     */
    public TeacherAssociation isApplyed(Integer userId) {
        return teacherService.findByUserId(userId);
    }


    /**
     * @param teacher        教师信息
     * @param idCardImg1     身份证正面照
     * @param idCardImg2     身份证反面照
     * @param certificateImg 教师资格证照
     * @return com.dtb.utils.resulthandler.ResponseBean<com.dtb.utils.resulthandler.CommonErrorEnum>
     * @auther lmx
     * @date 2019/3/17 16:14
     * @descript 添加教师认证
     */
    @RequestMapping("/add")
    @ResponseBody
    public ResponseBean add(Teacher teacher,
                            @RequestParam("idCardImg1") MultipartFile idCardImg1,
                            @RequestParam("idCardImg2") MultipartFile idCardImg2,
                            @RequestParam("certificateImg") MultipartFile certificateImg) throws Exception {

        String uploadPath = "/upload/images/teacher";
        String rootPath = this.baseFilePath + uploadPath;
        String paperworkPhotos = "";

        //上传身份证正面照
        String imgPath = FileUploadUtil.upload(idCardImg1, rootPath, "teacher_idcard1_");
        imgPath = "/file" + uploadPath + "/" + imgPath;
        paperworkPhotos += imgPath;

        //上传身份证反面照
        imgPath = FileUploadUtil.upload(idCardImg2, rootPath, "teacher_idcard2_");
        imgPath = "/file" + uploadPath + "/" + imgPath;
        paperworkPhotos += "," + imgPath;//用逗号分隔多图

        //上传教师资格证照
        imgPath = FileUploadUtil.upload(certificateImg, rootPath, "teacher_certificate_");
        imgPath = "/file" + uploadPath + "/" + imgPath;
        paperworkPhotos += "," + imgPath;//用逗号分隔多图

        teacher.setPaperworkPhotos(paperworkPhotos);

        int affectedLine = teacherService.addTeacherAuth(teacher);
        if (affectedLine >= 1) {
            return new ResponseBean(true, CommonErrorEnum.WAIT_VERIFY);
        } else {
            return new ResponseBean(false, CommonErrorEnum.FAILED_QUESTION);
        }

    }
}
