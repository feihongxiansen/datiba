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

    int updateByPrimaryKeySelective(Address record);
}