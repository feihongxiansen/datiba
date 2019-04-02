package com.dtb.admin.service.impl;

import com.dtb.admin.dao.QuestionMapper;
import com.dtb.admin.service.QuestionService;
import com.dtb.entity.AnswersWithBLOBs;
import com.dtb.entity.QuestionsAssociation;
import com.dtb.entity.QuestionsWithBLOBs;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author lmx
 * @version 1.0.0
 * @create 2019/4/2-19:24
 */
@Service("questionAdminService")
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;


    @Override
    public Page<QuestionsAssociation> selectPageQuestionVague(QuestionsWithBLOBs queryParam, String vague) {
        Page<QuestionsAssociation> questionList = questionMapper.selectPageQuestionVague(queryParam, vague);
        if (questionList.getResult().isEmpty()) {
            return questionList;
        }
        //把图片路径字符串分割为数组
        for (int i = 0; i < questionList.size(); i++) {
            if (questionList.getResult().get(i).getQuestionPhotos() == null) {
                continue;
            }
            String photosStr = questionList.getResult().get(i).getQuestionPhotos();
            questionList.getResult().get(i).setQuestionPhotoList(photosStr.split(","));
        }
        return questionList;
    }

    @Override
    public Integer updateBatchByIds(List<Integer> idList, QuestionsWithBLOBs param) {
        return questionMapper.updateBatchByIds(idList, param);
    }

    @Override
    public QuestionsAssociation findAnswerListById(Integer questionId) {
        QuestionsAssociation questionDetial = questionMapper.findAnswerListById(questionId);

        //把问题图片字符串转为数组返回
        if (questionDetial.getQuestionPhotos() != null) {
            questionDetial.setQuestionPhotoList(questionDetial.getQuestionPhotos().split(","));
        }

        List<AnswersWithBLOBs> answerList = questionDetial.getAnswers();
        if (answerList == null || answerList.isEmpty()) {
            return questionDetial;
        }

        for (AnswersWithBLOBs answers : answerList) {
            String photosStr = answers.getAnswerPhotos();
            if (photosStr == null) {
                continue;
            }
            answers.setAnswerPhotoList(photosStr.split(","));
        }
        //对答案集合根据赞同人数倒序排序
        Collections.sort(answerList, new Comparator<AnswersWithBLOBs>() {
            @Override
            public int compare(AnswersWithBLOBs o1, AnswersWithBLOBs o2) {
                return o1.getApprovalNum() > o2.getApprovalNum() ? -1
                        : o1.getApprovalNum().equals(o2.getApprovalNum()) ? 0 : 1;
            }
        });
        questionDetial.setAnswers(answerList);
        //被采纳的排第一
        for (int i = 0; i < answerList.size(); i++) {
            if (answerList.get(i).getAdoptionState()) {
                AnswersWithBLOBs temp = answerList.get(0);
                answerList.set(0, answerList.get(i));
                answerList.set(i, temp);
                break;
            }
        }
        return questionDetial;
    }
}
