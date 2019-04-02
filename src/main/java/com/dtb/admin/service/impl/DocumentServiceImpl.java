package com.dtb.admin.service.impl;

import com.dtb.admin.dao.DocumentMapper;
import com.dtb.admin.service.DocumentService;
import com.dtb.entity.Documents;
import com.dtb.entity.DocumentsAssociation;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lmx
 * @version 1.0.0
 * @create 2019/4/1-21:43
 */
@Service("documentAdminService")
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentMapper documentMapper;

    @Override
    public Page<DocumentsAssociation> findPageDocument(Documents document, String vagueParam) {
        return documentMapper.selectPageDocument(document, vagueParam);
    }

    @Override
    public Integer updateBatchByIds(List<Integer> idList, Documents param) {
        return documentMapper.updateBatchByIds(idList, param);
    }
}
