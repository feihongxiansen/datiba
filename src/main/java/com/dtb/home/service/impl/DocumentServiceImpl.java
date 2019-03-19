package com.dtb.home.service.impl;

import com.dtb.entity.Documents;
import com.dtb.entity.DocumentsAssociation;
import com.dtb.home.dao.DocumentsMapper;
import com.dtb.home.service.DocumentService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author：lmx
 * @Description：
 * @Date：Created on 21:19 2019/3/17.
 * @ModifyBy：
 */
@Service("documentService")
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentsMapper documentsMapper;

    @Override
    public int addDocument(Documents document) {
        return documentsMapper.insertSelective(document);
    }

    @Override
    public Page<DocumentsAssociation> findDocumentListToLimit(Documents document) {
        return documentsMapper.selectDocumentListToLimit(document);
    }
}
