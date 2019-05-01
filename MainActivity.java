package com.cecs453_final_project.quizmasters;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb = new DBHelper(this);

        // entering test question (only execute once to populate database)
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

        ArrayList<Question> test_array_list = mydb.getFiveQuestions(1);

        //mydb.deleteQuestion(test_array_list.get(0).getQuestionID());

        Toast.makeText(getApplicationContext(),  Integer.toString(test_array_list.size()),Toast.LENGTH_LONG).show();



    }
}
