package com.dtb.entity;

import java.util.Date;

public class Documents {
    private Integer id;

    private Integer userId;

    private Integer adminId;

    private Integer integral;

    private String title;

    private String summary;

    private Byte documentType;

    private Integer gradeId;

    private Integer subjectId;

    private Byte checkState;

    private Integer downloadCount;

    private String filePath;

    private String reason;

    private Float score;

    private Date createTime;

    private Date updateTime;

    private Date deleteTime;

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

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    public Byte getDocumentType() {
        return documentType;
    }

    public void setDocumentType(Byte documentType) {
        this.documentType = documentType;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Byte getCheckState() {
        return checkState;
    }

    public void setCheckState(Byte checkState) {
        this.checkState = checkState;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
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

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    @Override
    public String toString() {
        return "Documents{" +
                "id=" + id +
                ", userId=" + userId +
                ", adminId=" + adminId +
                ", integral=" + integral +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", documentType=" + documentType +
                ", gradeId=" + gradeId +
                ", subjectId=" + subjectId +
                ", checkState=" + checkState +
                ", downloadCount=" + downloadCount +
                ", filePath='" + filePath + '\'' +
                ", reason='" + reason + '\'' +
                ", score=" + score +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", deleteTime=" + deleteTime +
                '}';
    }
}