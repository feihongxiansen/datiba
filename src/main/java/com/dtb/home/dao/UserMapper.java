package com.dtb.home.dao;

import com.dtb.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    /**
     * @auther: lmx
     * @descript: 根据手机号和密码登录
     * @date: 2019/2/28 22:52
     * @param: [phone]
     * @return: com.dtb.entity.User
     */
    User selectByPhone(@Param("phone") String phone);

    /**
     * @auther: lmx
     * @date: 2019/3/1 18:33
     * @descript: 根据邮箱查询用户
     * @param: email 邮箱地址
     * @return: com.dtb.entity.User
     */
    User selectByEmail(@Param("email") String email);

    /**
     * @auther: lmx
     * @date: 2019/3/2 17:32
     * @descript: 根据id查询用户信息
     * @param: id 用户主键
     * @return: int
     */
    int deleteByPrimaryKey(@Param("id") Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKeyWithBLOBs(User record);

    int updateByPrimaryKey(User record);
}