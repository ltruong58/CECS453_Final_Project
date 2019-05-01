package com.cecs453_final_project.quizmasters;

public class TakenQuiz {

    private int takenQuizID;
    private int accountID;

    public TakenQuiz(int takenQuizID, int accountID) {
        this.takenQuizID = takenQuizID;
        this.accountID = accountID;
    }

    public int getTakenQuizID() {
        return takenQuizID;
    }

    public void setTakenQuizID(int takenQuizID) {
        this.takenQuizID = takenQuizID;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }
}
