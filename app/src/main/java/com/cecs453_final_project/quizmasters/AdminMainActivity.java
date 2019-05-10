
package com.cecs453_final_project.quizmasters;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class AdminMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        final Button btAddQuestion = (Button) findViewById(R.id.btAddNewQuestion) ;
        btAddQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addQuestionActivity = new Intent(
                        AdminMainActivity.this, AddQuestionActivity.class);
                AdminMainActivity.this.startActivity(addQuestionActivity);
                finish();
            }
        });

        final Button btGoBack = (Button) findViewById(R.id.back_to_login_btn);
        btGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(AdminMainActivity.this, LoginActivity.class);
                AdminMainActivity.this.startActivity(back);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(AdminMainActivity.this, LoginActivity.class));
        finish();
    }
}

