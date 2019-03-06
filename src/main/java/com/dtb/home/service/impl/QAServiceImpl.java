package com.dtb.home.service.impl;

import com.dtb.entity.Questions;
import com.dtb.entity.QuestionsAssociation;
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
    public Page<Questions> findQuestionList(Integer gradeId, Integer subjectId, String questionSummary, Boolean needIntegral) {

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
            return questionsMapper.selectQuestionList(gradeId,subjectId,keySummary,needIntegral);
        }
        return questionsMapper.selectQuestionList(gradeId,subjectId,questionSummary,needIntegral);
    }

    @Override
    public Page<QuestionsAssociation> findAnswerList(Integer questionId) {
        return questionsMapper.selectAnswerList(questionId);
    }
}
