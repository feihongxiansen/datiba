package com.dtb.home.dao;

import com.dtb.entity.Questions;
import com.dtb.entity.QuestionsWithBLOBs;
import com.github.pagehelper.Page;

public interface QuestionsMapper {

    /**
     * @auther: lmx
     * @date: 2019/3/3 17:33
     * @descript: 分页查询问题列表
     * @param:
     * @return: com.github.pagehelper.Page<com.dtb.entity.Questions>
     */
    Page<Questions> selectQuestionList();

    int deleteByPrimaryKey(Integer id);

    int insert(QuestionsWithBLOBs record);

    int insertSelective(QuestionsWithBLOBs record);

    QuestionsWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QuestionsWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(QuestionsWithBLOBs record);

    int updateByPrimaryKey(Questions record);
}