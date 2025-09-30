package com.mycompany.mvcproject.domain;

public class QuizQuestion {
    private String question;
    private String answer;

    public QuizQuestion(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    // Getters
    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}
