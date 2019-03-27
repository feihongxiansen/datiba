package com.dtb;

import com.dtb.entity.Documents;
import com.dtb.home.dao.DocumentCommentsMapper;
import com.dtb.home.dao.DocumentsMapper;
import com.dtb.home.service.DocumentService;
import com.github.pagehelper.PageHelper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author：lmx
 * @Description：
 * @Date：Created on 0:06 2019/3/21.
 * @ModifyBy：
 */
public class DocumentServiceTest extends DtbApplicationTests{
    @Autowired
    private DocumentCommentsMapper documentCommentsMapper;

    @Autowired
    private DocumentsMapper documentsMapper;

    @Autowired
    private DocumentService documentService;

    @Test
    public void selectListByDocumentId(){
        PageHelper.startPage(1,10);
        Documents documents = new Documents();
        documents.setId(1);
        documents.setCheckState((byte)1);
//        System.out.println(documentsMapper.selectDocumentListToLimit(documents).get(0).toString());
//
//        System.out.println(documentService.findDocumentListToLimit(documents).get(0).getComments().get(0).getCommentContent());

        System.out.println(documentCommentsMapper.selecDownloadListByUserId(1));
    }
}
