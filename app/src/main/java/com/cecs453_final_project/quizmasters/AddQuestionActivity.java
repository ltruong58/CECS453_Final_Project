package com.cecs453_final_project.quizmasters;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class AddQuestionActivity extends AppCompatActivity {
    private DBHelper dbHelper;
    private int edit;
    private Question ques;
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

        // edit(= 1) is passed from QuestionDetailsActivity to know that it is for edit an existing question, not to create a new one. default: 0
        edit = getIntent().getIntExtra("edit", 0);
        dbHelper = new DBHelper(AddQuestionActivity.this);

        if(edit != 0) { // Load the existing question
            ques = (Question) getIntent().getSerializableExtra("extra");
            etQuestionText.setText(ques.getQuestionText());
            etCorrectAns.setText(ques.getCorrectAnswer());
            etAlt1Ans1.setText(ques.getAltAnswer1());
            etAlt1Ans2.setText(ques.getAltAnswer2());
            etAlt1Ans3.setText(ques.getAltAnswer3());
            switch(ques.getDifficulty()) {
                case 1: rgDifficulty.check(R.id.rbt1); break;
                case 2: rgDifficulty.check(R.id.rbt2); break;
                case 3: rgDifficulty.check(R.id.rbt3); break;
                default: break;
            }

            // Change the button text from CREATE to EDIT
            btCreateQuestion.setText(R.string.edit);
        }

        // Use for both creating new or updating existing question
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
                    case R.id.rbt1: lvl = 1; break;
                    case R.id.rbt2: lvl = 2; break;
                    case R.id.rbt3: lvl = 3; break;
                }

                if(edit == 0) { // Add new question
                    dbHelper.insertQuestion(question, corAns, alt1Ans, alt2Ans, alt3Ans, lvl);
                } else { // edit question
                    dbHelper.updateQuestion(ques.getQuestionID(), question, corAns, alt1Ans, alt2Ans, alt3Ans, lvl);
                }

                // Go back to AdminMainActivity after question is created or updated
                Intent intent = new Intent(AddQuestionActivity.this, AdminMainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(AddQuestionActivity.this, AdminMainActivity.class));
        finish();
    }
}
