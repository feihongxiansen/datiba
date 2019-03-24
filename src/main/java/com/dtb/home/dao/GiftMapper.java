package com.dtb.home.dao;

import com.dtb.entity.GiftWithBLOBs;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

public interface GiftMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(GiftWithBLOBs record);

    /**
     * 根据id查询
     *
     * @param id 主键id
     * @return com.dtb.entity.GiftWithBLOBs
     * @author lmx
     * @date 2019/3/23 17:34
     */
    GiftWithBLOBs selectByPrimaryKey(@Param("id") Integer id);

    /**
     * 分页查询物品信息
     *
     * @return com.github.pagehelper.Page<com.dtb.entity.GiftWithBLOBs>
     * @author lmx
     * @date 2019/3/23 17:39
     */
    Page<GiftWithBLOBs> selectPageGiftList();

    int updateByPrimaryKeySelective(GiftWithBLOBs record);

}