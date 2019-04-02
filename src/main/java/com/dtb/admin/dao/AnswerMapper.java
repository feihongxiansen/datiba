package com.dtb.admin.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author lmx
 * @version 1.0.0
 * @create 2019/4/2-23:08
 */
@Repository("answerAdminMapper")
public interface AnswerMapper {

    /**
     * 根据id删除
     *
     * @param id 主键
     * @return java.lang.Integer
     * @author lmx
     * @date 2019/4/2 23:09
     */
    Integer softDeleteById(@Param("id") Integer id);
}
