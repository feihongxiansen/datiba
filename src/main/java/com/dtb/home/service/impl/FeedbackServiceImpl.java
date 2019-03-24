package com.dtb.home.service.impl;

import com.dtb.entity.FeedbackWithBLOBs;
import com.dtb.home.dao.FeedbackMapper;
import com.dtb.home.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lmx
 * @version 1.0.0
 * @create 2019/3/24-20:27
 */
@Service("feedbackService")
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackMapper feedbackMapper;

    @Override
    public Integer addFeedback(FeedbackWithBLOBs feedback) {
        return feedbackMapper.insertSelective(feedback);
    }
}
