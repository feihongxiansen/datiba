package com.dtb.home.dao;

import com.dtb.entity.Address;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AddressMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Address record);

    /**
     * 根据用户id查询地址列表
     *
     * @param userId 用户id
     * @return java.util.List<com.dtb.entity.Address>
     * @author lmx
     * @date 2019/3/23 23:58
     */
    List<Address> selectAddressListByUserId(@Param("userId") Integer userId);

    /**
     * 根据id查询address
     *
     * @param id id
     * @return com.dtb.entity.Address
     * @author lmx
     * @date 2019/3/24 16:06
     */
    Address selectByPrimaryKey(@Param("id") Integer id);

    /**
     * 根据id修改
     *
     * @param param 参数
     * @return java.lang.Integer
     * @author lmx
     * @date 2019/4/6 16:08
     */
    Integer updateById(@Param("param") Address param);

    /**
     * 添加
     *
     * @param param 参数
     * @return java.lang.Integer
     * @author lmx
     * @date 2019/4/6 16:37
     */
    Integer insert(@Param("param") Address param);
}