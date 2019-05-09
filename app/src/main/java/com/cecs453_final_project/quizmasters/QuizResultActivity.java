package com.cecs453_final_project.quizmasters;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class QuizResultActivity extends AppCompatActivity {

	private RecyclerView rc;
	private QuizResultAdapter mAdapter;
	private RecyclerView.LayoutManager layoutManager;

	private String[] chosenAnswers;
	private String[] questions;
	private String[] correctAnswers;

	private Button mComplete;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz_result);

		chosenAnswers = new String[5];
		questions = new String[5];
		correctAnswers = new String[5];

		Bundle bundle = getIntent().getBundleExtra("extra");
		if (bundle != null) {
			chosenAnswers = (String[]) bundle.getSerializable(QuizActivity.CHOSEN_ANSWERS);
			correctAnswers = (String[]) bundle.getSerializable(QuizActivity.CORRECT_ANSWERS);
			questions = (String[]) bundle.getSerializable(QuizActivity.QUESTIONS_);
		}

		rc = (RecyclerView) findViewById(R.id.results_recycler_view);
		layoutManager = new LinearLayoutManager(this);
		rc.setLayoutManager(layoutManager);

		mAdapter = new QuizResultAdapter(this, questions, chosenAnswers, correctAnswers);
		rc.setAdapter(mAdapter);

		mComplete = (Button) findViewById(R.id.completion_btn);
		mComplete.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				temp_resultToast(correctAnswers, chosenAnswers);
//				Intent i = new Intent(QuizResultActivity.this, QuizSelectActivity.class);
//				startActivity(i);
				finish();
			}
		});
	}

	int countCorrectAnswer(String[] strCorr, String[] strChos){
		int cnt = 0;
		for(int i = 0; i < 5; i++) {
			if(strChos[i].equals(strCorr[i])){
				cnt++;
			}
		}
		return cnt;
	}
	void temp_resultToast(String[] strCorr, String[] strChos){
		Toast.makeText(this,
				"Quiz completed. You got "+countCorrectAnswer(strChos, strCorr)+" correct answers!",
				Toast.LENGTH_SHORT).show();
	}
}
