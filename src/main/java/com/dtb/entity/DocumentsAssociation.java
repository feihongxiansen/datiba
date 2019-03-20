package com.dtb.entity;

import java.util.List;

/**
 * @Author：lmx
 * @Description：
 * @Date：Created on 22:42 2019/3/19.
 * @ModifyBy：
 */
public class DocumentsAssociation extends Documents {

    private User user;

    private Admin admin;

    private Grade grade;

    private Subject subject;

    private List<DocumentCommentsAssociation> comments;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public List<DocumentCommentsAssociation> getComments() {
        return comments;
    }

    public void setComments(List<DocumentCommentsAssociation> comments) {
        this.comments = comments;
    }
}
