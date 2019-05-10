package com.cecs453_final_project.quizmasters;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class QuestionDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_details);

        DBHelper dbHelper = new DBHelper(this);
        final Question ques;

        final int quesId = getIntent().getIntExtra("id", 0);

        ques = dbHelper.getQuestionById(quesId);

        final TextView tvQuesText = (TextView) findViewById(R.id.tvQuesText);
        final TextView tvCorAns = (TextView) findViewById(R.id.tvCorAns);
        final TextView tvAltAns1 = (TextView) findViewById(R.id.tvAltAns1);
        final TextView tvAltAns2 = (TextView) findViewById(R.id.tvAltAns2);
        final TextView tvAltAns3 = (TextView) findViewById(R.id.tvAltAns3);

        final Button btBack = (Button) findViewById(R.id.btBack);
        final Button btEdit = (Button) findViewById(R.id.btEdit);

        tvQuesText.setText(ques.getQuestionText());
        tvAltAns1.setText(ques.getAltAnswer1());
        tvAltAns2.setText(ques.getAltAnswer2());
        tvAltAns3.setText(ques.getAltAnswer3());
        tvCorAns.setText(ques.getCorrectAnswer());

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuestionDetailsActivity.this, AdminMainActivity.class);
                startActivity(intent);
            }
        });

        btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuestionDetailsActivity.this, AddQuestionActivity.class);
                intent.putExtra("edit", 1);
                intent.putExtra("extra", ques);

                startActivity(intent);
            }
        });
    }
}
