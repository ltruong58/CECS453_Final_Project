package com.cecs453_final_project.quizmasters.Model;

/**
 * The Class .
 * <p>
 * Created by Long
 */
public class Question {
    private int questionID;
    private String questionText;
    private String correctedAnwser;
    private String altAnswer1;
    private String altAnswer2;
    private String altAnswer3;
    private int difficulty;

    public Question(int questionID, String questionText, String correctedAnwser, String altAnswer1, String altAnswer2, String altAnswer3, int difficulty) {
        this.questionID = questionID;
        this.questionText = questionText;
        this.correctedAnwser = correctedAnwser;
        this.altAnswer1 = altAnswer1;
        this.altAnswer2 = altAnswer2;
        this.altAnswer3 = altAnswer3;
        this.difficulty = difficulty;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getCorrectedAnwser() {
        return correctedAnwser;
    }

    public void setCorrectedAnwser(String correctedAnwser) {
        this.correctedAnwser = correctedAnwser;
    }

    public String getAltAnswer1() {
        return altAnswer1;
    }

    public void setAltAnswer1(String altAnswer1) {
        this.altAnswer1 = altAnswer1;
    }

    public String getAltAnswer2() {
        return altAnswer2;
    }

    public void setAltAnswer2(String altAnswer2) {
        this.altAnswer2 = altAnswer2;
    }

    public String getAltAnswer3() {
        return altAnswer3;
    }

    public void setAltAnswer3(String altAnswer3) {
        this.altAnswer3 = altAnswer3;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}
