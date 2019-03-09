package com.dtb.home.dao;

import com.dtb.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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

    /**
     * @auther lmx
     * @date 2019/3/10 1:46
     * @descript 获取用户列表
     * @param
     * @return java.util.List<com.dtb.entity.User>
     */
    @Select("select id,user_name,nick_name from as_user where email_verify=true and delete_time is null")
    List<User> selectUserList();

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKeyWithBLOBs(User record);

    int updateByPrimaryKey(User record);
}