package com.dtb.admin.service;

import com.dtb.entity.User;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * @author lmx
 * @version 1.0.0
 * @create 2019/3/31-9:48
 */
public interface UserService {

    /**
     * 分页查询用户信息
     *
     * @param queryParam 查询参数
     * @return com.github.pagehelper.Page<java.util.List < com.dtb.entity.User>>
     * @author lmx
     * @date 2019/3/31 10:11
     */
    Page<User> selectPageUserList(User queryParam);

    /**
     * 分页模糊查询用户信息，根据姓名、昵称、电话或邮箱进行OR匹配
     *
     * @param queryParam 查询参数
     * @return com.github.pagehelper.Page<com.dtb.entity.User>
     * @author lmx
     * @date 2019/3/31 12:15
     */
    Page<User> selectPageUserListVague(User queryParam, String vagueParam);

    /**
     * 根据id修改用户信息
     *
     * @param param 参数
     * @return java.lang.Integer
     * @author lmx
     * @date 2019/3/31 14:43
     */
    Integer updateByIdSelective(User param);

    /**
     * 根据用户id查找用户信息
     *
     * @param id 用户id
     * @return com.dtb.entity.User
     * @author lmx
     * @date 2019/3/31 15:18
     */
    User findById(Integer id);

    /**
     * 根据id数组进行软删除
     *
     * @param param  批量修改参数
     * @param idList id数组
     * @return java.lang.Integer
     * @author lmx
     * @date 2019/3/31 16:27
     */
    Integer updateBatchByIds(List<Integer> idList, User param);

}
