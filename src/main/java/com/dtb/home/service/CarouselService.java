package com.dtb.home.service;

import com.dtb.entity.Carousel;

import java.util.List;

/**
 * @Author：lmx
 * @Description：轮播图服务类
 * @Date：Created on 0:24 2019/3/11.
 * @ModifyBy：
 */
public interface CarouselService {

    /**
     * @auther lmx
     * @date 2019/3/11 0:24
     * @descript 获取轮播图列表
     * @return java.util.List<com.dtb.entity.Carousel>
     */
    List<Carousel> findCarouselList();
}
