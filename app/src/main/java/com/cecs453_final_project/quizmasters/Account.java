package com.cecs453_final_project.quizmasters;

public class Account {

    private int accountID;
    private String userName;
    private String password;
    private String type;

    public Account(int accountID, String userName, String password, String type) {
        this.accountID = accountID;
        this.userName = userName;
        this.password = password;
        this.type = type;
    }

    public Account() {
        this.accountID = 0;
        this.userName ="";
        this.password ="";
        this.type = "";
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
