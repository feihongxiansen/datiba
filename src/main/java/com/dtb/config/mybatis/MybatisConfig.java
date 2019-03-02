package com.dtb.config.mybatis;

/**
 * @auther: lmx
 * @descript:
 * @date: 2019/2/28 20:28
 */

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@MapperScan({"com.dtb.admin.dao","com.dtb.home.dao"})
@Configuration
public class MybatisConfig {
}
