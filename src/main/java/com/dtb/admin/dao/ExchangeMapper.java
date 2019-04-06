package com.dtb.admin.dao;

import com.dtb.entity.Exchange;
import com.dtb.entity.ExchangeAssociation;
import com.dtb.entity.User;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lmx
 * @version 1.0.0
 * @create 2019/4/4-21:41
 */
@Repository("exchangeAdminMapper")
public interface ExchangeMapper {

    /**
     * 多条件模糊分页查询
     *
     * @param queryParam 查询参数
     * @param vagueParam 模糊查询参数
     * @return com.github.pagehelper.Page<com.dtb.entity.ExchangeAssociation>
     * @author lmx
     * @date 2019/4/4 21:52
     */
    Page<ExchangeAssociation> selectPageExchangeListVague(@Param("queryParam") Exchange queryParam, @Param("vagueParam") String vagueParam);

    /**
     * 根据id批量修改
     *
     * @param idList id数组
     * @param param  修改参数
     * @return java.lang.Integer
     * @author lmx
     * @date 2019/4/4 23:45
     */
    Integer updateBatchByIds(@Param("idList") List<Integer> idList, @Param("param") Exchange param);

    /**
     * 根据审核申请查询id数组查询用户
     *
     * @param idList 主键
     * @return java.util.List<com.dtb.entity.User>
     * @author lmx
     * @date 2019/4/6 14:43
     */
    List<User> selectUserByExchangeIds(@Param("idList") List<Integer> idList);
}
