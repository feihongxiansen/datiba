package com.dtb.home.service;

import com.dtb.entity.User;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

/**
 * @Author：lmx
 * @Description：
 * @Date：Created on 14:35 2019/2/25.
 * @ModifyBy：
 */
public interface UserService {

    /**
     * @auther: lmx
     * @date: 2019/3/1 18:34
     * @descript: 根据手机查找用户
     * @param: phone 手机号码
     * @return: com.dtb.entity.User
     */
    User findByPhone(String phone);

    /**
     * @auther: lmx
     * @date: 2019/3/1 18:33
     * @descript: 根据邮箱查询用户
     * @param: email 邮箱地址
     * @return: com.dtb.entity.User
     */
    User findByEmail(String email);
    
    /**
     * @auther: lmx
     * @date: 2019/3/1 20:07
     * @descript: 添加用户
     * @param: user 用户实体类
     * @return: int
     */
    int createUser(User user);

    /**
     * @auther: lmx
     * @date: 2019/3/2 17:26
     * @descript: 根据用户ID查找用户
     * @param: id 用户id
     * @return: com.dtb.entity.User
     */
    User findById(Integer id);

    /**
     * @auther: lmx
     * @date: 2019/3/2 17:43
     * @descript: 通过id修改用户信息
     * @param: user 用户实体类
     * @return: int 受影响行数
     */
    int updateByIdSelective(User user);

    /**
     * @auther lmx
     * @date 2019/3/10 1:46
     * @descript 获取用户列表
     * @param
     * @return java.util.List<com.dtb.entity.User>
     */
    List<User> findUserList();

    /**
     * @auther lmx
     * @date 2019/3/10 22:51
     * @descript 根据用户类别分页查询用户列表
     * @param userType
     * @return com.github.pagehelper.Page<com.dtb.entity.User>
     */
    Page<User> findUserListToLimit(Byte userType);


    /**
     * @auther lmx
     * @date 2019/3/15 1:10
     * @descript 根据用户id修改积分值
     * @param integral 调整的积分（增加/减少）
     * @param userId 用户id
     * @return int
     */
    int updateIntegralById(Integer integral,Integer userId);

    /**
     * @auther lmx
     * @date 2019/3/16 17:45
     * @descript 根据用户id查询信息
     * @param userId
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    Map<String,Object> findUserInfoById(Integer userId);
}
