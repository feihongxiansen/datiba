package com.dtb.home.controller;

import com.dtb.entity.Exchange;
import com.dtb.entity.GiftWithBLOBs;
import com.dtb.entity.User;
import com.dtb.home.service.ExchangeService;
import com.dtb.home.service.GiftService;
import com.dtb.home.service.UserService;
import com.dtb.utils.resulthandler.CommonErrorEnum;
import com.dtb.utils.resulthandler.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lmx
 * @version 1.0.0
 * @create 2019/3/24-18:15
 */
@RequestMapping("home/exchange")
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
    @RequestMapping("addExchange")
    @ResponseBody
    public ResponseBean<String> addExchange(Exchange exchange) {
        User user = userService.findById(exchange.getUserId());
        GiftWithBLOBs gift = giftService.findById(exchange.getGiftId());
        if (gift.getQuantity() <= 0) {
            return new ResponseBean<String>(false, "当前物品已被兑换完毕，请兑换其他物品！", CommonErrorEnum.SUCCESS_REQUEST);
        }
        if (user.getIntegral() < gift.getIntegral()) {
            return new ResponseBean<String>(false, "您当前积分不足，不能兑换该物品，请去答题赚取积分或者兑换其他物品！", CommonErrorEnum.SUCCESS_REQUEST);
        }
        userService.updateIntegralById(-gift.getIntegral(), user.getId());
        GiftWithBLOBs newGift = new GiftWithBLOBs();
        newGift.setId(gift.getId());
        newGift.setQuantity(gift.getQuantity() - 1);
        giftService.updateGiftSelectiveById(newGift);
        exchangeService.addExchange(exchange);
        return new ResponseBean<String>(true, "兑换成功！", CommonErrorEnum.SUCCESS_REQUEST);
    }
}
