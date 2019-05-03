package com.cecs453_final_project.quizmasters;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {
	public static String CHOSEN_ANSWERS = "Come get your quiz answers here!";
	public static String QUESTION_ID = "the question id is...";

	private int difficulty;
	private ArrayList<Question> questions;

	private RecyclerView rc;
	private activeQuizAdapter mAdapter;
	private RecyclerView.LayoutManager layoutManager;

	private Button mSubmit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz);

		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			difficulty = bundle.getInt(QuizSelectActivity.SELECT_VALUE, 1);
		} else {
			difficulty = 1;
		}

		// get the questions
		questions = (new DBHelper(this)).getFiveQuestions(difficulty);

		rc = (RecyclerView) findViewById(R.id.active_quiz_recycler_view);
		layoutManager = new LinearLayoutManager(this);
		rc.setLayoutManager(layoutManager);

		mAdapter = new activeQuizAdapter(this, questions);
		rc.setAdapter(mAdapter);

		mSubmit = (Button) findViewById(R.id.submit_btn);
		mSubmit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
//				temp_SubmitToast();

				if (isAdapterQuestionsAnswered()) {
				    Intent resultIntent = new Intent(QuizActivity.this, QuizResultActivity.class);
				    resultIntent.putExtra(CHOSEN_ANSWERS, getAdapterChosenAnswers());
				    resultIntent.putExtra(QUESTION_ID, getQuestionIDs());
				    startActivity(resultIntent);
				    finish();
				}
			}
		});
	}

	String[] getAdapterChosenAnswers() {
		return mAdapter.getChosenAnswers();
	}
	boolean isAdapterQuestionsAnswered() {
		return mAdapter.isAllQuestionsAnswered();
	}
	int[] getQuestionIDs() {
		int[] questionIDs = new int[5];
		for (int i = 0; i < questions.size(); i++) {
			questionIDs[i] = questions.get(i).getQuestionID();
		}
		return questionIDs;
	}

	void temp_SubmitToast() {
		Toast.makeText(this, "Quiz submit", Toast.LENGTH_SHORT).show();
	}
}
