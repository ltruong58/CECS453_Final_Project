package com.cecs453_final_project.quizmasters;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    public EditText username;
    public EditText password;
    public EditText repass;
    public EditText age;
    public Button register;
    public Button back;
    public DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_screen);


        back = (Button) findViewById(R.id.back);
        register = (Button) findViewById(R.id.register);
        age = (EditText) findViewById(R.id.age);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.pass);
        repass = (EditText) findViewById(R.id.repass);
        db = new DBHelper(this);


    }

    public void goBack(View view){
        Intent i = new Intent (RegisterActivity.this, LoginActivity.class);
        startActivity(i);
        finish();
    }

    public void registrate(View view){

        db.insertAccount("austin","austin","admin");
        db.insertAccount("long","long","admin");
        db.insertAccount("brian","brian","admin");
        db.insertAccount("javier","javier","admin");


        if(!db.usernameAlreadyTaken(username.getText().toString())){
            if(Integer.parseInt(age.getText().toString()) > 5 || Integer.parseInt(age.getText().toString()) < 100){
                if(password.getText().toString() != repass.getText().toString()){
                    Toast.makeText(this, "The password fields do not match.", Toast.LENGTH_LONG).show();
                }else{
                    db.insertAccount(username.getText().toString(),password.getText().toString(),"user");
                }
            }
            else{
                Toast.makeText(this, "The age entered is invalid", Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(this, "This username is already taken",Toast.LENGTH_LONG).show();
        }


    }
}
