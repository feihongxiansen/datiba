package com.dtb.admin.service;

/**
 * @author lmx
 * @version 1.0.0
 * @create 2019/4/2-23:09
 */
public interface AnswerService {

    /**
     * 根据id删除
     *
     * @param id 主键
     * @return java.lang.Integer
     * @author lmx
     * @date 2019/4/2 23:09
     */
    Integer softDeleteById(Integer id);
}
