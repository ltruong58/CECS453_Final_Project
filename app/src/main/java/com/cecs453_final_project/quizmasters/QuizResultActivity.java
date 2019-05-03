package com.cecs453_final_project.quizmasters;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class QuizResultActivity extends AppCompatActivity {

	private RecyclerView rc;
	private resultQuizAdapter mAdapter;
	private RecyclerView.LayoutManager layoutManager;

	private String[] chosenAnswers;
	private int questionID;

	private Button mComplete;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz_result);

		String[] chosenAnswers = new String[5];
		int[] questionIDs = new int[5];

		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			chosenAnswers = bundle.getStringArray(QuizActivity.CHOSEN_ANSWERS);
			questionIDs = bundle.getIntArray(QuizActivity.QUESTION_ID);
		}
		ArrayList<Question> questions = getQuestions(questionIDs);

		rc = (RecyclerView) findViewById(R.id.results_recycler_view);
		layoutManager = new LinearLayoutManager(this);
		rc.setLayoutManager(layoutManager);

		mAdapter = new resultQuizAdapter(this, chosenAnswers, questions);
		rc.setAdapter(mAdapter);

		mComplete = (Button) findViewById(R.id.completion_btn);
		mComplete.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				temp_resultToast();
//				Intent i = new Intent(/*QuizResultActivity.this, ???*/);
//				startActivity(i);
//				finish();
			}
		});
	}

	ArrayList<Question> getQuestions(int[] questionIDs) {
		DBHelper helper = new DBHelper(this);
		return helper.getCertainQuestions(questionIDs);
	}

	void temp_resultToast(){
		Toast.makeText(this, "Quiz completed." , Toast.LENGTH_SHORT).show();
	}
}
