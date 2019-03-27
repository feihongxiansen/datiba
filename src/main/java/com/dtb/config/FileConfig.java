package com.dtb.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author lmx
 * @version 1.0.0
 * @create 2019/3/27-22:39
 */
@Configuration
public class FileConfig implements WebMvcConfigurer {

    /**
     * 配置本地磁盘资源路径，防止冲编译后资源被删除
     * @author lmx
     * @date 2019/3/27 23:10
     */
    @Value("${com.dtb.file.baseFilePath}")
    private String baseFilePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/file/**").addResourceLocations("file:"+baseFilePath);
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

}
