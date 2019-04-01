package com.dtb;

import com.dtb.admin.dao.AdminMapper;
import com.dtb.admin.service.TeacherService;
import com.dtb.entity.Teacher;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author lmx
 * @version 1.0.0
 * @create 2019/3/30-18:33
 */
public class AdminServiceTest extends DtbApplicationTests {

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private TeacherService teacherService;

    @Test
    public void selectPageAdminListByVagueTest() {
        Teacher teacher = new Teacher();
        teacher.setId(1);
//        System.out.println(teacherService.selectPageTeacherListVague(teacher));
//        System.out.println(adminMapper.selectPageAdminListByVague(true, "a"));
    }
}
