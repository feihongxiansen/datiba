package com.dtb.admin.dao;

import com.dtb.entity.Admin;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("adminAdminMapper")
public interface AdminMapper {

    Integer deleteByPrimaryKey(Integer id);

    /**
     * 插入数据
     *
     * @param admin 参数
     * @return java.lang.Integer
     * @author lmx
     * @date 2019/3/31 0:04
     */
    Integer insertSelective(@Param("admin") Admin admin);

    /**
     * 根据id查找
     *
     * @param id 主键
     * @return com.dtb.entity.Admin
     * @author lmx
     * @date 2019/3/30 23:41
     */
    Admin selectByPrimaryKey(@Param("id") Integer id);

    /**
     * 查询一条信息
     *
     * @param admin 查询参数
     * @return com.dtb.entity.Admin
     * @author lmx
     * @date 2019/3/30 13:53
     */
    Admin selectOne(@Param("admin") Admin admin);

    /**
     * 分页查询管理员列表
     *
     * @param admin 查询参数
     * @return com.github.pagehelper.Page<java.util.List < com.dtb.entity.Admin>>
     * @author lmx
     * @date 2019/3/30 16:14
     */
    Page<List<Admin>> selectPageAdminList(@Param("admin") Admin admin);

    /**
     * 根据状态精确查询，对昵称、姓名、邮箱、电话进行模糊查询
     *
     * @param loginState 启用状态
     * @param param      模糊查询参数
     * @return com.github.pagehelper.Page<java.util.List < com.dtb.entity.Admin>>
     * @author lmx
     * @date 2019/3/30 18:06
     */
    Page<List<Admin>> selectPageAdminListByVague(@Param("loginState") Boolean loginState, @Param("param") String param);

    /**
     * 修改
     *
     * @param admin 参数
     * @return java.lang.Integer
     * @author lmx
     * @date 2019/3/30 18:44
     */
    Integer updateByPrimaryKeySelective(@Param("admin") Admin admin);
}