package com.dtb.home.controller;

import com.dtb.entity.Address;
import com.dtb.home.service.AddressService;
import com.dtb.utils.resulthandler.CommonErrorEnum;
import com.dtb.utils.resulthandler.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author lmx
 * @version 1.0.0
 * @create 2019/3/24-0:09
 */
@Controller("addressController")
@RequestMapping("/home/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    /**
     * 根据用户id查询收货地址列表
     *
     * @param userId 用户id
     * @return com.dtb.utils.resulthandler.ResponseBean<java.util.List < com.dtb.entity.Address>>
     * @author lmx
     * @date 2019/3/24 0:35
     */
    @RequestMapping("/queryAddressListByUserId/{userId}")
    @ResponseBody
    public ResponseBean<List<Address>> queryAddressListByUserId(@PathVariable("userId") Integer userId) {
        List<Address> addressList = addressService.findAddressListByUserId(userId);
        return new ResponseBean<List<Address>>(true, addressList, CommonErrorEnum.SUCCESS_REQUEST);
    }

    /**
     * 收货地址列表页面渲染
     *
     * @return java.lang.String
     * @author lmx
     * @date 2019/3/24 13:40
     */
    @RequestMapping("/addressList")
    public String addressList() {
        return "/home/address-list";
    }

    /**
     * 根据id查询地址
     *
     * @param id address主键id
     * @return com.dtb.utils.resulthandler.ResponseBean<com.dtb.entity.Address>
     * @author lmx
     * @date 2019/3/24 16:09
     */
    @RequestMapping("/queryAddressById/{id}")
    @ResponseBody
    public ResponseBean<Address> queryAddressById(@PathVariable("id") Integer id) {
        Address address = addressService.findAddressById(id);
        return new ResponseBean<Address>(true, address, CommonErrorEnum.SUCCESS_REQUEST);
    }

    /**
     * 我的地址页面渲染
     *
     * @return java.lang.String
     * @author lmx
     * @date 2019/4/6 15:31
     */
    @RequestMapping("/myaddress")
    public String myaddress() {
        return "/home/myaddress";
    }

    /**
     * 根id修改
     *
     * @param param 参数
     * @return com.dtb.utils.resulthandler.ResponseBean
     * @author lmx
     * @date 2019/4/6 16:10
     */
    @RequestMapping("/updateById")
    @ResponseBody
    public ResponseBean updateById(Address param) {
        addressService.updateById(param);
        return new ResponseBean(true, CommonErrorEnum.SUCCESS_OPTION);
    }

    /**
     * 添加
     *
     * @param param 参数
     * @return com.dtb.utils.resulthandler.ResponseBean
     * @author lmx
     * @date 2019/4/6 16:52
     */
    @RequestMapping("/add")
    @ResponseBody
    public ResponseBean add(Address param) {
        addressService.add(param);
        return new ResponseBean(true, CommonErrorEnum.SUCCESS_OPTION);
    }

    /**
     * 添加地址页面渲染
     *
     * @return java.lang.String
     * @author lmx
     * @date 2019/4/6 17:08
     */
    @RequestMapping("/addPage")
    public String addPage() {
        return "/home/add-address";
    }
}
