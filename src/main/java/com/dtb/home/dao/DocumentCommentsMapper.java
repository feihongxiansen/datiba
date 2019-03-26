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

    /**
     * 查询自己上传和下载的资料信息
     *
     * @param userId 自己的userID
     * @return java.util.List<com.dtb.entity.DocumentCommentsAssociation>
     * @author lmx
     * @date 2019/3/26 22:06
     */
    List<DocumentCommentsAssociation> selectUploadAndDownloadListByUserId(@Param("userId") Integer userId);

    /**
     * 根据用户id获取下载过的资源列表
     *
     * @param userId 用户id
     * @return java.util.List<com.dtb.entity.DocumentCommentsAssociation>
     * @author lmx
     * @date 2019/3/26 23:39
     */
    List<DocumentCommentsAssociation> selecDownloadListByUserId(@Param("userId") Integer userId);

    int updateByPrimaryKeySelective(DocumentComments record);

    int updateByPrimaryKey(DocumentComments record);
}