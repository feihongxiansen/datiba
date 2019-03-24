package com.dtb.home.service.impl;

import com.dtb.entity.Address;
import com.dtb.home.dao.AddressMapper;
import com.dtb.home.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lmx
 * @version 1.0.0
 * @create 2019/3/24-0:05
 */
@Service("addressService")
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public List<Address> findAddressListByUserId(Integer userId) {
        return addressMapper.selectAddressListByUserId(userId);
    }

    @Override
    public Address findAddressById(Integer id) {
        return addressMapper.selectByPrimaryKey(id);
    }
}
