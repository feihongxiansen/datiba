package com.dtb.home.service;

import com.dtb.entity.Documents;

/**
 * @Author：lmx
 * @Description：文档操作相关服务接口
 * @Date：Created on 21:17 2019/3/17.
 * @ModifyBy：
 */
public interface DocumentService {

    /**
     * @auther lmx
     * @date 2019/3/17 21:19
     * @descript 上传一个文档
     * @param document 文档实体类
     * @return int 返回受影响行数
     */
    int addDocument(Documents document);
}
