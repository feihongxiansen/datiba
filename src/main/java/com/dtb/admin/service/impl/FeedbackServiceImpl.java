package com.dtb.admin.service.impl;

import com.dtb.admin.dao.FeedbackMapper;
import com.dtb.admin.service.FeedbackService;
import com.dtb.entity.FeedbackAssociation;
import com.dtb.entity.FeedbackWithBLOBs;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lmx
 * @version 1.0.0
 * @create 2019/4/3-21:07
 */
@Service("feedbackAdminService")
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackMapper feedbackMapper;

    @Override
    public Page<FeedbackAssociation> selectPageFeedbackListVague(FeedbackWithBLOBs feedback, String vagueParam) {
        Page<FeedbackAssociation> pageList = feedbackMapper.selectPageFeedbackListVague(feedback, vagueParam);
        if (pageList.getResult().isEmpty()) {
            return pageList;
        }
        int i = 0;
        for (FeedbackAssociation item : pageList.getResult()) {
            if (item.getQuestionPhotos() == null || item.getQuestionPhotos() == "") {
                i++;
                continue;
            }
            pageList.getResult().get(i).setQuestionPhotoArray(item.getQuestionPhotos().split(","));
        }
        return pageList;
    }

    @Override
    public Integer updateBatchByIds(List<Integer> idList, FeedbackWithBLOBs param) {
        return feedbackMapper.updateBatchByIds(idList, param);
    }

    @Override
    public FeedbackAssociation findAssociationById(Integer id) {
        FeedbackAssociation feedbackAssociation = feedbackMapper.findAssociationById(id);
        if (feedbackAssociation.getQuestionPhotos() == null || "".equals(feedbackAssociation.getQuestionPhotos())) {
            return feedbackAssociation;
        }
        feedbackAssociation.setQuestionPhotoArray(feedbackAssociation.getQuestionPhotos().split(","));
        return feedbackAssociation;
    }
}
