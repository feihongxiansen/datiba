package com.dtb.entity;

public class QuestionsWithBLOBs extends Questions {
    private String questionSummary;

    private String questionPhotos;

    public String getQuestionSummary() {
        return questionSummary;
    }

    public void setQuestionSummary(String questionSummary) {
        this.questionSummary = questionSummary == null ? null : questionSummary.trim();
    }

    public String getQuestionPhotos() {
        return questionPhotos;
    }

    public void setQuestionPhotos(String questionPhotos) {
        this.questionPhotos = questionPhotos == null ? null : questionPhotos.trim();
    }
}