package com.dtb.entity;

/**
 * @author lmx
 * @version 1.0.0
 * @create 2019/4/21-23:29
 */
public class ReportAssociation extends Report {

    private User user;

    private Admin admin;

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
}
