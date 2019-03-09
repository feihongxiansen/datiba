package com.dtb.home.service.impl;

import com.dtb.entity.AnswersWithBLOBs;
import com.dtb.entity.Questions;
import com.dtb.entity.QuestionsAssociation;
import com.dtb.entity.QuestionsWithBLOBs;
import com.dtb.home.dao.AnswersMapper;
import com.dtb.home.dao.QuestionsMapper;
import com.dtb.home.service.QAService;
import com.github.pagehelper.Page;
import com.hankcs.hanlp.HanLP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author：lmx
 * @Description：
 * @Date：Created on 17:22 2019/3/3.
 * @ModifyBy：
 */
@Service("qAService")
public class QAServiceImpl implements QAService {

    @Autowired
    private QuestionsMapper questionsMapper;

    @Autowired
    private AnswersMapper answersMapper;

    @Override
    public Page<QuestionsWithBLOBs> findQuestionList(Integer gradeId, Integer subjectId, String questionSummary, Boolean needIntegral) {

        if (questionSummary!=null && questionSummary!=""){
            //关键词提取
            List<String> keywordList = HanLP.extractKeyword(questionSummary, 100);
            System.out.println("关键词提取结果："+keywordList);
            String keySummary="";
            if (keywordList.size()>0){
                for (int i=0; i<keywordList.size(); i++) {
                    keySummary += "*"+keywordList.get(i)+"* ";
                }
            }else{
                keySummary += "*"+questionSummary+"*";
            }
            return this.photosStrToStrArr(questionsMapper.selectQuestionList(gradeId,subjectId,questionSummary,needIntegral));
        }
        return this.photosStrToStrArr(questionsMapper.selectQuestionList(gradeId,subjectId,questionSummary,needIntegral));
    }

    @Override
    public QuestionsAssociation findAnswerList(Integer questionId) {
        QuestionsAssociation questionDetial = questionsMapper.selectAnswerList(questionId);

        //把问题图片字符串转为数组返回
        if (questionDetial.getQuestionPhotos()!=null){
            questionDetial.setQuestionPhotoList(questionDetial.getQuestionPhotos().split(","));
        }

        List<AnswersWithBLOBs> answerList = questionDetial.getAnswers();
        for (int j=0; j<answerList.size(); j++){
            String photosStr = answerList.get(j).getAnswerPhotos();
            if (photosStr==null)
                continue;
            answerList.get(j).setAnswerPhotoList(photosStr.split(","));
        }
        questionDetial.setAnswers(answerList);
        return questionDetial;
    }

    /**
     * @auther: lmx
     * @date: 2019/3/8 20:55
     * @descript: 处理问题图片列表
     * @param: questionsList
     * @return: com.github.pagehelper.Page<com.dtb.entity.QuestionsWithBLOBs>
     */
    public Page<QuestionsWithBLOBs> photosStrToStrArr(Page<QuestionsWithBLOBs> questionsList){
        for (int i=0; i<questionsList.size(); i++){
            if (questionsList.getResult().get(i).getQuestionPhotos()==null)
                continue;
            String photosStr = questionsList.getResult().get(i).getQuestionPhotos();
            questionsList.getResult().get(i).setQuestionPhotoList(photosStr.split(","));
        }
        return questionsList;
    }

}
