package com.dtb;

import com.dtb.home.service.SubjectService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

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

    @Test
    public void arraySerilize(){
        Set<String> hashSet = new HashSet<>();
        hashSet.add("AAAAAAAAAAAAAAAAAAA/AAAAAAAa/AAAAAAAAA.jpg");
        hashSet.add("BBBBBB/BBBBB/BBBB/BB.jpg");
        hashSet.add("CCCC/CCCC/CC.jpg");
        System.out.println(hashSet.toString());

        String str = hashSet.toString();

        String[] set = str.split(",");
        System.out.println(set[2]);
        System.out.println(set.toString());

    }
}
