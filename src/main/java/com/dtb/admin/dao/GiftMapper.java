package com.dtb.admin.dao;

import com.dtb.entity.GiftWithBLOBs;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author lmx
 * @version 1.0.0
 * @create 2019/4/4-21:57
 */
@Repository("giftAdminMapper")
public interface GiftMapper {

    /**
     * 根据id查询
     *
     * @param id 主键
     * @return com.dtb.entity.GiftWithBLOBs
     * @author lmx
     * @date 2019/4/4 21:58
     */
    GiftWithBLOBs selectById(@Param("id") Integer id);
}
