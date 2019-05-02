package com.cecs453_final_project.quizmasters;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    public EditText username;
    public EditText password;
    public EditText repass;
    public EditText age;
    public Button register;
    public Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        back = (Button) findViewById(R.id.back);
        register = (Button) findViewById(R.id.register);
        age = (EditText) findViewById(R.id.age);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.pass);
        repass = (EditText) findViewById(R.id.repass);


    }

    public void goBack(View view){
        Intent i = new Intent (RegisterActivity.this, LoginActivity.class);
        startActivity(i);
        finish();
    }
}
