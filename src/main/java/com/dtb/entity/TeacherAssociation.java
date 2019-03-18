package com.dtb.entity;

/**
 * @Author：lmx
 * @Description：
 * @Date：Created on 16:48 2019/3/17.
 * @ModifyBy：
 */
public class TeacherAssociation extends Teacher{

    private User user;

    private Admin admin;

    private Subject subject;

    //用户上传的审核材料图片，第一张为IDcard正面，第二张为IDcard反面，第三张为资格证
    private String[] paperworkPhotoArray;

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

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String[] getPaperworkPhotoArray() {
        return paperworkPhotoArray;
    }

    public void setPaperworkPhotoArray(String[] paperworkPhotoArray) {
        this.paperworkPhotoArray = paperworkPhotoArray;
    }
}
