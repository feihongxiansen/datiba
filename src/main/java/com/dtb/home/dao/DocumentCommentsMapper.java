package com.dtb.home.dao;

import com.dtb.entity.DocumentComments;
import com.dtb.entity.DocumentCommentsAssociation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DocumentCommentsMapper {
    int deleteByPrimaryKey(Integer id);

    /**
     * @param record
     * @return java.lang.Integer
     * @auther lmx
     * @date 2019/3/21 22:54
     * @descript 插入数据
     */
    Integer insertSelective(DocumentComments record);

    DocumentComments selectByPrimaryKey(@Param("id") Integer id);

    /**
     * @param userId
     * @param documentId
     * @return com.dtb.entity.DocumentComments
     * @auther lmx
     * @date 2019/3/21 20:49
     * @descript 根据用户id和文档id查询，看用户是否下载过，下载过再次下载不再扣除积分
     */
    DocumentComments selectByUserIdAndDocumentId(@Param("userId") Integer userId,
                                                 @Param("documentId") Integer documentId);

    /**
     * @auther lmx
     * @date 2019/3/21 0:01
     * @descript 根据documentid查询评论
     * @param documentId
     * @return java.util.List<com.dtb.entity.DocumentCommentsAssociation>
     */
    List<DocumentCommentsAssociation> selectListByDocumentId(@Param("documentId")Integer documentId);

    int updateByPrimaryKeySelective(DocumentComments record);

    int updateByPrimaryKey(DocumentComments record);
}