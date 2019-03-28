package com.dtb.home.dao;

import com.dtb.entity.Questions;
import com.dtb.entity.QuestionsAssociation;
import com.dtb.entity.QuestionsWithBLOBs;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

public interface QuestionsMapper {

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
    Page<QuestionsWithBLOBs> selectQuestionList(@Param("gradeId")Integer gradeId,
                                     @Param("subjectId")Integer subjectId,
                                     @Param("questionSummary")String questionSummary,
                                     @Param("needIntegral")Boolean needIntegral);

    /**
     * 分页查询问题列表
     *
     * @param state  问题状态
     * @param userId 用户id
     * @return com.github.pagehelper.Page<com.dtb.entity.QuestionsAssociation>
     * @author lmx
     * @date 2019/3/28 20:37
     */
    Page<QuestionsAssociation> findQuestionListByState(@Param("state") Integer state, @Param("userId") Integer userId);

    /**
     * @auther: lmx
     * @date: 2019/3/6 23:23
     * @descript: 根据问题id获取答案信息
     * @param: questionId
     * @return: com.dtb.entity.AnswersAssociation
     */
    QuestionsAssociation selectAnswerList(@Param("questionId") Integer questionId);


    int deleteByPrimaryKey(Integer id);

    int insert(QuestionsWithBLOBs record);

    /**
     * @auther lmx
     * @date 2019/3/11 23:50
     * @descript 插入问题记录
     * @param record
     * @return int
     */
    int insertSelective(QuestionsWithBLOBs record);

    QuestionsWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QuestionsWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(QuestionsWithBLOBs record);

    int updateByPrimaryKey(Questions record);
}