package com.dtb.admin.dao;

import com.dtb.entity.FeedbackAssociation;
import com.dtb.entity.FeedbackWithBLOBs;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lmx
 * @version 1.0.0
 * @create 2019/4/3-21:07
 */
@Repository("feedbackAdminMapper")
public interface FeedbackMapper {

    /**
     * 模糊分页搜索
     *
     * @param feedback   搜索条件
     * @param vagueParam 模糊搜索条件
     * @return com.github.pagehelper.Page<com.dtb.entity.FeedbackAssociation>
     * @author lmx
     * @date 2019/4/3 21:40
     */
    Page<FeedbackAssociation> selectPageFeedbackListVague(@Param("feedback") FeedbackWithBLOBs feedback, @Param("vagueParam") String vagueParam);

    /**
     * 根据id数组批量修改
     *
     * @param idList id数组
     * @param param  修改参数
     * @return java.lang.Integer
     * @author lmx
     * @date 2019/4/3 22:48
     */
    Integer updateBatchByIds(@Param("idList") List<Integer> idList, @Param("param") FeedbackWithBLOBs param);
}
