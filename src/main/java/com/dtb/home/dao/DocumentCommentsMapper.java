package com.dtb.home.dao;

import com.dtb.entity.DocumentComments;
import com.dtb.entity.DocumentCommentsAssociation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DocumentCommentsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DocumentComments record);

    int insertSelective(DocumentComments record);

    DocumentComments selectByPrimaryKey(@Param("id") Integer id);

    /**
     * @auther lmx
     * @date 2019/3/21 0:01
     * @descript 根据documentid查询评论
     * @param documentId
     * @return java.util.List<com.dtb.entity.DocumentCommentsAssociation>
     */
    List<DocumentCommentsAssociation> selectListByDocumentId(@Param("documentId")Integer documentId);

    int updateByPrimaryKeySelective(DocumentComments record);

    int updateByPrimaryKeyWithBLOBs(DocumentComments record);

    int updateByPrimaryKey(DocumentComments record);
}