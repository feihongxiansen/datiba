package com.dtb.admin.service.impl;

import com.dtb.admin.dao.DataAnalysisMapper;
import com.dtb.admin.service.DataAnalysisService;
import com.dtb.entity.AnswersWithBLOBs;
import com.dtb.entity.QuestionsWithBLOBs;
import com.dtb.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author lmx
 * @version 1.0.0
 * @create 2019/4/5-11:06
 */
@Service("dataAnalysisAdminService")
public class DataAnalysisServiceImpl implements DataAnalysisService {

    @Autowired
    private DataAnalysisMapper dataAnalysisMapper;

    @Override
    public Map<String, Object> overallStatistics(String groupType) {
        return dataAnalysisMapper.overallStatistics(groupType);
    }

    @Override
    public List<Map<String, Object>> selectQuestionByStateCount(Date startDate, Date endDate, String groupType, QuestionsWithBLOBs param) {
        return dataAnalysisMapper.selectQuestionByStateCount(startDate, endDate, groupType, param);
    }

    @Override
    public List<Map<String, Object>> selectAnswerByStateCount(Date startDate, Date endDate, String groupType, AnswersWithBLOBs param) {
        return dataAnalysisMapper.selectAnswerByStateCount(startDate, endDate, groupType, param);
    }

    @Override
    public List<Map<String, Object>> selectUserCount(User user) {
        return dataAnalysisMapper.selectUserCount(user);
    }
}
