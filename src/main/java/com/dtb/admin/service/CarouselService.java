package com.dtb.admin.service;

import com.dtb.entity.Carousel;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * @author lmx
 * @version 1.0.0
 * @create 2019/4/5-23:59
 */
public interface CarouselService {

    /**
     * 分页查询
     *
     * @param queryParam 查询参数
     * @return com.github.pagehelper.Page<com.dtb.entity.Carousel>
     * @author lmx
     * @date 2019/4/6 0:08
     */
    Page<Carousel> selectPageList(Carousel queryParam);

    /**
     * 根据id批量修改
     *
     * @param idList id 数组
     * @param param  修改参数
     * @return java.lang.Integer
     * @author lmx
     * @date 2019/4/6 0:37
     */
    Integer updateBatchByIds(List<Integer> idList, Carousel param);

    /**
     * 添加数据
     *
     * @param param 参数
     * @return java.lang.Integer
     * @author lmx
     * @date 2019/4/6 1:06
     */
    Integer insert(Carousel param);

    /**
     * 根据id查找
     *
     * @param id 主键
     * @return com.dtb.entity.Carousel
     * @author lmx
     * @date 2019/4/6 1:22
     */
    Carousel findById(Integer id);
}
