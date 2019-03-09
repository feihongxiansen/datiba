package com.dtb;

import com.dtb.home.dao.UserMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author：lmx
 * @Description：
 * @Date：Created on 1:59 2019/3/10.
 * @ModifyBy：
 */
public class UserServiceTest extends DtbApplicationTests{

    @Autowired
    private UserMapper userMapper;

    @Test
    public void getUserListTest(){
        System.out.println(userMapper.selectByPrimaryKey(1));
        System.out.println(userMapper.selectUserList());
    }
}
