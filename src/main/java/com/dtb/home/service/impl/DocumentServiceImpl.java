package com.dtb.home.service.impl;

import com.dtb.entity.DocumentComments;
import com.dtb.entity.Documents;
import com.dtb.entity.DocumentsAssociation;
import com.dtb.home.dao.DocumentCommentsMapper;
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
    @Autowired
    private DocumentCommentsMapper documentCommentsMapper;

    @Override
    public int addDocument(Documents document) {
        return documentsMapper.insertSelective(document);
    }

    @Override
    public Page<DocumentsAssociation> findDocumentListToLimit(Documents document) {
        return documentsMapper.selectDocumentListToLimit(document);
    }

    @Override
    public DocumentComments findByUserIdAndDocumentId(Integer userId, Integer documentId) {
        return documentCommentsMapper.selectByUserIdAndDocumentId(userId, documentId);
    }

    @Override
    public Integer addDocumentComment(DocumentComments documentComment) {
        return documentCommentsMapper.insertSelective(documentComment);
    }

    @Override
    public Integer downloadCountAdd(Integer id) {
        return documentsMapper.downloadCountAdd(id);
    }
}
