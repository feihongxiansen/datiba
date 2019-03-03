package com.dtb.home.service;

import com.dtb.entity.Questions;
import com.github.pagehelper.Page;

/**
 * @Author：lmx
 * @Description：
 * @Date：Created on 17:22 2019/3/3.
 * @ModifyBy：
 */
public interface QAService {

    /**
     * @auther: lmx
     * @date: 2019/3/3 17:33
     * @descript: 分页查询问题列表
     * @param:
     * @return: com.github.pagehelper.Page<com.dtb.entity.Questions>
     */
    Page<Questions> findQuestionList();

}
