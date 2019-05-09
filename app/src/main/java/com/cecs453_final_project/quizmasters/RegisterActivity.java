package com.cecs453_final_project.quizmasters;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText etRUsername = (EditText) findViewById(R.id.etRUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etRPassword);
        final EditText etRetPassword = (EditText) findViewById(R.id.etRepassword);
        final EditText etFname = (EditText) findViewById(R.id.etFname);
        final EditText etLname = (EditText) findViewById(R.id.etLname);
        final EditText etAge = (EditText) findViewById(R.id.etAge);
        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        final Button btSave = (Button) findViewById(R.id.btSave);
        final Button btCancel = (Button) findViewById(R.id.btCancel);

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username, password, rePassword, fName, lName, email, age;

                username = etRUsername.getText().toString();
                password = etPassword.getText().toString();
                rePassword = etRetPassword.getText().toString();
                fName = etFname.getText().toString();
                lName = etLname.getText().toString();
                email = etEmail.getText().toString();
                age = etAge.getText().toString();
                int iAge = Integer.parseInt(age);
                // Check all fields
                if(username.isEmpty() || password.isEmpty() || rePassword.isEmpty() || fName.isEmpty() ||
                        lName.isEmpty() || email.isEmpty() || age.isEmpty()){
                    Toast.makeText(RegisterActivity.this,
                            "All fields are mandatory.", Toast.LENGTH_LONG).show();
                }

                // Check retype password
                else if(!password.equals(rePassword) ) {
                    Toast.makeText(RegisterActivity.this,
                            "Retype password field needs to match the password field.",
                            Toast.LENGTH_LONG).show();
                }

                // Check age
                else if(iAge < 1 || iAge > 99){
                    Toast.makeText(RegisterActivity.this,
                            "Age needs to be a number between 1 to 99.", Toast.LENGTH_LONG).show();
                }

                File fileR = new File(
                        RegisterActivity.this.getFilesDir().getPath().toString() + "/Lab2File.txt");
                String absolutePath = fileR.getAbsolutePath();
                // Store data in your file
                FileOutputStream fileOut;
                OutputStreamWriter outputWriter = null;
                boolean success = false;
                try {
                    fileOut = new FileOutputStream(fileR, true);
                    outputWriter = new OutputStreamWriter(fileOut);

                    String info = "Username " + username +
                            "\nPassword " + password +
                            "\nFirstname " + fName +
                            "\nLastname " + lName +
                            "\nEmail " + email +
                            "\nAge " + age + "\n";
                    outputWriter.write(info);

                    //display file saved message
                    Toast.makeText(getBaseContext(), "File saved successfully!",
                            Toast.LENGTH_SHORT).show();

                    success = true;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (outputWriter != null) outputWriter.close();
                    } catch(IOException e) {
                        e.printStackTrace();
                    }
                }
                if (success) { //return to login
//                    Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
//                    RegisterActivity.this.startActivity(i);
                    finish();
                }
            }
        });

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create intent to go back to Login Activity
//                Intent loginActivity = new Intent(RegisterActivity.this, LoginActivity.class);
//                RegisterActivity.this.startActivity(loginActivity);
                finish();
            }
        });
    }
}
