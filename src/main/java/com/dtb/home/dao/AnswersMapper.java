package com.dtb.home.dao;

import com.dtb.entity.Answers;
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
    List<AnswersWithBLOBs> selectByQuestionId(@Param("questionId") Integer questionId);

    int deleteByPrimaryKey(Integer id);

    int insert(AnswersWithBLOBs record);

    int insertSelective(AnswersWithBLOBs record);

    AnswersWithBLOBs selectByPrimaryKey(@Param("id") Integer id);

    int updateByPrimaryKeySelective(AnswersWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(AnswersWithBLOBs record);

    int updateByPrimaryKey(Answers record);
}