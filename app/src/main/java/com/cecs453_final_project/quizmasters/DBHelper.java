package com.cecs453_final_project.quizmasters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

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


    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
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
                        "(takenQuizID integer primary key, userName text, takenDate text)"
        );

        db.execSQL(
                "create table answers " +
                        "(answerID integer primary key, quizID integer, questionID integer ,selectedAnswer text)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS accounts");
        db.execSQL("DROP TABLE IF EXISTS questions");
        db.execSQL("DROP TABLE IF EXISTS takenQuizzes");
        db.execSQL("DROP TABLE IF EXISTS pickedQuestions");
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

    public boolean insertQuestion (String questionText, String correctAnswer, String altAnswer1, String altAnswer2, String altAnswer3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("questionText", questionText);
        contentValues.put("correctAnswer", correctAnswer);
        contentValues.put("altAnswer1", altAnswer1);
        contentValues.put("altAnswer2", altAnswer1);
        contentValues.put("altAnswer3", altAnswer1);
        db.insert("questions", null, contentValues);
        return true;
    }

    public boolean insertTakenQuiz (String userName, String password, String type) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("userName", userName);
        contentValues.put("password", password);
        contentValues.put("type", type);
        db.insert("accounts", null, contentValues);
        return true;
    }

    public boolean insertPickedQuestion (String userName, String password, String type) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("userName", userName);
        contentValues.put("password", password);
        contentValues.put("type", type);
        db.insert("pickedQuestions", null, contentValues);
        return true;
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



    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> array_list = new ArrayList<Question>();

        //hp = new HashMap();
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
                    2
            );//Integer.parseInt(res.getString(res.getColumnIndex(QUESTIONS_COLUMN_DIFFICULTY)))

            array_list.add(q);

            res.moveToNext();
        }
        return array_list;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts where id="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        //SQLiteDatabase db = this.getReadableDatabase();
        //int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
        //return numRows;
        return 1;
    }

    public boolean updateContact (Integer id, String name, String phone, String email, String street,String place) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        contentValues.put("street", street);
        contentValues.put("place", place);
        db.update("contacts", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }




}
