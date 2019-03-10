package com.dtb.home.dao;

import com.dtb.entity.Carousel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CarouselMapper {

    /**
     * @auther lmx
     * @date 2019/3/11 0:21
     * @descript 获取主页轮播图列表
     * @return java.util.List<com.dtb.entity.Carousel>
     */
    @Select("select * from as_carousel where use_state = true and delete_time is null")
    List<Carousel> selectCarouselList();

    int deleteByPrimaryKey(@Param("id") Integer id);

    int insert(Carousel record);

    int insertSelective(Carousel record);

    Carousel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Carousel record);

    int updateByPrimaryKey(Carousel record);
}