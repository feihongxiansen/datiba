package com.dtb.common.controller;

import com.dtb.entity.Carousel;
import com.dtb.home.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author zhufeihong
 * @date 2020/8/7-13:16
 * <p>
 * 通用控制器
 * </p>
 */
@Controller("commonIndexController")
public class IndexController {

    @Autowired
    private CarouselService carouselService;

    /**
     * 解决项目默认跳转主页问题
     *
     * @return java.lang.String
     * @author zhufeihong
     * @date 2020/8/7 13:19
     */
    @RequestMapping(value = {"/", ""})
    public String index(Model model) {
        List<Carousel> resultList = carouselService.findCarouselList();
        model.addAttribute("carouselList", resultList);
        return "home/index";
    }
}
