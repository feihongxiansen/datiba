package com.dtb.home.service.impl;

import com.dtb.entity.Exchange;
import com.dtb.entity.ExchangeAssociation;
import com.dtb.home.dao.ExchangeMapper;
import com.dtb.home.service.ExchangeService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lmx
 * @version 1.0.0
 * @create 2019/3/24-18:23
 */
@Service("exchangeService")
public class ExchangeServiceImpl implements ExchangeService {

    @Autowired
    private ExchangeMapper exchangeMapper;

    @Override
    public Integer addExchange(Exchange exchange) {
        return exchangeMapper.insertSelective(exchange);
    }

    @Override
    public Page<ExchangeAssociation> findListByUserId(Integer userId) {
        return exchangeMapper.selectListByUserId(userId);
    }

    @Override
    public Integer updateById(Exchange param) {
        return exchangeMapper.updateById(param);
    }
}
