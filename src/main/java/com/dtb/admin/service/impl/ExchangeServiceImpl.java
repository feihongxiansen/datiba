package com.dtb.admin.service.impl;

import com.dtb.admin.dao.ExchangeMapper;
import com.dtb.admin.service.ExchangeService;
import com.dtb.entity.Exchange;
import com.dtb.entity.ExchangeAssociation;
import com.dtb.entity.User;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lmx
 * @version 1.0.0
 * @create 2019/4/4-21:38
 */
@Service("exchangeAdminService")
public class ExchangeServiceImpl implements ExchangeService {

    @Autowired
    private ExchangeMapper exchangeMapper;


    @Override
    public Page<ExchangeAssociation> selectPageExchangeListVague(Exchange exchange, String vagueParam) {
        return exchangeMapper.selectPageExchangeListVague(exchange, vagueParam);
    }

    @Override
    public Integer updateBatchByIds(List<Integer> idList, Exchange param) {
        return exchangeMapper.updateBatchByIds(idList, param);
    }

    @Override
    public List<User> selectUserByExchangeIds(List<Integer> idList) {
        return exchangeMapper.selectUserByExchangeIds(idList);
    }
}
