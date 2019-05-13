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


public class LoginActivity extends AppCompatActivity {
    final int SHUTDOWN_TIMEOUT_SECONDS = 3;
    final int SHUTDOWN_TIMEOUT = 1000 * SHUTDOWN_TIMEOUT_SECONDS;
    static String ACCOUNT_ = "account id";
    private DBHelper dbHelper;
    static int loginAttempts = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        dbHelper = new DBHelper(this);

        debug();

        final Button btAdminLogin = (Button) findViewById(R.id.btAdminLogin);
        final Button btUserLogin = (Button) findViewById(R.id.btUserLogin);
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final TextView tvNewUser = (TextView) findViewById(R.id.tvNewUser);

        btUserLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputUser = etUsername.getText().toString();
                String inputPassword = etPassword.getText().toString();

                Account account = new Account();

                account = dbHelper.verifyLogin(inputUser, inputPassword);
                // debug_setCred(account, "user", "user", "user");

                if(account == null){
                    Toast.makeText(LoginActivity.this,
                            "Username not found", Toast.LENGTH_LONG).show();
                    loginAttempts += 1;
                } else {
                    boolean inValidated = false;
                    if(account.getPassword().equals(inputPassword) ){
                        if(account.getType().equals("user")){
                            // Change to your UserActivity
                            Intent intent = new Intent(
                                    LoginActivity.this, QuizSelectActivity.class);
                            intent.putExtra(ACCOUNT_, account.getAccountID());
                            LoginActivity.this.startActivity(intent);
                            finish();
                            etUsername.setText("");
                            etPassword.setText("");
                        } else {
                            Toast.makeText(LoginActivity.this,
                                    "This is not user account", Toast.LENGTH_LONG).show();
                            inValidated = true;
                    }   } else {
                        Toast.makeText(LoginActivity.this,
                                "Incorrect password", Toast.LENGTH_LONG).show();
                        inValidated = true;
                    }
                    loginAttempts += (inValidated ? 1 : 0);
                }

                // Allow user to make at most 2 mistakes
                if(loginAttempts == 3) {
                    Toast.makeText(LoginActivity.this,
                            "Exiting the program in "+SHUTDOWN_TIMEOUT_SECONDS+"s",
                            Toast.LENGTH_LONG).show();

                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                            System.exit(0);
                        }
                    }, SHUTDOWN_TIMEOUT);
                }
            }
        });

        btAdminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputUser = etUsername.getText().toString();
                String inputPassword = etPassword.getText().toString();

                Account account = new Account();

                account = dbHelper.verifyLogin(inputUser, inputPassword);
                // debug_setCred(account, "admin", "admin", "admin");

                if(account == null){
                    Toast.makeText(LoginActivity.this,
                            "Username not found", Toast.LENGTH_LONG).show();
                    loginAttempts += 1;
                } else {
                    boolean inValidated = false;
                    if(account.getPassword().equals(inputPassword) ){
                        if(account.getType().equals("admin")){
                            Intent intent = new Intent(
                                    LoginActivity.this, AdminMainActivity.class);
                            startActivity(intent);
                            finish();
                            etUsername.setText("");
                            etPassword.setText("");
                        } else {
                            Toast.makeText(LoginActivity.this,
                                    "This is not admin account", Toast.LENGTH_LONG).show();
                            inValidated = true;
                    }   } else {
                        Toast.makeText(LoginActivity.this,
                                "Incorrect password", Toast.LENGTH_LONG).show();
                        inValidated = true;
                    }
                    loginAttempts += (inValidated ? 1 : 0);
                }

                // Allow user to make at most 2 mistakes
                if(loginAttempts == 3) {
                    Toast.makeText(LoginActivity.this,
                            "Exiting the program in "+SHUTDOWN_TIMEOUT_SECONDS+"s",
                            Toast.LENGTH_LONG).show();

                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                            System.exit(0);
                        }
                    }, SHUTDOWN_TIMEOUT);
                }
            }
        });

        tvNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create intent to start Register Activity
                Intent registerIntent = new Intent(
                        LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });
    }

    void debug() {
        dbHelper.insertAccount("admin", "admin", "admin");
        dbHelper.insertAccount("user", "user", "user");
    }
    void debug_setCred(Account account, String user, String pass, String type) {
        account.setUserName(user);
        account.setPassword(pass);
        account.setType(type);
    }

}
