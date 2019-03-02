package com.dtb.home.dao;

import com.dtb.entity.Answers;
import com.dtb.entity.AnswersWithBLOBs;

public interface AnswersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AnswersWithBLOBs record);

    int insertSelective(AnswersWithBLOBs record);

    AnswersWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AnswersWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(AnswersWithBLOBs record);

    int updateByPrimaryKey(Answers record);
}