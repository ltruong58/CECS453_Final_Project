package com.cecs453_final_project.quizmasters;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cecs453_final_project.quizmasters.Model.Account;


import java.io.FileInputStream;


public class LoginActivity extends AppCompatActivity {
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        dbHelper = new DBHelper(this);
        dbHelper.resetDB();

        dbHelper.insertAccount("admin", "admin", "admin");

        dbHelper.insertQuestion("test1 question text","test1 correct answer", "test1 alt answer 1", "test1 alt answer 2", "test1 alt answer 3",1);
        dbHelper.insertQuestion("test2 question text","test2 correct answer", "test2 alt answer 1", "test2 alt answer 2", "test2 alt answer 3",1);
        dbHelper.insertQuestion("test3 question text","test3 correct answer", "test3 alt answer 1", "test3 alt answer 2", "test3 alt answer 3",1);
        dbHelper.insertQuestion("test4 question text","test4 correct answer", "test4 alt answer 1", "test4 alt answer 2", "test4 alt answer 3",1);
        dbHelper.insertQuestion("test5 question text","test5 correct answer", "test5 alt answer 1", "test5 alt answer 2", "test5 alt answer 3",1);
        dbHelper.insertQuestion("test6 question text","test6 correct answer", "test6 alt answer 1", "test6 alt answer 2", "test6 alt answer 3",1);
        dbHelper.insertQuestion("test7 question text","test7 correct answer", "test7 alt answer 1", "test7 alt answer 2", "test7 alt answer 3",2);
        dbHelper.insertQuestion("test8 question text","test8 correct answer", "test8 alt answer 1", "test8 alt answer 2", "test8 alt answer 3",2);
        dbHelper.insertQuestion("test9 question text","test9 correct answer", "test9 alt answer 1", "test9 alt answer 2", "test9 alt answer 3",2);
        dbHelper.insertQuestion("test10 question text","test10 correct answer", "test10 alt answer 1", "test5 alt answer 2", "test10 alt answer 3",2);
        dbHelper.insertQuestion("test11 question text","test11 correct answer", "test11 alt answer 1", "test11 alt answer 2", "test11 alt answer 3",2);
        dbHelper.insertQuestion("test12 question text","test12 correct answer", "test12 alt answer 1", "test12 alt answer 2", "test12 alt answer 3",2);
        dbHelper.insertQuestion("test13 question text","test13 correct answer", "test13 alt answer 1", "test13 alt answer 2", "test13 alt answer 3",3);
        dbHelper.insertQuestion("test14 question text","test14 correct answer", "test14 alt answer 1", "test14 alt answer 2", "test14 alt answer 3",3);
        dbHelper.insertQuestion("test15 question text","test15 correct answer", "test15 alt answer 1", "test15 alt answer 2", "test15 alt answer 3",3);
        dbHelper.insertQuestion("test16 question text","test16 correct answer", "test16 alt answer 1", "test16 alt answer 2", "test16 alt answer 3",3);
        dbHelper.insertQuestion("test17 question text","test17 correct answer", "test17 alt answer 1", "test17 alt answer 2", "test17 alt answer 3",3);
        dbHelper.insertQuestion("test18 question text","test18 correct answer", "test18 alt answer 1", "test18 alt answer 2", "test18 alt answer 3",3);

        final Button btAdminLogin = (Button) findViewById(R.id.btAdminLogin);
        final Button btUserLogin = (Button) findViewById(R.id.btUserLogin);
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final TextView tvNewUser = (TextView) findViewById(R.id.tvNewUser);
        final int[] loginAttempts = {0};


        btUserLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputUser;
                String inputPassword;

                inputUser = etUsername.getText().toString();
                inputPassword = etPassword.getText().toString();



                Account account = new Account();

                account = dbHelper.verifyLogin(inputUser, inputPassword);
                //account.setUserName("admin");
                //account.setPassword("admin");
                //account.setType("admin");

                if(account.getUserName().isEmpty()){
                    Toast.makeText(LoginActivity.this, "Username not found", Toast.LENGTH_LONG).show();
                }
                else {
                    if(account.getPassword().equals(inputPassword) ){
                        if(account.getType().equals("user")){
                            // Change to your UserActivity
                            Intent intent = new Intent(LoginActivity.this, AdminMainActivity.class);
                            LoginActivity.this.startActivity(intent);
                        }else {
                            Toast.makeText(LoginActivity.this, "This is not user account", Toast.LENGTH_LONG).show();
                        }
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Incorrect password", Toast.LENGTH_LONG).show();
                    }
                }

                // Allow user to make at most 2 mistakes
                if(loginAttempts[0] == 3)
                {
                    Toast.makeText(LoginActivity.this, "Exiting the program in 3s", Toast.LENGTH_LONG).show();

                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                            System.exit(0);
                        }
                    }, 3000);


                }
            }
        });
        btAdminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputUser;
                String inputPassword;

                inputUser = etUsername.getText().toString();
                inputPassword = etPassword.getText().toString();



                Account account = new Account();

                account = dbHelper.verifyLogin(inputUser, inputPassword);
                //account.setUserName("admin");
                //account.setPassword("admin");
                //account.setType("admin");

                if(account == null){
                    Toast.makeText(LoginActivity.this, "Username not found", Toast.LENGTH_LONG).show();
                }
                else {
                    if(account.getPassword().equals(inputPassword) ){
                        if(account.getType().equals("admin")){
                            Intent intent = new Intent(LoginActivity.this, AdminMainActivity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(LoginActivity.this, "This is not admin account", Toast.LENGTH_LONG).show();
                        }
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Incorrect password", Toast.LENGTH_LONG).show();
                    }
                }

                // Allow user to make at most 2 mistakes
                if(loginAttempts[0] == 3)
                {
                    Toast.makeText(LoginActivity.this, "Exiting the program in 3s", Toast.LENGTH_LONG).show();

                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                            System.exit(0);
                        }
                    }, 3000);


                }
            }
        });

        tvNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Create intent to start Register Activity
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });
    }

}
