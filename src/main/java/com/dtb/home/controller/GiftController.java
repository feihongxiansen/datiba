package com.dtb.home.controller;

import com.dtb.entity.GiftWithBLOBs;
import com.dtb.home.service.GiftService;
import com.dtb.utils.resulthandler.CommonErrorEnum;
import com.dtb.utils.resulthandler.ResponseBean;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lmx
 * @version 1.0.0
 * @create 2019/3/23-18:24
 */
@Controller("giftController")
@RequestMapping("/home/gift")
public class GiftController {

    @Autowired
    private GiftService giftService;

    /**
     * @return com.dtb.utils.resulthandler.ResponseBean<com.dtb.utils.resulthandler.CommonErrorEnum>
     * @author lmx
     * @date 2019/3/23 18:40
     */
    @RequestMapping("/queryGiftList/{pageNum}/{pageSize}")
    @ResponseBody
    public ResponseBean queryGiftList(@PathVariable("pageNum") Integer pageNum,
                                      @PathVariable("pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<GiftWithBLOBs> pageList = giftService.findPageGiftList();
        Map<String, Object> reslutMap = new HashMap<>();
        reslutMap.put("giftList", pageList);

        Map<String, Object> pageInfoMap = new HashMap<>();
        pageInfoMap.put("total", pageList.getTotal());
        pageInfoMap.put("pageNum", pageList.getPageNum());
        pageInfoMap.put("pageSize", pageList.getPageSize());
        pageInfoMap.put("pages", pageList.getPages());
        reslutMap.put("pageInfo", pageInfoMap);
        return new ResponseBean<>(true, reslutMap, CommonErrorEnum.SUCCESS_REQUEST);
    }

    /**
     * 积分兑换详情，及其收货信息填写
     *
     * @param giftId gift主键
     * @param model  视图容器
     * @return java.lang.String
     * @author lmx
     * @date 2019/3/23 22:00
     */
    @RequestMapping("/detial/{giftId}")
    public String detial(@PathVariable("giftId") Integer giftId, Model model) {
        model.addAttribute("gift", giftService.findById(giftId));
        return "home/gift-detial";
    }

}
