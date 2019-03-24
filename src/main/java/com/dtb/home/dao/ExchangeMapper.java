package com.dtb.home.dao;

import com.dtb.entity.Exchange;
import org.apache.ibatis.annotations.Param;

public interface ExchangeMapper {


    int deleteByPrimaryKey(@Param("id") Integer id);

    /**
     * 插入兑换记录
     *
     * @param record 订单信息
     * @return Integer
     * @author lmx
     * @date 2019/3/24 18:18
     */
    Integer insertSelective(Exchange record);

    /**
     * 根据id查询
     *
     * @param id 主键
     * @return int
     * @author lmx
     * @date 2019/3/24 18:16
     */
    Exchange selectByPrimaryKey(@Param("id") Integer id);

    int updateByPrimaryKeySelective(Exchange record);
}