package com.dtb.home.controller;

import com.dtb.entity.Exchange;
import com.dtb.entity.ExchangeAssociation;
import com.dtb.entity.GiftWithBLOBs;
import com.dtb.entity.User;
import com.dtb.home.service.ExchangeService;
import com.dtb.home.service.GiftService;
import com.dtb.home.service.UserService;
import com.dtb.utils.resulthandler.CommonErrorEnum;
import com.dtb.utils.resulthandler.ResponseBean;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lmx
 * @version 1.0.0
 * @create 2019/3/24-18:15
 */
@RequestMapping("/home/exchange")
@Controller("exchangeController")
public class ExchangeController {

    @Autowired
    private ExchangeService exchangeService;
    @Autowired
    private UserService userService;
    @Autowired
    private GiftService giftService;

    /**
     * 积分兑换插入数据
     *
     * @param exchange 插入参数
     * @return com.dtb.utils.resulthandler.ResponseBean<java.lang.String>
     * @author lmx
     * @date 2019/3/24 18:40
     */
    @RequestMapping("/addExchange")
    @ResponseBody
    public ResponseBean<String> addExchange(Exchange exchange, HttpSession session) {
        User user = userService.findById(exchange.getUserId());
        GiftWithBLOBs gift = giftService.findById(exchange.getGiftId());
        if (gift.getQuantity() <= 0) {
            return new ResponseBean<String>(false, "当前物品已被兑换完毕，请兑换其他物品！", CommonErrorEnum.SUCCESS_REQUEST);
        }
        if (user.getIntegral() < gift.getIntegral()) {
            return new ResponseBean<String>(false, "您当前积分不足，不能兑换该物品，请去答题赚取积分或者兑换其他物品！", CommonErrorEnum.SUCCESS_REQUEST);
        }
        userService.updateIntegralById(-gift.getIntegral(), user.getId());
        //更新session的数据
        session.setAttribute("user", userService.findById(user.getId()));
        GiftWithBLOBs newGift = new GiftWithBLOBs();
        newGift.setId(gift.getId());
        newGift.setQuantity(gift.getQuantity() - 1);
        giftService.updateGiftSelectiveById(newGift);
        exchangeService.addExchange(exchange);
        return new ResponseBean<String>(true, "兑换成功！", CommonErrorEnum.SUCCESS_REQUEST);
    }

    /**
     * 查看我的兑换记录
     *
     * @return java.lang.String
     * @author lmx
     * @date 2019/4/6 18:03
     */
    @RequestMapping("/exchangePage")
    public String exchangePage() {
        return "/home/exchange-list";
    }

    /**
     * 根据用户id查询兑换记录
     *
     * @param userId 用户id
     * @return com.dtb.utils.resulthandler.ResponseBean
     * @author lmx
     * @date 2019/4/6 18:12
     */
    @RequestMapping("/queryExchangeListByUserId/{pageNum}/{pageSize}/{userId}")
    @ResponseBody
    public ResponseBean queryExchangeListByUserId(@PathVariable Integer pageNum,
                                                  @PathVariable Integer pageSize,
                                                  @PathVariable Integer userId) {
        PageHelper.startPage(pageNum, pageSize);
        Page<ExchangeAssociation> pageList = exchangeService.findListByUserId(userId);
        //获取分页信息
        Map<String, Object> pageInfo = new HashMap<String, Object>();
        pageInfo.put("pageSize", pageList.getPageSize());
        pageInfo.put("pageNum", pageList.getPageNum());
        pageInfo.put("pages", pageList.getPages());
        pageInfo.put("total", pageList.getTotal());

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("list", pageList);
        resultMap.put("pageInfo", pageInfo);

        return new ResponseBean<>(true, resultMap, CommonErrorEnum.SUCCESS_OPTION);
    }

    /**
     * 根据id修改
     *
     * @param param 参数
     * @return com.dtb.utils.resulthandler.ResponseBean
     * @author lmx
     * @date 2019/4/6 18:42
     */
    @RequestMapping("/updateById")
    @ResponseBody
    public ResponseBean updateById(Exchange param) {
        exchangeService.updateById(param);
        return new ResponseBean(true, CommonErrorEnum.SUCCESS_OPTION);
    }
}
