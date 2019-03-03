package com.dtb;

import com.dtb.entity.Questions;
import com.dtb.home.service.QAService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author：lmx
 * @Description：
 * @Date：Created on 17:38 2019/3/3.
 * @ModifyBy：
 */
public class QAServiceTest extends DtbApplicationTests {

    @Autowired
    private QAService qaService;

    @Test
    public void findQuestionsListTest(){
        PageHelper.startPage(1,1);
        Page<Questions> questions = qaService.findQuestionList();
        System.out.println("findQuestionListTest:"+questions);

    }
}
