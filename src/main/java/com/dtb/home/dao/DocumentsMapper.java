package com.dtb.home.dao;

import com.dtb.entity.Documents;

public interface DocumentsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Documents record);

    int insertSelective(Documents record);

    Documents selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Documents record);

    int updateByPrimaryKey(Documents record);
}