package com.dtb.admin.service.impl;

import com.dtb.admin.dao.GiftMapper;
import com.dtb.admin.service.GiftService;
import com.dtb.entity.GiftWithBLOBs;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lmx
 * @version 1.0.0
 * @create 2019/4/6-1:43
 */
@Service("giftAdminService")
public class GiftServiceImpl implements GiftService {

    @Autowired
    private GiftMapper giftMapper;

    @Override
    public Page<GiftWithBLOBs> selectPageList(GiftWithBLOBs queryParam) {
        return giftMapper.selectPageList(queryParam);
    }

    @Override
    public Integer updateBatchByIds(List<Integer> idList, GiftWithBLOBs param) {
        return giftMapper.updateBatchByIds(idList, param);
    }

    @Override
    public Integer insert(GiftWithBLOBs param) {
        return giftMapper.insert(param);
    }

    @Override
    public GiftWithBLOBs findById(Integer id) {
        return giftMapper.findById(id);
    }
}
