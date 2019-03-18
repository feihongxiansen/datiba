package com.dtb.home.dao;

import com.dtb.entity.Documents;
import org.apache.ibatis.annotations.Param;

public interface DocumentsMapper {
    int deleteByPrimaryKey(Integer id);

    /**
     * @auther lmx
     * @date 2019/3/17 21:20
     * @descript 添加一个文档
     * @param record 文档实体类
     * @return int受影响行数
     */
    int insertSelective(Documents record);

    Documents selectByPrimaryKey(@Param("id") Integer id);

    int updateByPrimaryKeySelective(Documents record);

    int updateByPrimaryKey(Documents record);
}