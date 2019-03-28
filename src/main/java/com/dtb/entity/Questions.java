package com.dtb.entity;

import java.util.Date;

public class Questions {
    private Integer id;

    private Integer userId;

    private Integer invitaId;

    private Integer integral;

    private Integer solveState;

    private Boolean questionState;

    private Integer subjectId;

    private Integer gradeId;

    private Date createTime;

    private Date deleteTime;

    private Date updateTime;

    private String[] questionPhotoList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getInvitaId() {
        return invitaId;
    }

    public void setInvitaId(Integer invitaId) {
        this.invitaId = invitaId;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Integer getSolveState() {
        return solveState;
    }

    public void setSolveState(Integer solveState) {
        this.solveState = solveState;
    }

    public Boolean getQuestionState() {
        return questionState;
    }

    public void setQuestionState(Boolean questionState) {
        this.questionState = questionState;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
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

    public String[] getQuestionPhotoList() {
        return questionPhotoList;
    }

    public void setQuestionPhotoList(String[] questionPhotoList) {
        this.questionPhotoList = questionPhotoList;
    }
}