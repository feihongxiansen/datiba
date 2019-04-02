package com.dtb.admin.dao;

import com.dtb.entity.QuestionsAssociation;
import com.dtb.entity.QuestionsWithBLOBs;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lmx
 * @version 1.0.0
 * @create 2019/4/2-19:24
 */
@Repository("questionAdminMapper")
public interface QuestionMapper {

    /**
     * 多条件模糊分页搜索
     *
     * @param queryParam 搜索参数
     * @param vague      模糊搜索条件
     * @return com.github.pagehelper.Page<com.dtb.entity.QuestionsAssociation>
     * @author lmx
     * @date 2019/4/2 20:06
     */
    Page<QuestionsAssociation> selectPageQuestionVague(@Param("queryParam") QuestionsWithBLOBs queryParam, @Param("vague") String vague);

    /**
     * 根据id批量修改
     *
     * @param idList id数组
     * @param param  修改参数
     * @return java.lang.Integer
     * @author lmx
     * @date 2019/4/2 21:27
     */
    Integer updateBatchByIds(@Param("idList") List<Integer> idList, @Param("param") QuestionsWithBLOBs param);

    /**
     * 根据问题id查询答案列表
     *
     * @param questionId 问题id
     * @return com.dtb.entity.QuestionsAssociation
     * @author lmx
     * @date 2019/4/2 22:14
     */
    QuestionsAssociation findAnswerListById(@Param("questionId") Integer questionId);
}
