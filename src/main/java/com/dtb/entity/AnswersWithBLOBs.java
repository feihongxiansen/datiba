package com.dtb.entity;

public class AnswersWithBLOBs extends Answers {
    private String answerSummary;

    private String answerPhotos;

    public String getAnswerSummary() {
        return answerSummary;
    }

    public void setAnswerSummary(String answerSummary) {
        this.answerSummary = answerSummary == null ? null : answerSummary.trim();
    }

    public String getAnswerPhotos() {
        return answerPhotos;
    }

    public void setAnswerPhotos(String answerPhotos) {
        this.answerPhotos = answerPhotos == null ? null : answerPhotos.trim();
    }
}