package com.dtb.home.service;

import com.dtb.entity.GiftWithBLOBs;
import com.github.pagehelper.Page;

/**
 * @author lmx
 * @version 1.0.0
 * @date 2019/3/23 17:58
 */
public interface GiftService {

    /**
     * 分页查询积分兑换活动区的物品列表
     *
     * @return com.github.pagehelper.Page<com.dtb.entity.GiftWithBLOBs>
     * @author lmx
     * @date 2019/3/23 18:18
     */
    Page<GiftWithBLOBs> findPageGiftList();

    /**
     * 根据id查找gift信息
     *
     * @param id gift的主键
     * @return com.dtb.entity.GiftWithBLOBs
     * @author lmx
     * @date 2019/3/23 22:03
     */
    GiftWithBLOBs findById(Integer id);

    /**
     * 修改
     *
     * @param gift 参数
     * @return java.lang.Integer
     * @author lmx
     * @date 2019/3/24 18:36
     */
    Integer updateGiftSelectiveById(GiftWithBLOBs gift);
}
