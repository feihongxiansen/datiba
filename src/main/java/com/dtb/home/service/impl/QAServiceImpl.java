package com.dtb.home.service.impl;

import com.dtb.entity.Questions;
import com.dtb.home.dao.QuestionsMapper;
import com.dtb.home.service.QAService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author：lmx
 * @Description：
 * @Date：Created on 17:22 2019/3/3.
 * @ModifyBy：
 */
@Service("qAService")
public class QAServiceImpl implements QAService {

    @Autowired
    private QuestionsMapper questionsMapper;

    @Override
    public Page<Questions> findQuestionList() {
        return questionsMapper.selectQuestionList();
    }
}
