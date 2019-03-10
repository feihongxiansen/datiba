package com.dtb.home.service.impl;

import com.dtb.entity.Carousel;
import com.dtb.home.dao.CarouselMapper;
import com.dtb.home.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author：lmx
 * @Description：
 * @Date：Created on 0:25 2019/3/11.
 * @ModifyBy：
 */
@Service("carouselService")
public class CarouselServiceImpl implements CarouselService {

    @Autowired
    private CarouselMapper carouselMapper;

    @Override
    public List<Carousel> findCarouselList() {
        return carouselMapper.selectCarouselList();
    }
}
