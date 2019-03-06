package com.dtb.home.service;

import com.dtb.entity.Questions;
import com.dtb.entity.QuestionsAssociation;
import com.github.pagehelper.Page;

/**
 * @Author：lmx
 * @Description：
 * @Date：Created on 17:22 2019/3/3.
 * @ModifyBy：
 */
public interface QAService {

    /**
     * @auther: lmx
     * @date: 2019/3/6 0:01
     * @descript: 分页查询问题列表
     * @param: gradeId 年级id
     * @param: subjectId 学科id
     * @param: questionSummary 问题描述
     * @param: needIntegral 是否需要积分
     * @return: com.github.pagehelper.Page<com.dtb.entity.Questions>
     */
    Page<Questions> findQuestionList(Integer gradeId,
                                     Integer subjectId,
                                     String questionSummary,
                                     Boolean needIntegral);

    /**
     * @auther: lmx
     * @date: 2019/3/6 23:23
     * @descript: 根据问题id获取答案信息
     * @param: questionId
     * @return: com.github.pagehelper.Page<com.dtb.entity.AnswersAssociation>
     */
    Page<QuestionsAssociation> findAnswerList(Integer questionId);

}
