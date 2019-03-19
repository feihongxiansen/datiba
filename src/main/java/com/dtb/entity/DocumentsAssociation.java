package com.dtb.entity;

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
}
