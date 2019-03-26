package com.dtb.entity;

/**
 * @Author：lmx
 * @Description：
 * @Date：Created on 23:50 2019/3/20.
 * @ModifyBy：
 */
public class DocumentCommentsAssociation extends DocumentComments {

    private User user;

    private Documents document;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Documents getDocument() {
        return document;
    }

    public void setDocument(Documents document) {
        this.document = document;
    }
}
