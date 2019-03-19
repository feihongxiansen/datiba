package com.dtb.home.dao;

import com.dtb.entity.Documents;
import com.dtb.entity.DocumentsAssociation;
import com.github.pagehelper.Page;
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

    /**
     * @auther lmx
     * @date 2019/3/19 22:45
     * @descript 分页查询文件列表
     * @param documents
     * @return com.github.pagehelper.Page<com.dtb.entity.DocumentsAssociation>
     */
    Page<DocumentsAssociation> selectDocumentListToLimit(Documents documents);

    Documents selectByPrimaryKey(@Param("id") Integer id);

    int updateByPrimaryKeySelective(Documents record);

    int updateByPrimaryKey(Documents record);
}