package com.dtb.admin.service;

import com.dtb.entity.Admin;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * @author lmx
 * @version 1.0.0
 * @create 2019/3/30-13:55
 */
public interface AdminService {

    /**
     * 查询一条数据
     *
     * @param admin 查询参数
     * @return com.dtb.entity.Admin
     * @author lmx
     * @date 2019/3/30 13:56
     */
    Admin findOne(Admin admin);

    /**
     * 根据参数分页查询admin列表
     *
     * @param admin 查询参数
     * @return com.github.pagehelper.Page<java.util.List < com.dtb.entity.Admin>>
     * @author lmx
     * @date 2019/3/30 16:20
     */
    Page<List<Admin>> findPageAdminList(Admin admin);

    /**
     * 根据状态精确查询，对昵称、姓名、邮箱、电话进行模糊查询
     *
     * @param loginState 启用状态
     * @param param      模糊查询参数
     * @return com.github.pagehelper.Page<java.util.List < com.dtb.entity.Admin>>
     * @author lmx
     * @date 2019/3/30 18:06
     */
    Page<List<Admin>> findPageAdminListByVague(Boolean loginState, String param);

    /**
     * 修改
     *
     * @param admin 参数
     * @return java.lang.Integer
     * @author lmx
     * @date 2019/3/30 18:44
     */
    Integer updateByIdSelective(Admin admin);

    /**
     * 根据id查找
     *
     * @param id 主键
     * @return com.dtb.entity.Admin
     * @author lmx
     * @date 2019/3/30 23:40
     */
    Admin findById(Integer id);

    /**
     * 添加管理员
     *
     * @param admin 参数
     * @return java.lang.Integer
     * @author lmx
     * @date 2019/3/31 0:04
     */
    Integer addAdmin(Admin admin);
}
