package com.dtb.entity;

import java.util.List;

/**
 * @Author：lmx
 * @Description：
 * @Date：Created on 23:10 2019/3/5.
 * @ModifyBy：
 */
public class QuestionsAssociation extends QuestionsWithBLOBs {

    private User user;

    private User invita;

    private Grade grade;

    private Subject subject;

    private List<AnswersWithBLOBs> answers;

    //题目回答总数
    private Integer count;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getInvita() {
        return invita;
    }

    public void setInvita(User invita) {
        this.invita = invita;
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<AnswersWithBLOBs> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswersWithBLOBs> answers) {
        this.answers = answers;
    }
}
