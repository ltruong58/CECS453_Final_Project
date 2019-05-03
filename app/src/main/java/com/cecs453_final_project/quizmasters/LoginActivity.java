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


import java.io.FileInputStream;


public class LoginActivity extends AppCompatActivity {
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper = new DBHelper(this);
        dbHelper.insertAccount("admin", "admin", "admin");


        final Button btAdminLogin = (Button) findViewById(R.id.btAdminLogin);
        final Button btUserLogin = (Button) findViewById(R.id.btUserLogin);
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final TextView tvNewUser = (TextView) findViewById(R.id.tvNewUser);
        final int[] loginAttempts = {0};


        btUserLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btAdminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputUser;
                String inputPassword;

                FileInputStream fin = null;

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
                        if(account.getType().equals("admin")){
//                            Intent intent = new Intent(LoginActivity.this, AdminMainActivity.class);
//                            LoginActivity.this.startActivity(intent);
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

