package com.dtb.home.dao;

import com.dtb.entity.Exchange;

public interface ExchangeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Exchange record);

    int insertSelective(Exchange record);

    Exchange selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Exchange record);

    int updateByPrimaryKey(Exchange record);
}