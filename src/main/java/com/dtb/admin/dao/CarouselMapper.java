package com.dtb.admin.dao;

import com.dtb.entity.Carousel;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lmx
 * @version 1.0.0
 * @create 2019/4/6-0:00
 */
@Repository("carouselAdminMapper")
public interface CarouselMapper {

    /**
     * 分页查询
     *
     * @param queryParam 查询参数
     * @return com.github.pagehelper.Page<com.dtb.entity.Carousel>
     * @author lmx
     * @date 2019/4/6 0:08
     */
    Page<Carousel> selectPageList(@Param("queryParam") Carousel queryParam);

    /**
     * 根据id批量修改
     *
     * @param idList id 数组
     * @param param  修改参数
     * @return java.lang.Integer
     * @author lmx
     * @date 2019/4/6 0:37
     */
    Integer updateBatchByIds(@Param("idList") List<Integer> idList, @Param("param") Carousel param);

    /**
     * 添加数据
     *
     * @param param 参数
     * @return java.lang.Integer
     * @author lmx
     * @date 2019/4/6 1:06
     */
    Integer insert(@Param("param") Carousel param);

    /**
     * 根据id查找
     *
     * @param id 主键
     * @return com.dtb.entity.Carousel
     * @author lmx
     * @date 2019/4/6 1:22
     */
    Carousel selectById(@Param("id") Integer id);
}
