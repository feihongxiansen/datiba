package com.dtb.entity;

/**
 * @author lmx
 * @version 1.0.0
 * @create 2019/4/3-21:34
 */
public class FeedbackAssociation extends FeedbackWithBLOBs {

    private User user;

    private Admin admin;

    private String[] questionPhotoArray;

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

    public String[] getQuestionPhotoArray() {
        return questionPhotoArray;
    }

    public void setQuestionPhotoArray(String[] questionPhotoArray) {
        this.questionPhotoArray = questionPhotoArray;
    }
}
