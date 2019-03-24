package com.dtb.home.service;

import com.dtb.entity.FeedbackWithBLOBs;

/**
 * @author lmx
 * @version 1.0.0
 * @create 2019/3/24-20:26
 */
public interface FeedbackService {

    /**
     * 添加反馈记录
     *
     * @param feedback 参数
     * @return java.lang.Integer
     * @author lmx
     * @date 2019/3/24 20:27
     */
    Integer addFeedback(FeedbackWithBLOBs feedback);
}
