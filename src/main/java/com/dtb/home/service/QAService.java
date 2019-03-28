package com.dtb.home.service;

import com.dtb.entity.AnswersWithBLOBs;
import com.dtb.entity.QuestionsAssociation;
import com.dtb.entity.QuestionsWithBLOBs;
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
    Page<QuestionsWithBLOBs> findQuestionList(Integer gradeId,
                                              Integer subjectId,
                                              String questionSummary,
                                              Boolean needIntegral);

    /**
     * 分页查询问题列表
     *
     * @param state  问题状态，枚举值：1待解决，2待采纳，3已采纳，4已关闭
     * @param userId 用户id
     * @return com.github.pagehelper.Page<com.dtb.entity.QuestionsAssociation>
     * @author lmx
     * @date 2019/3/28 20:37
     */
    Page<QuestionsAssociation> findQuestionListByState(Integer state, Integer userId);

    /**
     * @auther: lmx
     * @date: 2019/3/6 23:23
     * @descript: 根据问题id获取答案信息
     * @param: questionId
     * @return: com.dtb.entity.AnswersAssociation
     */
    QuestionsAssociation findAnswerList(Integer questionId);

    /**
     * @param question
     * @return int
     * @auther lmx
     * @date 2019/3/11 23:52
     * @descript 添加问题
     */
    int addQuestion(QuestionsWithBLOBs question);

    /**
     * @param answer
     * @return int
     * @auther lmx
     * @date 2019/3/16 11:33
     * @descript 添加答案
     */
    int addAnswer(AnswersWithBLOBs answer);

}
