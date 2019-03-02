package com.dtb.entity;

import java.util.Date;

public class Answers {
    private Integer id;

    private Integer userId;

    private Integer questionId;

    private Integer approvalNum;

    private Integer opposeNum;

    private Boolean adoptionState;

    private Byte score;

    private Date deleteTime;

    private Date createTime;

    private Date updateTime;

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

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getApprovalNum() {
        return approvalNum;
    }

    public void setApprovalNum(Integer approvalNum) {
        this.approvalNum = approvalNum;
    }

    public Integer getOpposeNum() {
        return opposeNum;
    }

    public void setOpposeNum(Integer opposeNum) {
        this.opposeNum = opposeNum;
    }

    public Boolean getAdoptionState() {
        return adoptionState;
    }

    public void setAdoptionState(Boolean adoptionState) {
        this.adoptionState = adoptionState;
    }

    public Byte getScore() {
        return score;
    }

    public void setScore(Byte score) {
        this.score = score;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}