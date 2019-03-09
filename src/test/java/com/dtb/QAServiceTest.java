package com.dtb;

import com.dtb.entity.QuestionsAssociation;
import com.dtb.home.dao.AnswersMapper;
import com.dtb.home.dao.QuestionsMapper;
import com.dtb.home.service.QAService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author：lmx
 * @Description：
 * @Date：Created on 17:38 2019/3/3.
 * @ModifyBy：
 */
public class QAServiceTest extends DtbApplicationTests {

    @Autowired
    private QAService qaService;

    @Autowired
    private QuestionsMapper questionsMapper;

    @Autowired
    private AnswersMapper answersMapper;

    @Test
    public void findQuestionsListTest(){

        System.out.println(questionsMapper.selectAnswerList(1));

    }
}
