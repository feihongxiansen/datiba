package com.dtb.admin.dao;

import com.dtb.entity.User;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lmx
 * @version 1.0.0
 * @create 2019/3/31-9:47
 */
@Repository("userAdminMapper")
public interface UserMapper {

    /**
     * 分页查询用户信息
     *
     * @param queryParam 查询参数
     * @return com.github.pagehelper.Page<java.util.List < com.dtb.entity.User>>
     * @author lmx
     * @date 2019/3/31 10:11
     */
    Page<User> selectPageUserList(@Param("queryParam") User queryParam);

    /**
     * 分页模糊查询用户信息，根据姓名、昵称、电话或邮箱进行OR匹配
     *
     * @param queryParam 查询参数
     * @param vagueParam 模糊搜索参数
     * @return com.github.pagehelper.Page<com.dtb.entity.User>
     * @author lmx
     * @date 2019/3/31 12:15
     */
    Page<User> selectPageUserListVague(@Param("queryParam") User queryParam, String vagueParam);

    /**
     * 根据id修改用户信息
     *
     * @param param 参数
     * @return java.lang.Integer
     * @author lmx
     * @date 2019/3/31 14:43
     */
    Integer updateByIdSelective(@Param("param") User param);

    /**
     * 根据id查找用户
     *
     * @param id 用户id
     * @return com.dtb.entity.User
     * @author lmx
     * @date 2019/3/31 15:16
     */
    User selectById(@Param("id") Integer id);

    /**
     * 根据id数组进行软删除
     *
     * @param param  批量修改参数
     * @param idList id数组
     * @return java.lang.Integer
     * @author lmx
     * @date 2019/3/31 16:27
     */
    Integer updateBatchByIds(@Param("idList") List<Integer> idList, @Param("param") User param);
}
