package com.cecs453_final_project.quizmasters;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class AddQuestionActivity extends AppCompatActivity {
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        final EditText etQuestionText = (EditText) findViewById(R.id.etQuestionText);
        final EditText etCorrectAns = (EditText) findViewById(R.id.etCorrectAns);
        final EditText etAlt1Ans1 = (EditText) findViewById(R.id.etAlternate1);
        final EditText etAlt1Ans2 = (EditText) findViewById(R.id.etAlternate2);
        final EditText etAlt1Ans3 = (EditText) findViewById(R.id.etAlternate3);
        final RadioGroup rgDifficulty = (RadioGroup) findViewById(R.id.rgDificulty);
        final Button btCreateQuestion = (Button) findViewById(R.id.btCreateQuestion);

        btCreateQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String question, corAns, alt1Ans, alt2Ans, alt3Ans;
                int lvl = 1; // default of difficulty is 1

                question = etQuestionText.getText().toString();
                corAns = etCorrectAns.getText().toString();
                alt1Ans = etAlt1Ans1.getText().toString();
                alt2Ans = etAlt1Ans2.getText().toString();
                alt3Ans = etAlt1Ans3.getText().toString();

                int selected = rgDifficulty.getCheckedRadioButtonId();

                switch (selected){
                    case R.id.rbt1:
                        lvl = 1;
                        break;
                    case R.id.rbt2:
                        lvl = 2;
                        break;
                    case R.id.rbt3:
                        lvl = 3;
                        break;
                }

                dbHelper = new DBHelper(AddQuestionActivity.this);
                dbHelper.insertQuestion(question, corAns, alt1Ans, alt2Ans, alt3Ans, lvl);

                Intent intent = new Intent(
                        AddQuestionActivity.this, AdminMainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
