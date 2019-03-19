package com.dtb.home.service;

import com.dtb.entity.Documents;
import com.dtb.entity.DocumentsAssociation;
import com.github.pagehelper.Page;

/**
 * @Author：lmx
 * @Description：文档操作相关服务接口
 * @Date：Created on 21:17 2019/3/17.
 * @ModifyBy：
 */
public interface DocumentService {

    /**
     * @auther lmx
     * @date 2019/3/17 21:19
     * @descript 上传一个文档
     * @param document 文档实体类
     * @return int 返回受影响行数
     */
    int addDocument(Documents document);

    /**
     * @auther lmx
     * @date 2019/3/19 23:09
     * @descript 多条件查询文档数据
     * @param document 查询条件
     * @return com.github.pagehelper.Page<com.dtb.entity.DocumentsAssociation>
     */
    Page<DocumentsAssociation> findDocumentListToLimit(Documents document);

}
