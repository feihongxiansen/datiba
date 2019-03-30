package com.dtb;

import com.dtb.admin.dao.AdminMapper;
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

    @Test
    public void selectPageAdminListByVagueTest() {
        System.out.println(adminMapper.selectPageAdminListByVague(true, "a"));
    }
}
