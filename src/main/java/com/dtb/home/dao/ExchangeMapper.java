package com.dtb.home.dao;

import com.dtb.entity.Exchange;
import com.dtb.entity.ExchangeAssociation;
import com.github.pagehelper.Page;
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

    /**
     * 根据id查询兑换记录
     *
     * @param userId 用户id
     * @return com.dtb.entity.ExchangeAssociation
     * @author lmx
     * @date 2019/4/6 18:13
     */
    Page<ExchangeAssociation> selectListByUserId(@Param("userId") Integer userId);

    /**
     * 根据id修改
     *
     * @param param 参数
     * @return java.lang.Integer
     * @author lmx
     * @date 2019/4/6 18:43
     */
    Integer updateById(@Param("param") Exchange param);
}