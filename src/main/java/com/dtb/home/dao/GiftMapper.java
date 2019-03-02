package com.dtb.home.dao;

import com.dtb.entity.Gift;
import com.dtb.entity.GiftWithBLOBs;

public interface GiftMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GiftWithBLOBs record);

    int insertSelective(GiftWithBLOBs record);

    GiftWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GiftWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(GiftWithBLOBs record);

    int updateByPrimaryKey(Gift record);
}