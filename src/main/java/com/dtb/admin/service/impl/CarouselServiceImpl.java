package com.dtb.admin.service.impl;

import com.dtb.admin.dao.CarouselMapper;
import com.dtb.admin.service.CarouselService;
import com.dtb.entity.Carousel;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lmx
 * @version 1.0.0
 * @create 2019/4/5-23:59
 */
@Service("carouselAdminService")
public class CarouselServiceImpl implements CarouselService {

    @Autowired
    private CarouselMapper carouselMapper;

    @Override
    public Page<Carousel> selectPageList(Carousel queryParam) {
        return carouselMapper.selectPageList(queryParam);
    }

    @Override
    public Integer updateBatchByIds(List<Integer> idList, Carousel param) {
        return carouselMapper.updateBatchByIds(idList, param);
    }

    @Override
    public Integer insert(Carousel param) {
        return carouselMapper.insert(param);
    }

    @Override
    public Carousel findById(Integer id) {
        return carouselMapper.selectById(id);
    }
}
