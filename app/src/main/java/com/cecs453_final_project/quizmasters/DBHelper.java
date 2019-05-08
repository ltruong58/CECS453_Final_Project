
package com.cecs453_final_project.quizmasters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Random;

public class DBHelper extends SQLiteOpenHelper {



    public static final String DATABASE_NAME = "MyDBName.db";

    public static final String ACCOUNTS_TABLE_NAME = "accounts";
    public static final String ACCOUNTS_COLUMN_ACCOUNTID = "accountID";
    public static final String ACCOUNTS_COLUMN_USERNAME = "userName";
    public static final String ACCOUNTS_COLUMN_PASSWORD = "password";
    public static final String ACCOUNTS_COLUMN_TYPE = "type";

    public static final String QUESTIONS_TABLE_NAME = "questions";
    public static final String QUESTIONS_COLUMN_QUESTIONID = "questionID";
    public static final String QUESTIONS_COLUMN_QUESTIONTEXT = "questionText";
    public static final String QUESTIONS_COLUMN_CORRECTANSWER = "correctAnswer";
    public static final String QUESTIONS_COLUMN_ALTANSWER1 = "altAnswer1";
    public static final String QUESTIONS_COLUMN_ALTANSWER2 = "altAnswer2";
    public static final String QUESTIONS_COLUMN_ALTANSWER3 = "altAnswer3";
    public static final String QUESTIONS_COLUMN_DIFFICULTY = "difficulty";

    public static final String TAKENQUIZZES_TABLE_NAME = "takenQuizzes";
    public static final String QUIZZES_COLUMN_TAKENQUIZID = "takenQuizID";
    public static final String QUIZZES_COLUMN_ACCOUNTID = "accountID";

    public static final String ANSWERS_TABLE_NAME = "answers";
    public static final String ANSWERS_COLUMN_ANSWERID = "answerID";
    public static final String ANSWERS_COLUMN_QUIZID = "quizID";
    public static final String ANSWERS_COLUMN_QUESTIONID = "questionID";
    public static final String ANSWERS_COLUMN_CHOSENANSWER = "chosenAnswer";



    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    public void resetDB() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS accounts");
        db.execSQL("DROP TABLE IF EXISTS questions");
        db.execSQL("DROP TABLE IF EXISTS takenQuizzes");
        db.execSQL("DROP TABLE IF EXISTS answers");

        db.execSQL(
                "create table accounts " +
                        "(accountID integer primary key, userName text,password text,type text)"
        );

        db.execSQL(
                "create table questions " +
                        "(questionID integer primary key, questionText text, correctAnswer text, altAnswer1 text, altAnswer2 text, altAnswer3 text, difficulty integer)"
        );

        db.execSQL(
                "create table takenQuizzes " +
                        "(takenQuizID integer primary key, accountID integer)"
        );

        db.execSQL(
                "create table answers " +
                        "(answerID integer primary key, quizID integer, questionID integer ,chosenAnswer text)"
        );
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

        db.execSQL(
                "create table accounts " +
                        "(accountID integer primary key, userName text,password text,type text)"
        );

        db.execSQL(
                "create table questions " +
                        "(questionID integer primary key, questionText text, correctAnswer text, altAnswer1 text, altAnswer2 text, altAnswer3 text, difficulty integer)"
        );

        db.execSQL(
                "create table takenQuizzes " +
                        "(takenQuizID integer primary key, accountID integer)"
        );

        db.execSQL(
                "create table answers " +
                        "(answerID integer primary key, quizID integer, questionID integer ,chosenAnswer text)"
        );

    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS accounts");
        db.execSQL("DROP TABLE IF EXISTS questions");
        db.execSQL("DROP TABLE IF EXISTS takenQuizzes");
        db.execSQL("DROP TABLE IF EXISTS answers");
        onCreate(db);
    }



    public boolean insertAccount (String userName, String password, String type) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("userName", userName);
        contentValues.put("password", password);
        contentValues.put("type", type);
        db.insert("accounts", null, contentValues);
        return true;
    }

    public boolean insertQuestion (String questionText, String correctAnswer, String altAnswer1, String altAnswer2, String altAnswer3, Integer difficulty) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("questionText", questionText);
        contentValues.put("correctAnswer", correctAnswer);
        contentValues.put("altAnswer1", altAnswer1);
        contentValues.put("altAnswer2", altAnswer2);
        contentValues.put("altAnswer3", altAnswer3);
        contentValues.put("difficulty", difficulty);
        db.insert("questions", null, contentValues);
        return true;
    }

    public int insertTakenQuiz (Integer accountID) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("accountID", accountID);
        return (int) db.insert("takenQuizzes", null, contentValues);
    }

    public int insertAnswer (Integer quizID, Integer questionID, String chosenAnswer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("quizID", quizID);
        contentValues.put("questionID", questionID);
        contentValues.put("chosenAnswer", chosenAnswer);
        return (int) db.insert("answers", null, contentValues);
    }



    public Integer deleteAccount (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("accounts",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public Integer deleteQuestion (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("questions",
                "questionID = ? ",
                new String[] { Integer.toString(id) });
    }

    public Integer deleteTakenQuiz (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("takenQuizzes",
                "takenQuizID = ? ",
                new String[] { Integer.toString(id) });
    }

    public Integer deleteAnswer (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("answer",
                "answerID = ? ",
                new String[] { Integer.toString(id) });
    }


    /*
     *  Gets the correct answer of a particular question from the database
     *  input: the int id of a question
     *  output: the String correct answer
     */
    public String getCorrectAnswerByQuestionId(int qID) {
        ArrayList<Question> array_list = new ArrayList<Question>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from questions where questionID = ?", new String[] {Integer.toString(qID)} );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            Question q = new Question(
                    Integer.parseInt(res.getString(res.getColumnIndex(QUESTIONS_COLUMN_QUESTIONID))),
                    res.getString(res.getColumnIndex(QUESTIONS_COLUMN_QUESTIONTEXT)),
                    res.getString(res.getColumnIndex(QUESTIONS_COLUMN_CORRECTANSWER)),
                    res.getString(res.getColumnIndex(QUESTIONS_COLUMN_ALTANSWER1)),
                    res.getString(res.getColumnIndex(QUESTIONS_COLUMN_ALTANSWER2)),
                    res.getString(res.getColumnIndex(QUESTIONS_COLUMN_ALTANSWER3)),
                    res.getInt(res.getColumnIndex(QUESTIONS_COLUMN_DIFFICULTY))

            );

            array_list.add(q);

            res.moveToNext();
        }
        return array_list.get(0).getCorrectAnswer();
    }


    /*
     *  Gets all question from the database
     *  input: N/A
     *  output: Arraylist containing all Question objects
     */
    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> array_list = new ArrayList<Question>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from questions", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            Question q = new Question(
                    Integer.parseInt(res.getString(res.getColumnIndex(QUESTIONS_COLUMN_QUESTIONID))),
                    res.getString(res.getColumnIndex(QUESTIONS_COLUMN_QUESTIONTEXT)),
                    res.getString(res.getColumnIndex(QUESTIONS_COLUMN_CORRECTANSWER)),
                    res.getString(res.getColumnIndex(QUESTIONS_COLUMN_ALTANSWER1)),
                    res.getString(res.getColumnIndex(QUESTIONS_COLUMN_ALTANSWER2)),
                    res.getString(res.getColumnIndex(QUESTIONS_COLUMN_ALTANSWER3)),
                    res.getInt(res.getColumnIndex(QUESTIONS_COLUMN_DIFFICULTY))

            );//Integer.parseInt(res.getString(res.getColumnIndex(QUESTIONS_COLUMN_DIFFICULTY)))

            array_list.add(q);

            res.moveToNext();
        }
        return array_list;
    }


    /*
     *  Gets 5 random question from the database that are of the specified difficulty
     *  input: int difficulty 1, 2, or 3
     *  output: Arraylist containing 5 Question objects
     */
    public ArrayList<Question> getFiveQuestions(int difficulty) {
        ArrayList<Question> array_list = new ArrayList<Question>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res;

        // gets all questions that have the desired difficulty

        if(difficulty==1){
            res =  db.rawQuery( "select * from questions where difficulty = 1", null );
        }
        else if(difficulty==2){
            res =  db.rawQuery( "select * from questions where difficulty = 2", null );
        }
        else{
            res =  db.rawQuery( "select * from questions where difficulty = 3", null );
        }

        res.moveToFirst();

        while(res.isAfterLast() == false){
            Question q = new Question(
                    Integer.parseInt(res.getString(res.getColumnIndex(QUESTIONS_COLUMN_QUESTIONID))),
                    res.getString(res.getColumnIndex(QUESTIONS_COLUMN_QUESTIONTEXT)),
                    res.getString(res.getColumnIndex(QUESTIONS_COLUMN_CORRECTANSWER)),
                    res.getString(res.getColumnIndex(QUESTIONS_COLUMN_ALTANSWER1)),
                    res.getString(res.getColumnIndex(QUESTIONS_COLUMN_ALTANSWER2)),
                    res.getString(res.getColumnIndex(QUESTIONS_COLUMN_ALTANSWER3)),
                    res.getInt(res.getColumnIndex(QUESTIONS_COLUMN_DIFFICULTY))

            );//Integer.parseInt(res.getString(res.getColumnIndex(QUESTIONS_COLUMN_DIFFICULTY)))

            array_list.add(q);

            res.moveToNext();
        }

        // randomly deletes question from list until only 5 remain

        int numToBeDeleted = array_list.size() - 5;

        Random random = new Random();
        int min = 0;
        int max;
        int randomNumber;

        for(int i = 0; i < numToBeDeleted; i++){
            max = array_list.size() -1;
            randomNumber = random.nextInt(max + 1 - min) + min;
            array_list.remove(randomNumber);
        }

        return array_list;
    }


    /*
     *  Gets an Answer object from the database by its ID
     *  input: int answerID
     *  output: Answer object
     */
    public Answer getAnswerByID(int aID) {
        ArrayList<Answer> array_list = new ArrayList<Answer>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from answers where answerID = ?", new String[] {Integer.toString(aID)} );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            Answer a = new Answer(
                    Integer.parseInt(res.getString(res.getColumnIndex(ANSWERS_COLUMN_ANSWERID))),
                    Integer.parseInt(res.getString(res.getColumnIndex(ANSWERS_COLUMN_QUIZID))),
                    Integer.parseInt(res.getString(res.getColumnIndex(ANSWERS_COLUMN_QUESTIONID))),
                    res.getString(res.getColumnIndex(ANSWERS_COLUMN_CHOSENANSWER))
            );

            array_list.add(a);

            res.moveToNext();
        }
        return array_list.get(0);
    }


    /*
     *  Checks if a username is already present in the database
     *  input: String username
     *  output: boolean true if the username is already taken, false if the username is available
     */
    public boolean usernameAlreadyTaken(String un) {
        ArrayList<Account> array_list = new ArrayList<Account>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from accounts where username = ?", new String[] {un} );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            Account a = new Account(
                    Integer.parseInt(res.getString(res.getColumnIndex(ACCOUNTS_COLUMN_ACCOUNTID))),
                    res.getString(res.getColumnIndex(ACCOUNTS_COLUMN_USERNAME)),
                    res.getString(res.getColumnIndex(ACCOUNTS_COLUMN_PASSWORD)),
                    res.getString(res.getColumnIndex(ACCOUNTS_COLUMN_TYPE))
            );

            array_list.add(a);

            res.moveToNext();
        }

        if( array_list.size()==0 ){
            return false; //that username is not already in the database
        }else{
            return true; //that username is taken
        }


    }


    /*
     *  Gets the Account object from the database that matches the entered credentials
     *  input: String username, String password
     *  output: Account object that has the inputted username, returns null if no account with the specified username exists
     */
    public Account verifyLogin(String un, String pw) {
        ArrayList<Account> array_list = new ArrayList<Account>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from accounts where username = ?", new String[] {un} );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            Account a = new Account(
                    Integer.parseInt(res.getString(res.getColumnIndex(ACCOUNTS_COLUMN_ACCOUNTID))),
                    res.getString(res.getColumnIndex(ACCOUNTS_COLUMN_USERNAME)),
                    res.getString(res.getColumnIndex(ACCOUNTS_COLUMN_PASSWORD)),
                    res.getString(res.getColumnIndex(ACCOUNTS_COLUMN_TYPE))
            );

            array_list.add(a);

            res.moveToNext();
        }

        if( array_list.size()==0 ){
            return null;
        }else {
            return array_list.get(0);
        }

    }


    /*
     *  Changes the password of an account in the database
     *  input: String username, String new password
     *  output: boolean true if successful, false if failed
     */
    public boolean changePassword ( String un, String newpassword ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password", newpassword);
        db.update("accounts", contentValues, "username = ? ", new String[] { un } );
        return true;
    }



}

        /*
        mydb.insertQuestion("test1 question text","test1 correct answer", "test1 alt answer 1", "test1 alt answer 2", "test1 alt answer 3",1);
        mydb.insertQuestion("test2 question text","test2 correct answer", "test2 alt answer 1", "test2 alt answer 2", "test2 alt answer 3",1);
        mydb.insertQuestion("test3 question text","test3 correct answer", "test3 alt answer 1", "test3 alt answer 2", "test3 alt answer 3",1);
        mydb.insertQuestion("test4 question text","test4 correct answer", "test4 alt answer 1", "test4 alt answer 2", "test4 alt answer 3",1);
        mydb.insertQuestion("test5 question text","test5 correct answer", "test5 alt answer 1", "test5 alt answer 2", "test5 alt answer 3",1);
        mydb.insertQuestion("test6 question text","test6 correct answer", "test6 alt answer 1", "test6 alt answer 2", "test6 alt answer 3",1);
        mydb.insertQuestion("test7 question text","test7 correct answer", "test7 alt answer 1", "test7 alt answer 2", "test7 alt answer 3",2);
        mydb.insertQuestion("test8 question text","test8 correct answer", "test8 alt answer 1", "test8 alt answer 2", "test8 alt answer 3",2);
        mydb.insertQuestion("test9 question text","test9 correct answer", "test9 alt answer 1", "test9 alt answer 2", "test9 alt answer 3",2);
        mydb.insertQuestion("test10 question text","test10 correct answer", "test10 alt answer 1", "test5 alt answer 2", "test10 alt answer 3",2);
        mydb.insertQuestion("test11 question text","test11 correct answer", "test11 alt answer 1", "test11 alt answer 2", "test11 alt answer 3",2);
        mydb.insertQuestion("test12 question text","test12 correct answer", "test12 alt answer 1", "test12 alt answer 2", "test12 alt answer 3",2);
        mydb.insertQuestion("test13 question text","test13 correct answer", "test13 alt answer 1", "test13 alt answer 2", "test13 alt answer 3",3);
        mydb.insertQuestion("test14 question text","test14 correct answer", "test14 alt answer 1", "test14 alt answer 2", "test14 alt answer 3",3);
        mydb.insertQuestion("test15 question text","test15 correct answer", "test15 alt answer 1", "test15 alt answer 2", "test15 alt answer 3",3);
        mydb.insertQuestion("test16 question text","test16 correct answer", "test16 alt answer 1", "test16 alt answer 2", "test16 alt answer 3",3);
        mydb.insertQuestion("test17 question text","test17 correct answer", "test17 alt answer 1", "test17 alt answer 2", "test17 alt answer 3",3);
        mydb.insertQuestion("test18 question text","test18 correct answer", "test18 alt answer 1", "test18 alt answer 2", "test18 alt answer 3",3);
        */
