package com.dtb.home.dao;

import com.dtb.entity.AnswersAssociation;
import com.dtb.entity.AnswersWithBLOBs;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AnswersMapper {

    /**
     * @auther: lmx
     * @date: 2019/3/6 23:23
     * @descript: 根据问题id获取答案信息
     * @param: questionId
     * @return: com.github.pagehelper.Page<com.dtb.entity.AnswersAssociation>
     */
    Page<AnswersAssociation> selectAnswerList(@Param("questionId") Integer questionId);

    /**
     * @auther: lmx
     * @date: 2019/3/7 0:17
     * @descript: 获取答案列表
     * @param: questionId
     * @return: java.util.List<com.dtb.entity.AnswersWithBLOBs>
     */
    List<AnswersAssociation> selectByQuestionId(@Param("questionId") Integer questionId);

    int deleteByPrimaryKey(Integer id);

    /**
     * @auther lmx
     * @date 2019/3/16 11:32
     * @descript 添加答案
     * @param record
     * @return int
     */
    int insertSelective(AnswersWithBLOBs record);

    /**
     * 给答案点赞OR踩
     *
     * @param tp       种类，LIKE为赞，OPPOSE为踩
     * @param answerId 答案ID
     * @return java.lang.Integer
     * @author lmx
     * @date 2019/4/21 21:32
     */
    Integer approvalOROppose(@Param("tp") String tp, @Param("answerId") Integer answerId);

    AnswersWithBLOBs selectByPrimaryKey(@Param("id") Integer id);

    int updateByPrimaryKeySelective(AnswersWithBLOBs record);
}