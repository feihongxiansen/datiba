package com.dtb.admin.dao;

import com.dtb.entity.Documents;
import com.dtb.entity.DocumentsAssociation;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lmx
 * @version 1.0.0
 * @create 2019/4/1-21:41
 */
@Repository("documentAdminMapper")
public interface DocumentMapper {

    /**
     * 多条件模糊查询资料列表
     *
     * @param vagueParam 模糊搜索参数
     * @param document   查询参数
     * @return com.github.pagehelper.Page<com.dtb.entity.DocumentsAssociation>
     * @author lmx
     * @date 2019/4/1 22:13
     */
    Page<DocumentsAssociation> selectPageDocument(@Param("queryParam") Documents document, @Param("vagueParam") String vagueParam);

    /**
     * 根据主键查询资料
     *
     * @param id 主键
     * @return com.dtb.entity.Documents
     * @author lmx
     * @date 2019/4/1 22:19
     */
    Documents selectById(@Param("id") Integer id);

    /**
     * 根据id数组批量修改
     *
     * @param idList id数组
     * @param param  修改参数
     * @return java.lang.Integer
     * @author lmx
     * @date 2019/4/1 23:01
     */
    Integer updateBatchByIds(@Param("idList") List<Integer> idList, @Param("param") Documents param);
}
