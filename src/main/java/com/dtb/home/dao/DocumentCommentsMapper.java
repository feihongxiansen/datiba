package com.dtb.home.dao;

import com.dtb.entity.DocumentComments;

public interface DocumentCommentsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DocumentComments record);

    int insertSelective(DocumentComments record);

    DocumentComments selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DocumentComments record);

    int updateByPrimaryKeyWithBLOBs(DocumentComments record);

    int updateByPrimaryKey(DocumentComments record);
}