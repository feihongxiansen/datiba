package com.dtb.home.service.impl;

import com.dtb.entity.GiftWithBLOBs;
import com.dtb.home.dao.GiftMapper;
import com.dtb.home.service.GiftService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lmx
 * @version 1.0.0
 * @create 2019/3/23-18:20
 */
@Service("giftService")
public class GiftServiceImpl implements GiftService {

    @Autowired
    private GiftMapper giftMapper;

    @Override
    public Page<GiftWithBLOBs> findPageGiftList() {
        return giftMapper.selectPageGiftList();
    }

    @Override
    public GiftWithBLOBs findById(Integer id) {
        return giftMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer updateGiftSelectiveById(GiftWithBLOBs gift) {
        return giftMapper.updateByPrimaryKeySelective(gift);
    }


}
