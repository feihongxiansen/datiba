package com.dtb.admin.controller;

import com.dtb.admin.service.ExchangeService;
import com.dtb.entity.Exchange;
import com.dtb.entity.ExchangeAssociation;
import com.dtb.entity.User;
import com.dtb.utils.email.EmailUtil;
import com.dtb.utils.resulthandler.CommonErrorEnum;
import com.dtb.utils.resulthandler.ResponseBean;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lmx
 * @version 1.0.0
 * @create 2019/4/4-21:34
 */
@Controller("exchangeAdminController")
@RequestMapping("/admin/exchange")
public class ExchangeController {

    @Autowired
    private ExchangeService exchangeService;
    @Autowired
    private EmailUtil emailUtil;

    /**
     * 兑换订单列表页面渲染
     *
     * @return java.lang.String
     * @author lmx
     * @date 2019/4/4 21:43
     */
    @RequestMapping("/listPage")
    public String listPage() {
        return "/admin/order/list";
    }

    /**
     * 模糊分页查询
     *
     * @param pageNum    当前页码
     * @param pageSize   每页显示数量
     * @param vagueParam 模糊搜索条件
     * @param exchange   多搜索条件
     * @return com.dtb.utils.resulthandler.ResponseBean
     * @author lmx
     * @date 2019/4/4 22:09
     */
    @RequestMapping("/getPageOrderListVague/{pageNum}/{pageSize}")
    @ResponseBody
    public ResponseBean getPageListVague(@PathVariable Integer pageNum,
                                         @PathVariable Integer pageSize,
                                         @RequestParam String vagueParam,
                                         Exchange exchange) {
        if (exchange == null) {
            return new ResponseBean(false, CommonErrorEnum.BAD_REQUEST);
        }
        PageHelper.startPage(pageNum, pageSize);
        Page<ExchangeAssociation> pageList = exchangeService.selectPageExchangeListVague(exchange, vagueParam);
        Map<String, Object> pageInfo = new HashMap<>();
        pageInfo.put("pageNum", pageList.getPageNum());
        pageInfo.put("pageSize", pageList.getPageSize());
        pageInfo.put("total", pageList.getTotal());
        pageInfo.put("pages", pageList.getPages());

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("pageInfo", pageInfo);
        resultMap.put("orderList", pageList);
        return new ResponseBean<Map<String, Object>>(true, resultMap, CommonErrorEnum.SUCCESS_REQUEST);
    }

    /**
     * 根据id批量修改
     *
     * @param idList id数组
     * @param param  修改参数
     * @return com.dtb.utils.resulthandler.ResponseBean
     * @author lmx
     * @date 2019/3/31 23:11
     */
    @RequestMapping("/updateBatchByIds")
    @ResponseBody
    public ResponseBean updateBatchByIds(@RequestParam List<Integer> idList,
                                         Exchange param) {
        int result = exchangeService.updateBatchByIds(idList, param);
        if (param.getTrackNumber() == null || "".equals(param.getTrackNumber())) {
            return new ResponseBean(true, CommonErrorEnum.SUCCESS_OPTION);
        }

        //快递已发货，发送邮件告知
        List<User> userList = exchangeService.selectUserByExchangeIds(idList);
        for (User item : userList) {
            Map<String, Object> infoMap = new HashMap<>();
            infoMap.put("userName", item.getUserName());
            emailUtil.sendTemplateMailAsync(item.getEmail(), "【答题吧-物流信息】", infoMap, "email/exchange_ship");
        }
        return new ResponseBean(true, CommonErrorEnum.SUCCESS_OPTION);
    }
}


