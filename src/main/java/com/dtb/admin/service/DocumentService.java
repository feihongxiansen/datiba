package com.dtb.admin.service;

import com.dtb.entity.Documents;
import com.dtb.entity.DocumentsAssociation;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * @author lmx
 * @version 1.0.0
 * @create 2019/4/1-21:42
 */
public interface DocumentService {

    /**
     * 多条件模糊查询资料列表
     *
     * @param vagueParam 模糊搜索参数
     * @param document   查询参数
     * @return com.github.pagehelper.Page<com.dtb.entity.DocumentsAssociation>
     * @author lmx
     * @date 2019/4/1 22:13
     */
    Page<DocumentsAssociation> findPageDocument(Documents document, String vagueParam);

    /**
     * 根据id数组批量修改
     *
     * @param idList id数组
     * @param param  修改参数
     * @return java.lang.Integer
     * @author lmx
     * @date 2019/4/1 23:01
     */
    Integer updateBatchByIds(List<Integer> idList, Documents param);
}
