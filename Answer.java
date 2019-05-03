package com.cecs453_final_project.quizmasters;

public class Answer {

    private  int answerID;
    private  int quizID;
    private  int questionID;
    private  String chosenAnswer;

    public Answer(int answerID, int quizID, int questionID, String chosenAnswer) {
        this.answerID = answerID;
        this.quizID = quizID;
        this.questionID = questionID;
        this.chosenAnswer = chosenAnswer;
    }

    public int getAnswerID() {
        return answerID;
    }

    public void setAnswerID(int answerID) {
        this.answerID = answerID;
    }

    public int getQuizID() {
        return quizID;
    }

    public void setQuizID(int quizID) {
        this.quizID = quizID;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public String getChosenAnswer() {
        return chosenAnswer;
    }

    public void setChosenAnswer(String chosenAnswer) {
        this.chosenAnswer = chosenAnswer;
    }
}
