package com.dtb;

import com.dtb.entity.Grade;
import com.dtb.home.service.GradeService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author：lmx
 * @Description：
 * @Date：Created on 17:06 2019/3/3.
 * @ModifyBy：
 */
public class GradeServiceTest extends DtbApplicationTests {

    @Autowired
    private GradeService gradeService;

    @Test
    public void findAllTest(){
        System.out.println(gradeService.findAll().toString());
    }

    @Test
    public void findByIdTest(){
        Grade grade = gradeService.findById(5);
        System.out.println(grade.getGradeName());
    }
}
