package com.dtb.home.dao;

import com.dtb.entity.Questions;
import com.dtb.entity.QuestionsWithBLOBs;

public interface QuestionsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(QuestionsWithBLOBs record);

    int insertSelective(QuestionsWithBLOBs record);

    QuestionsWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QuestionsWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(QuestionsWithBLOBs record);

    int updateByPrimaryKey(Questions record);
}