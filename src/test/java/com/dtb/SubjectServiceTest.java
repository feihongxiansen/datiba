package com.dtb;

import com.dtb.home.service.SubjectService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author：lmx
 * @Description：
 * @Date：Created on 17:13 2019/3/3.
 * @ModifyBy：
 */
public class SubjectServiceTest extends DtbApplicationTests{

    @Autowired
    private SubjectService subjectService;

    @Test
    public void findAllTest(){
        System.out.println(subjectService.findAll());
    }

    @Test
    public void findByIdTest(){
        System.out.println(subjectService.findById(2).getSubjectName());
    }
}
