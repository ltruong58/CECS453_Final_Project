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

       // mydb = new DBHelper(this);

        //mydb.insertQuestion("test1 question text","test1 correct answer", "test1 alt answer 1", "test1 alt answer 2", "test1 alt answer 3");
        //mydb.insertQuestion("test2 question text","test2 correct answer", "test2 alt answer 1", "test2 alt answer 2", "test2 alt answer 3");
        //mydb.insertQuestion("test3 question text","test3 correct answer", "test3 alt answer 1", "test3 alt answer 2", "test3 alt answer 3");
        //mydb.insertQuestion("test4 question text","test4 correct answer", "test4 alt answer 1", "test4 alt answer 2", "test4 alt answer 3");
        //mydb.insertQuestion("test5 question text","test5 correct answer", "test5 alt answer 1", "test5 alt answer 2", "test5 alt answer 3");

        ArrayList<Question> test_array_list = mydb.getAllQuestions();

         //mydb.deleteQuestion();

        //Toast.makeText(getApplicationContext(),  Integer.toString(test_array_list.size()),Toast.LENGTH_LONG).show();



    }
}
