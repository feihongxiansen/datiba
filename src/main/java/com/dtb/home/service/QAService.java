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
    Integer addQuestion(QuestionsWithBLOBs question);

    /**
     * @param answer
     * @return int
     * @auther lmx
     * @date 2019/3/16 11:33
     * @descript 添加答案
     */
    int addAnswer(AnswersWithBLOBs answer);

    /**
     * 更新问题信息
     *
     * @param questions 参数
     * @return java.lang.Integer
     * @author lmx
     * @date 2019/3/29 20:11
     */
    Integer updateQuestionSelectiveById(QuestionsWithBLOBs questions);

    /**
     * 根据问题id查询问题信息
     *
     * @param id 问题id
     * @return com.dtb.entity.QuestionsWithBLOBs
     * @author lmx
     * @date 2019/3/29 20:37
     */
    QuestionsWithBLOBs findById(Integer id);

    /**
     * 根据答案id查询
     *
     * @param id 答案id
     * @return com.dtb.entity.AnswersWithBLOBs
     * @author lmx
     * @date 2019/3/29 21:53
     */
    AnswersWithBLOBs findByAnswerId(Integer id);

    /**
     * 根据答案id修改信息
     *
     * @param answer 参数
     * @return java.lang.Integer
     * @author lmx
     * @date 2019/3/29 22:01
     */
    Integer updateAnswerSelectiveById(AnswersWithBLOBs answer);

    /**
     * 给答案点赞OR踩
     *
     * @param tp       种类，LIKE为赞，OPPOSE为踩
     * @param answerId 答案ID
     * @return java.lang.Integer
     * @author lmx
     * @date 2019/4/21 21:32
     */
    Integer approvalOROppose(String tp, Integer answerId);
}
