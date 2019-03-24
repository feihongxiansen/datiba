package com.dtb.home.dao;

import com.dtb.entity.FeedbackWithBLOBs;
import org.apache.ibatis.annotations.Param;

public interface FeedbackMapper {
    int deleteByPrimaryKey(Integer id);

    /**
     * 添加反馈信息
     *
     * @param record 参数
     * @return java.lang.Integer
     * @author lmx
     * @date 2019/3/24 20:25
     */
    Integer insertSelective(FeedbackWithBLOBs record);

    /**
     * 根据主键查询
     *
     * @param id 主键
     * @return com.dtb.entity.FeedbackWithBLOBs
     * @author lmx
     * @date 2019/3/24 20:29
     */
    FeedbackWithBLOBs selectByPrimaryKey(@Param("id") Integer id);

    int updateByPrimaryKeySelective(FeedbackWithBLOBs record);
}