package com.dtb.entity;

import java.util.Date;

public class Teacher {
    private Integer id;

    private Byte authState;

    private Integer userId;

    private Integer adminId;

    private String reason;

    private Date createTime;

    private Date deleteTime;

    private Date updateTime;

    private String paperworkPhotos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getAuthState() {
        return authState;
    }

    public void setAuthState(Byte authState) {
        this.authState = authState;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getPaperworkPhotos() {
        return paperworkPhotos;
    }

    public void setPaperworkPhotos(String paperworkPhotos) {
        this.paperworkPhotos = paperworkPhotos == null ? null : paperworkPhotos.trim();
    }
}