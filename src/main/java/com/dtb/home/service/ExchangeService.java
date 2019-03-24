package com.dtb.home.service;

import com.dtb.entity.Exchange;

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
}
