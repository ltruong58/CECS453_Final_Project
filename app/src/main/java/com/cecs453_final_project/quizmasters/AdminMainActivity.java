
package com.cecs453_final_project.quizmasters;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

//import com.cecs453_final_project.quizmasters.Fragment.QuestionFragment;

public class AdminMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        final Button btAddQuestion = (Button) findViewById(R.id.btAddNewQuestion) ;
        btAddQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addQuestionActivity = new Intent(AdminMainActivity.this, AddQuestionActivity.class);
                AdminMainActivity.this.startActivity(addQuestionActivity);
            }
        });

        //QuestionFragment questionFragment = (QuestionFragment)
               // getSupportFragmentManager().findFragmentById(R.id.questionFragment);
    }
}

