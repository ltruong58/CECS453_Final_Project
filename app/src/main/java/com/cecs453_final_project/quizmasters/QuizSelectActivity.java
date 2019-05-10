package com.cecs453_final_project.quizmasters;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class QuizSelectActivity extends AppCompatActivity {

	private Button startQuizButton, backButton;
	public static String SELECT_VALUE = "get the selector value";
	private int select;
	private int accountId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz_select);

		accountId = getIntent().getIntExtra(LoginActivity.ACCOUNT_, 0);
//		System.out.println("account id: " + accountId);

		select = 0;
		startQuizButton = (Button) findViewById(R.id.startquiz_btn);

		startQuizButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (select > 0) {
					loadToast(select);
					Intent quizIntent = new Intent(
							QuizSelectActivity.this, QuizActivity.class);
					quizIntent.putExtra(SELECT_VALUE, select);
					quizIntent.putExtra(LoginActivity.ACCOUNT_, accountId);

					startActivity(quizIntent);
					finish();
				} else {
					errorToast();
				}
			}
		});

		backButton = (Button) findViewById(R.id.back_to_login_btn);
		backButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivity(new Intent(
						QuizSelectActivity.this, LoginActivity.class));
				finish();
			}
		});
	}

	public void onRadioButtonClicked(View view) {
		boolean checked = ((RadioButton)view).isChecked();
		switch(view.getId()) {
			case R.id.radiobtn_diff_1:
				if (checked) {select = 1;}
				break;
			case R.id.radiobtn_diff_2:
				if (checked) {select = 2;}
				break;
			case R.id.radiobtn_diff_3:
				if (checked) {select = 3;}
				break;
			default:
				// none are checked
				select = 0;
				errorToast();
				break;
		}

		selectedDiffToast(select);
	}

	void loadToast(int selected) {
		Toast.makeText(this,
				"Loading quiz of difficulty " + selected,
				Toast.LENGTH_SHORT).show();
	}

	void errorToast() {
		Toast.makeText(this,
				"No difficulty has been chosen",
				Toast.LENGTH_SHORT).show();
	}

	void selectedDiffToast(int selected) {
		if (selected > 0) {
			Toast.makeText(this,
					"Difficulty level " + selected + " chosen",
					Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onBackPressed() {
		startActivity(new Intent(QuizSelectActivity.this, LoginActivity.class));
		finish();
	}
}
