package com.cecs453_final_project.quizmasters;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

	private Button mNewUser;
	private Button mAdlog;
	private Button mUslog;
	private EditText mUser;
	private EditText mPass;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		mNewUser = (Button) findViewById(R.id.new_button);
		mAdlog = (Button) findViewById(R.id.adm_button);
		mUslog = (Button) findViewById(R.id.u_button);
		mUser = (EditText) findViewById(R.id.uname);
		mPass = (EditText) findViewById(R.id.password);


	}

	public void goToRegister(View view) {
		Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
		startActivity(i);
		finish();

	}
}
