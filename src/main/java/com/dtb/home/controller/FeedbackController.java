package com.dtb.home.controller;

import com.dtb.entity.FeedbackWithBLOBs;
import com.dtb.home.service.FeedbackService;
import com.dtb.utils.FileUploadUtil;
import com.dtb.utils.resulthandler.CommonErrorEnum;
import com.dtb.utils.resulthandler.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author lmx
 * @version 1.0.0
 * @create 2019/3/24-20:28
 */
@Controller("feedbackController")
@RequestMapping("home/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;
    @Value("${com.dtb.file.baseFilePath}")
    private String baseFilePath;

    /**
     * 添加反馈记录
     *
     * @param feedback 参数
     * @return com.dtb.utils.resulthandler.ResponseBean<java.lang.String>
     * @author lmx
     * @date 2019/3/24 20:31
     */
    @RequestMapping("addFeedback")
    @ResponseBody
    public ResponseBean<String> addFeedback(FeedbackWithBLOBs feedback) {
        if (feedback.getQuestionPhotos() == null || feedback.getQuestionPhotos().trim() == "") {
            feedback.setQuestionPhotos(null);
        } else {
            feedback.setQuestionPhotos(feedback.getQuestionPhotos().substring(1));
        }

        int affectedLine = feedbackService.addFeedback(feedback);
        if (affectedLine >= 1) {
            return new ResponseBean<>(true, "反馈成功，感谢您的反馈！", CommonErrorEnum.SUCCESS_OPTION);
        } else {
            return new ResponseBean<>(false, "提交失败了，请稍后重试！", CommonErrorEnum.FAILED_QUESTION);
        }
    }

    /**
     * 上传反馈图片
     *
     * @param files 文件数组
     * @return com.dtb.utils.resulthandler.ResponseBean<java.lang.String>
     * @author lmx
     * @date 2019/3/24 20:41
     */
    @RequestMapping("upload/images")
    @ResponseBody
    public ResponseBean<String> uploadFeedbackImages(@RequestParam("files") MultipartFile[] files) throws IOException {
        String uploadPath = "/upload/images/feedback";
        String res = this.uploadImages(files, uploadPath, "feedback_");
        return new ResponseBean<>(true, res, CommonErrorEnum.FILEUPLOAD_SUCCESS);
    }

    /**
     * 上传图片处理方法
     *
     * @param files       文件数组
     * @param uploadPath  上传路径
     * @param fileNamePre 文件名前缀
     * @return java.lang.String
     * @author lmx
     * @date 2019/3/24 20:39
     */
    public String uploadImages(MultipartFile[] files, String uploadPath, String fileNamePre) throws IOException {
        String rootPath = this.baseFilePath + uploadPath;
        String res = FileUploadUtil.uploadFiles(files, rootPath, fileNamePre);
        //存储在数据库中的图片路径地址
        res = "/file" + uploadPath + "/" + res;
        return res;
    }

    /**
     * 添加反馈页面渲染
     *
     * @return java.lang.String
     * @author lmx
     * @date 2019/3/24 20:33
     */
    @RequestMapping("add")
    public String feedback() {
        return "home/feedback";
    }
}
