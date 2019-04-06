package com.dtb.home.service;

import com.dtb.entity.Exchange;
import com.dtb.entity.ExchangeAssociation;
import com.github.pagehelper.Page;

/**
 * @author lmx
 * @version 1.0.0
 * @create 2019/3/24-18:22
 */
public interface ExchangeService {

    /**
     * 添加兑换记录
     *
     * @param exchange 参数
     * @return java.lang.Integer
     * @author lmx
     * @date 2019/3/24 18:23
     */
    Integer addExchange(Exchange exchange);

    /**
     * 根据id查询兑换记录
     *
     * @param userId 用户id
     * @return com.dtb.entity.ExchangeAssociation
     * @author lmx
     * @date 2019/4/6 18:13
     */
    Page<ExchangeAssociation> findListByUserId(Integer userId);

    /**
     * 根据id修改
     *
     * @param param 参数
     * @return java.lang.Integer
     * @author lmx
     * @date 2019/4/6 18:43
     */
    Integer updateById(Exchange param);
}
