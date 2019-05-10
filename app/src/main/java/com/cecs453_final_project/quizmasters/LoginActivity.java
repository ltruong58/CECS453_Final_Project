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
    private DBHelper dbHelper;
    static int loginAttempts = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper = new DBHelper(this); //Gets the DBHelper

        debug(); //Creates the "admin" and "user" usernames and passwords for debug purposes.

        final Button btAdminLogin = (Button) findViewById(R.id.btAdminLogin);
        final Button btUserLogin = (Button) findViewById(R.id.btUserLogin);
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final TextView tvNewUser = (TextView) findViewById(R.id.tvNewUser); //Declare widget/UI relationships

        btUserLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputUser = etUsername.getText().toString();
                String inputPassword = etPassword.getText().toString();

                Account account = new Account();

                account = dbHelper.verifyLogin(inputUser, inputPassword); //Finds the account in the database and saves it into account
                // debug_setCred(account, "user", "user", "user");

                if(account == null){ //Notifies the user if the account doesn't exist.
                    Toast.makeText(LoginActivity.this,
                            "Username not found", Toast.LENGTH_LONG).show();
                    loginAttempts += 1;
                } else { //If the account exists the program verifies the values of the user and password fields.
                    boolean inValidated = false;
                    if(account.getPassword().equals(inputPassword) ){ //Verifies the password of the account
                        if(account.getType().equals("user")){         //Checks if the account is a user password to start up the QuizSelectActivity
                            // Change to your UserActivity
                            Intent intent = new Intent(
                                    LoginActivity.this, QuizSelectActivity.class);
                            LoginActivity.this.startActivity(intent);
                            finish(); //Terminate activity.
                            etUsername.setText(""); //Erase username and password fields.
                            etPassword.setText("");
                        } else {
                            Toast.makeText(LoginActivity.this,
                                    "This is not user account", Toast.LENGTH_LONG).show(); //Notify the account of being an admin, not a user.
                            inValidated = true;
                    }   } else {
                        Toast.makeText(LoginActivity.this, //Notify for incorrect password.
                                "Incorrect password", Toast.LENGTH_LONG).show();
                        inValidated = true;
                    }
                    loginAttempts += (inValidated ? 1 : 0); // Resets the counter if the number of trial attempts are exceeded.
                }

                // Allow user to make at most 2 mistakes
                if(loginAttempts == 3) {
                    Toast.makeText(LoginActivity.this,
                            "Exiting the program in "+SHUTDOWN_TIMEOUT_SECONDS+"s", //Tells the user the app is going to close in 3s.)
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
                    }, SHUTDOWN_TIMEOUT); //Closes the app in 3s since the login attempts have been exceeded.
                }
            }
        });

        tvNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Register Activity
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
