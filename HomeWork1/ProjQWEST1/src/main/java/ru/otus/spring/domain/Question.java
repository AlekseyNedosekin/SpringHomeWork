package ru.otus.spring.domain;

public class Question {
    private final String question;
    private final String[] answer;
    private final int answerNum;

    public Question(String question, String[] answer, int answerNum) {
        this.question  = question;
        this.answer    = answer;
        this.answerNum = answerNum;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getAnswer() {
        return answer;
    }

    public int getAnswerNum() {
        return answerNum;
    }
}
