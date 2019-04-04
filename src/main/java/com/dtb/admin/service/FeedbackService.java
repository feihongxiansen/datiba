package com.dtb.admin.service;

import com.dtb.entity.FeedbackAssociation;
import com.dtb.entity.FeedbackWithBLOBs;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * @author lmx
 * @version 1.0.0
 * @create 2019/4/3-21:06
 */
public interface FeedbackService {

    /**
     * 分页模糊查询反馈信息
     *
     * @param feedback   搜索条件
     * @param vagueParam 模糊搜索条件
     * @return com.github.pagehelper.Page<com.dtb.entity.FeedbackAssociation>
     * @author lmx
     * @date 2019/4/3 21:36
     */
    Page<FeedbackAssociation> selectPageFeedbackListVague(FeedbackWithBLOBs feedback, String vagueParam);

    /**
     * 根据id批量修改
     *
     * @param idList id数组
     * @param param  修改参数
     * @return java.lang.Integer
     * @author lmx
     * @date 2019/4/3 22:47
     */
    Integer updateBatchByIds(List<Integer> idList, FeedbackWithBLOBs param);

    /**
     * 根据id查找
     *
     * @param id 主键
     * @return com.dtb.entity.FeedbackAssociation
     * @author lmx
     * @date 2019/4/4 20:54
     */
    FeedbackAssociation findAssociationById(Integer id);
}
