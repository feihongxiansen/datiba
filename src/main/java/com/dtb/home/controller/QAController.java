package com.dtb.home.controller;

import com.dtb.entity.AnswersWithBLOBs;
import com.dtb.entity.QuestionsAssociation;
import com.dtb.entity.QuestionsWithBLOBs;
import com.dtb.entity.User;
import com.dtb.home.service.GradeService;
import com.dtb.home.service.QAService;
import com.dtb.home.service.SubjectService;
import com.dtb.home.service.UserService;
import com.dtb.utils.FileUploadUtil;
import com.dtb.utils.email.EmailUtil;
import com.dtb.utils.resulthandler.CommonErrorEnum;
import com.dtb.utils.resulthandler.ResponseBean;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
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
@RequestMapping("/home/question")
public class QAController {

    @Autowired
    private QAService qaService;
    @Autowired
    private UserService userService;
    @Autowired
    private GradeService gradeService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private EmailUtil emailUtil;
    @Value("${com.dtb.file.baseFilePath}")
    private String baseFilePath;

    /**
     * @param pageNum         分页参数，当前页码
     * @param pageSize        分页参数，每页显示数量
     * @param gradeId         年级查询条件，没有则查询全部年级
     * @param subjectId       学科查询条件，没有则查询全部学科
     * @param questionSummary 题目搜索内容
     * @param needIntegral    是否需要积分
     * @return com.dtb.utils.resulthandler.ResponseBean<com.dtb.utils.resulthandler.CommonErrorEnum>
     * @author lmx
     * @date 2019/3/3 18:26
     * @descript 获取问题列表
     */
    @RequestMapping("/getQuestionList")
    @ResponseBody
    public ResponseBean
    getQuestionList(@RequestParam(value = "pageNum", required = false, defaultValue = "0") Integer pageNum,
                    @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                    @RequestParam(value = "gradeId", required = false) Integer gradeId,
                    @RequestParam(value = "subjectId", required = false) Integer subjectId,
                    @RequestParam(value = "questionSummary", required = false) String questionSummary,
                    @RequestParam(value = "needIntegral", required = false) Boolean needIntegral) {

        //启用分页查询
        PageHelper.startPage(pageNum, pageSize);
        Page<QuestionsWithBLOBs> page = qaService.findQuestionList(gradeId, subjectId, questionSummary, needIntegral);

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("list", page);
        Map<String, Object> pageMap = new HashMap<String, Object>();
        pageMap.put("total", page.getTotal());
        pageMap.put("pageNum", page.getPageNum());
        pageMap.put("pageSize", page.getPageSize());
        pageMap.put("pages", page.getPages());
        resultMap.put("pageInfo", pageMap);
        return new ResponseBean<>(true, resultMap, CommonErrorEnum.SUCCESS_REQUEST);
    }


    /**
     * @param questionId 问题id
     * @return com.dtb.utils.resulthandler.ResponseBean<com.dtb.utils.resulthandler.CommonErrorEnum>
     * @author lmx
     * @date 2019/3/6 23:32
     * @descript 根据问题id获取答案列表
     */
    @RequestMapping("/getAnswerList/{questionId}")
    @ResponseBody
    public ResponseBean getAnswerList(@PathVariable("questionId") Integer questionId) {
        QuestionsAssociation questionAndAnswers = qaService.findAnswerList(questionId);
        return new ResponseBean<>(true, questionAndAnswers, CommonErrorEnum.SUCCESS_REQUEST);
    }

    /**
     * @param files 文件数组
     * @return com.dtb.utils.resulthandler.ResponseBean<com.dtb.utils.resulthandler.CommonErrorEnum>
     * @author lmx
     * @date 2019/3/16 11:37
     * @descript 上传问题图片
     */
    @RequestMapping("/uploadQuestionImages")
    @ResponseBody
    public ResponseBean uploadQuestionImages(@RequestParam("files") MultipartFile[] files) throws IOException {
        String uploadPath = "/upload/images/question";
        String res = this.uploadImages(files, uploadPath, "question_");
        return new ResponseBean<>(true, res, CommonErrorEnum.FILEUPLOAD_SUCCESS);
    }

    /**
     * @param files       图片数组
     * @param uploadPath  保存路径
     * @param fileNamePre 图片名称前缀
     * @return java.lang.String 返回使用逗号分隔的图片地址字符串
     * @author lmx
     * @date 2019/3/16 11:42
     * @descript 上传图片方法
     */
    private String uploadImages(MultipartFile[] files, String uploadPath, String fileNamePre) throws IOException {

        String rootPath = this.baseFilePath + uploadPath;
        String res = FileUploadUtil.uploadFiles(files, rootPath, fileNamePre);
        //存储在数据库中的图片路径地址
        res = "/file" + uploadPath + "/" + res;
        return res;
    }

    /**
     * @return com.dtb.utils.resulthandler.ResponseBean<com.dtb.utils.resulthandler.CommonErrorEnum>
     * @author lmx
     * @date 2019/3/11 23:56
     * @descript 用户提问
     */
    @RequestMapping("/addQuestion")
    @ResponseBody
    public ResponseBean addQuestion(QuestionsWithBLOBs question, HttpSession session) {
        if (question.getQuestionPhotos() == null || question.getQuestionPhotos().trim() == "") {
            question.setQuestionPhotos(null);
        } else {
            question.setQuestionPhotos(question.getQuestionPhotos().substring(1));
        }

        //有悬赏积分，扣除悬赏用户的相应积分
        if (question.getIntegral() != null && question.getIntegral() > 0) {
            userService.updateIntegralById(-question.getIntegral(), question.getUserId());
            //扣除积分后更新session
            session.setAttribute("user", userService.findById(question.getUserId()));
        }

        // 插入成功，返回主键
        Integer affectedLine = qaService.addQuestion(question);

        if (affectedLine >= 1) {
            // 如果有邀约解答，就给被邀约人发提醒邮件
            if (question.getInvitaId() != null) {
                User invita = userService.findById(question.getInvitaId());
                User user = userService.findById(question.getUserId());
                String gradeName = gradeService.findById(question.getGradeId()).getGradeName();
                String subjectName = subjectService.findById(question.getSubjectId()).getSubjectName();
                Map<String, Object> infoMap = new HashMap<>();
                infoMap.put("nickName", user.getNickName());
                infoMap.put("userId", user.getId());
                infoMap.put("invitaUserName", invita.getUserName());
                infoMap.put("gradeName", gradeName);
                infoMap.put("subjectName", subjectName);
                infoMap.put("questionId", question.getId());
                infoMap.put("integral", question.getIntegral());
                infoMap.put("questionSummary", question.getQuestionSummary());
                emailUtil.sendTemplateMailAsync(invita.getEmail(), "【答题吧-邀约解答】", infoMap, "email/invita_answer");
            }
            return new ResponseBean<>(true, question, CommonErrorEnum.SUCCESS_OPTION);
        } else {
            return new ResponseBean<>(false, question, CommonErrorEnum.FAILED_QUESTION);
        }
    }

    /**
     * @param files 图片数组
     * @return com.dtb.utils.resulthandler.ResponseBean<com.dtb.utils.resulthandler.CommonErrorEnum>
     * @author lmx
     * @date 2019/3/16 11:47
     * @descript 上传答案图片
     */
    @RequestMapping("/uploadAnswerImages")
    @ResponseBody
    public ResponseBean uploadAnswerImages(@RequestParam("files") MultipartFile[] files) throws IOException {
        String uploadPath = "/upload/images/answer";
        String res = this.uploadImages(files, uploadPath, "answer_");
        return new ResponseBean<>(true, res, CommonErrorEnum.FILEUPLOAD_SUCCESS);
    }


    /**
     * @param answer 问题对象
     * @return com.dtb.utils.resulthandler.ResponseBean<com.dtb.utils.resulthandler.CommonErrorEnum>
     * @author lmx
     * @date 2019/3/16 11:47
     * @descript 添加答案
     */
    @RequestMapping("/addAnswer")
    @ResponseBody
    public ResponseBean addAnswer(AnswersWithBLOBs answer) {
        if (answer.getAnswerPhotos() == null || answer.getAnswerPhotos().trim() == "") {
            answer.setAnswerPhotos(null);
        } else {
            answer.setAnswerPhotos(answer.getAnswerPhotos().substring(1));
        }

        //对提问者奖励积分
        userService.updateIntegralById(5, answer.getUserId());
        //插入数据库
        int affectedLine = qaService.addAnswer(answer);
        if (affectedLine >= 1) {
            QuestionsWithBLOBs question = qaService.findById(answer.getQuestionId());
            // 如果问题未被关闭，发送邮件给提问者，有人解答了问题
            if (question.getQuestionState()) {
                User solveUser = userService.findById(answer.getUserId());
                User questionUser = userService.findById(question.getUserId());
                Map<String, Object> infoMap = new HashMap<>();
                infoMap.put("solveUserId", solveUser.getId());
                infoMap.put("solveNickName", solveUser.getNickName());
                infoMap.put("userName", questionUser.getUserName());
                infoMap.put("questionId", answer.getQuestionId());
                infoMap.put("questionSummary", question.getQuestionSummary());
                infoMap.put("answerSummary", answer.getAnswerSummary());
                infoMap.put("gradeName", gradeService.findById(question.getGradeId()).getGradeName());
                infoMap.put("subjectName", subjectService.findById(question.getSubjectId()).getSubjectName());
                emailUtil.sendTemplateMailAsync(questionUser.getEmail(), "【答题吧-解答提醒】", infoMap, "email/new_answer");
            }
            return new ResponseBean(true, CommonErrorEnum.SUCCESS_OPTION);
        } else {
            return new ResponseBean(false, CommonErrorEnum.FAILED_QUESTION);
        }
    }

    /**
     * 我的提问
     *
     * @return java.lang.String
     * @author lmx
     * @date 2019/3/28 19:43
     */
    @RequestMapping("/myAsk")
    public String myAsk() {
        return "/home/myask";
    }

    /**
     * 分页搜索问题列表
     *
     * @param state    问题状态，枚举值：1待解决，2待采纳，3已采纳，4已关闭
     * @param pageNum  当前页码
     * @param pageSize 每页显示条数
     * @param session  会话session
     * @return com.dtb.utils.resulthandler.ResponseBean
     * @author lmx
     * @date 2019/3/28 20:34
     */
    @RequestMapping("/getQuestionListByState")
    @ResponseBody
    public ResponseBean<Map<String, Object>> getQuestionListByState(@RequestParam Integer state,
                                                                    @RequestParam Integer pageNum,
                                                                    @RequestParam Integer pageSize,
                                                                    HttpSession session) {
        //启用分页查询
        PageHelper.startPage(pageNum, pageSize);
        User user = (User) session.getAttribute("user");
        Page<QuestionsAssociation> page = qaService.findQuestionListByState(state, user.getId());

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("list", page);
        Map<String, Object> pageMap = new HashMap<String, Object>();
        pageMap.put("total", page.getTotal());
        pageMap.put("pageNum", page.getPageNum());
        pageMap.put("pageSize", page.getPageSize());
        pageMap.put("pages", page.getPages());
        resultMap.put("pageInfo", pageMap);
        return new ResponseBean<Map<String, Object>>(true, resultMap, CommonErrorEnum.SUCCESS_REQUEST);
    }

    /**
     * 关闭提问状态
     *
     * @param id      问题id
     * @param session 会话session
     * @return com.dtb.utils.resulthandler.ResponseBean
     * @author lmx
     * @date 2019/3/29 20:07
     */
    @RequestMapping("/closeQuestion/{id}")
    @ResponseBody
    public ResponseBean<String> closeQuestion(@PathVariable Integer id, HttpSession session) {
        //获取登录用户id
        User user = (User) session.getAttribute("user");
        //查询待关闭问题的积分情况，如果有悬赏积分，则把积分归还给用户后关闭问题
        QuestionsWithBLOBs dbQuestion = qaService.findById(id);
        String msg = "问题已关闭！";
        if (dbQuestion.getIntegral() > 0) {
            userService.updateIntegralById(dbQuestion.getIntegral(), user.getId());
            //更新session数据，防止积分数据有误差
            session.setAttribute("user", userService.findById(user.getId()));
            msg = "问题已关闭，积分已退还当前账号！";
        }

        QuestionsWithBLOBs question = new QuestionsWithBLOBs();
        question.setId(id);
        question.setUserId(user.getId());
        question.setQuestionState(false);
        int res = qaService.updateQuestionSelectiveById(question);
        if (res <= 0) {
            msg = "更新失败";
            return new ResponseBean<String>(false, msg, CommonErrorEnum.FAILED_QUESTION);
        }
        return new ResponseBean<String>(true, msg, CommonErrorEnum.SUCCESS_OPTION);
    }

    /**
     * 采纳答案
     *
     * @param questionId 问题id
     * @param answerId   采纳的答案id
     * @param session    会话session
     * @return com.dtb.utils.resulthandler.ResponseBean<java.lang.String>
     * @author lmx
     * @date 2019/3/29 21:50
     */
    @RequestMapping("/adoptAnswer/{questionId}/{answerId}")
    @ResponseBody
    public ResponseBean<String> adoptAnswer(@PathVariable Integer questionId,
                                            @PathVariable Integer answerId,
                                            HttpSession session) {
        User user = (User) session.getAttribute("user");
        QuestionsWithBLOBs question = qaService.findById(questionId);
        AnswersWithBLOBs answer = qaService.findByAnswerId(answerId);
        if (!user.getId().equals(question.getUserId())) {
            return new ResponseBean<>(false, "请求错误！", CommonErrorEnum.FAILED_QUESTION);
        }
        //如果有悬赏积分，将积分添加到答题者账号
        if (question.getIntegral() > 0) {
            userService.updateIntegralById(question.getIntegral(), answer.getUserId());
        }

        //修改答案的采纳状态
        answer.setAdoptionState(true);
        qaService.updateAnswerSelectiveById(answer);

        //修改问题的采纳状态并且关闭问题
        question.setSolveState(answerId);
        question.setQuestionState(false);
        int result = qaService.updateQuestionSelectiveById(question);
        if (result > 0) {
            User answerUser = userService.findById(answer.getUserId());
            Map<String, Object> infoMap = new HashMap<>();
            infoMap.put("answerUserName", answerUser.getUserName());
            infoMap.put("questionId", questionId);
            infoMap.put("questionNickName", user.getNickName());
            infoMap.put("questionSummary", question.getQuestionSummary());
            infoMap.put("answerSummary", answer.getAnswerSummary());
            infoMap.put("integral", question.getIntegral());
            infoMap.put("gradeName", gradeService.findById(question.getGradeId()).getGradeName());
            infoMap.put("subjectName", subjectService.findById(question.getSubjectId()).getSubjectName());
            emailUtil.sendTemplateMailAsync(answerUser.getEmail(), "【答题吧-答案采纳】", infoMap, "email/adoption_answer");
            return new ResponseBean<>(true, "采纳成功！", CommonErrorEnum.SUCCESS_OPTION);
        }
        return new ResponseBean<>(false, "采纳失败！", CommonErrorEnum.SUCCESS_OPTION);
    }

    /**
     * 给答案点赞OR踩
     *
     * @param tp       种类，LIKE为赞，OPPOSE为踩
     * @param answerId 答案id
     * @return com.dtb.utils.resulthandler.ResponseBean
     * @author lmx
     * @date 2019/4/21 21:30
     */
    @RequestMapping("/attitude")
    @ResponseBody
    public ResponseBean attitude(@RequestParam String tp, @RequestParam Integer answerId) {
        qaService.approvalOROppose(tp, answerId);
        return new ResponseBean(true, CommonErrorEnum.SUCCESS_OPTION);
    }
}
