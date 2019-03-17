package com.dtb.home.controller;

import com.dtb.entity.AnswersWithBLOBs;
import com.dtb.entity.QuestionsAssociation;
import com.dtb.entity.QuestionsWithBLOBs;
import com.dtb.home.service.QAService;
import com.dtb.home.service.UserService;
import com.dtb.utils.FileUploadUtil;
import com.dtb.utils.resulthandler.CommonErrorEnum;
import com.dtb.utils.resulthandler.ResponseBean;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author：lmx
 * @Description：问题搜索和解答相关业务类
 * @Date：Created on 16:31 2019/3/3.
 * @ModifyBy：
 */
@Controller("qAController")
@RequestMapping("home/question")
public class QAController {

    @Autowired
    private QAService qaService;

    @Autowired
    private UserService userService;

    /**
     * @auther: lmx
     * @date: 2019/3/3 18:26
     * @descript: 获取问题列表
     * @param: pageNum 分页参数，当前页码
     * @param: pageSize 分页参数，每页显示数量
     * @param: gradeId 年级查询条件，没有则查询全部年级
     * @param: subjectId 学科查询条件，没有则查询全部学科
     * @param: questionSummary 题目搜索内容
     * @param: needIntegral 是否需要积分
     * @return: com.dtb.utils.resulthandler.ResponseBean<com.dtb.utils.resulthandler.CommonErrorEnum>
     */
    @RequestMapping("getQuestionList")
    @ResponseBody
    public ResponseBean<CommonErrorEnum>
        getQuestionList(@RequestParam(value = "pageNum",required = true,defaultValue = "0") Integer pageNum,
                        @RequestParam(value = "pageSize",required = true,defaultValue = "10") Integer pageSize,
                        @RequestParam(value = "gradeId",required = false)Integer gradeId,
                        @RequestParam(value = "subjectId",required = false)Integer subjectId,
                        @RequestParam(value = "questionSummary",required = false)String questionSummary,
                        @RequestParam(value = "needIntegral",required = false)Boolean needIntegral){

        //启用分页查询
        PageHelper.startPage(pageNum,pageSize);
        Page<QuestionsWithBLOBs> page = qaService.findQuestionList(gradeId,subjectId,questionSummary,needIntegral);

        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("list",page);
        Map<String,Object> pageMap = new HashMap<String,Object>();
        pageMap.put("total",page.getTotal());
        pageMap.put("pageNum",page.getPageNum());
        pageMap.put("pageSize",page.getPageSize());
        pageMap.put("pages",page.getPages());
        resultMap.put("pageInfo",pageMap);

        System.out.println(resultMap);

        System.out.println("pageNum:"+pageNum+"-pageSize:"+pageSize+"-gradeId:"+gradeId+
                "-subjectId:"+subjectId+"-needIntegral:"+needIntegral+"-questionSummary:"+questionSummary);
        return new ResponseBean(true,resultMap,CommonErrorEnum.SUCCESS_REQUEST);
    }


    /**
     * @auther: lmx
     * @date: 2019/3/6 23:32
     * @descript: 根据问题id获取答案列表
     * @param: questionId
     * @param: pageNum
     * @param: pageSize
     * @return: com.dtb.utils.resulthandler.ResponseBean<com.dtb.utils.resulthandler.CommonErrorEnum>
     */
    @RequestMapping("getAnswerList/{questionId}")
    @ResponseBody
    public ResponseBean<CommonErrorEnum> getAnswerList(@PathVariable("questionId") Integer questionId){
        QuestionsAssociation questionAndAnswers = qaService.findAnswerList(questionId);
        return new ResponseBean(true,questionAndAnswers,CommonErrorEnum.SUCCESS_REQUEST);
    }

    /**
     * @auther lmx
     * @date 2019/3/16 11:37
     * @descript 上传问题图片
     * @param files
     * @return com.dtb.utils.resulthandler.ResponseBean<com.dtb.utils.resulthandler.CommonErrorEnum>
     */
    @RequestMapping("uploadQuestionImages")
    @ResponseBody
    public ResponseBean<CommonErrorEnum> uploadQuestionImages(@RequestParam("files") MultipartFile[] files)throws IOException {
        String uploadPath = "/static/upload/images/question";
        String res = this.uploadImages(files,uploadPath,"question_");
        return new ResponseBean(true,res,CommonErrorEnum.FILEUPLOAD_SUCCESS);
    }

    /**
     * @auther lmx
     * @date 2019/3/16 11:42
     * @descript 上传图片方法
     * @param files 图片数组
     * @param uploadPath 保存路径
     * @param fileNamePre 图片名称前缀
     * @return java.lang.String 返回使用逗号分隔的图片地址字符串
     */
    public String uploadImages(MultipartFile[] files,String uploadPath,String fileNamePre)throws IOException {

        String rootPath = ResourceUtils.getURL("classpath:").getPath()+uploadPath;
        String res = FileUploadUtil.uploadFiles(files,rootPath,fileNamePre);
        //存储在数据库中的图片路径地址
        res = uploadPath + "/" + res;
        return res;
    }

    /**
     * @auther lmx
     * @date 2019/3/11 23:56
     * @descript 用户提问
     * @param question
     * @return com.dtb.utils.resulthandler.ResponseBean<com.dtb.utils.resulthandler.CommonErrorEnum>
     */
    @RequestMapping("addQuestion")
    @ResponseBody
    public ResponseBean<CommonErrorEnum> addQuestion(QuestionsWithBLOBs question){
        if (question.getQuestionPhotos()==null || question.getQuestionPhotos().trim() ==""){
            question.setQuestionPhotos(null);
        }else{
            question.setQuestionPhotos(question.getQuestionPhotos().substring(1));
        }

        //有悬赏积分，扣除悬赏用户的相应积分
        if (question.getIntegral()!=null && question.getIntegral()> 0){
            userService.updateIntegralById(-question.getIntegral(),question.getUserId());
        }

        int affectedLine = qaService.addQuestion(question);
        if (affectedLine >= 1){
            return new ResponseBean(true,question,CommonErrorEnum.SUCCESS_OPTION);
        }else{
            return new ResponseBean(false,question,CommonErrorEnum.FAILED_QUESTION);
        }
    }

    /**
     * @auther lmx
     * @date 2019/3/16 11:47
     * @descript 上传答案图片
     * @param files 图片数组
     * @return com.dtb.utils.resulthandler.ResponseBean<com.dtb.utils.resulthandler.CommonErrorEnum>
     */
    @RequestMapping("uploadAnswerImages")
    @ResponseBody
    public ResponseBean<CommonErrorEnum> uploadAnswerImages(@RequestParam("files") MultipartFile[] files)throws IOException {
        String uploadPath = "/static/upload/images/answer";
        String res = this.uploadImages(files,uploadPath,"answer_");
        return new ResponseBean(true,res,CommonErrorEnum.FILEUPLOAD_SUCCESS);
    }


    /**
     * @auther lmx
     * @date 2019/3/16 11:47
     * @descript 添加答案
     * @param answer 问题对象
     * @return com.dtb.utils.resulthandler.ResponseBean<com.dtb.utils.resulthandler.CommonErrorEnum>
     */
    @RequestMapping("addAnswer")
    @ResponseBody
    public ResponseBean<CommonErrorEnum> addAnswer(AnswersWithBLOBs answer){
        if (answer.getAnswerPhotos()==null || answer.getAnswerPhotos().trim() ==""){
            answer.setAnswerPhotos(null);
        }else{
            answer.setAnswerPhotos(answer.getAnswerPhotos().substring(1));
        }

        //对提问者奖励积分
        userService.updateIntegralById(5,answer.getUserId());
        //插入数据库
        int affectedLine = qaService.addAnswer(answer);
        if (affectedLine >= 1){
            return new ResponseBean(true,CommonErrorEnum.SUCCESS_OPTION);
        }else{
            return new ResponseBean(false,CommonErrorEnum.FAILED_QUESTION);
        }
    }



}
