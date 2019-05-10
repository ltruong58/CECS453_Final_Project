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
	public static String QUESTIONS_ = "the question is...";
	public static String CORRECT_ANSWERS = "Correct Answers";
	private int difficulty;
	private ArrayList<Question> questions;

	private RecyclerView rc;
	private QuizAdapter mAdapter;
	private RecyclerView.LayoutManager layoutManager;
	private DBHelper dbHelper;

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
		} //Obtain the difficulty from the QuizAdapter. If not received, set easiest difficulty by default.

		// get the questions
		dbHelper = new DBHelper(this);
		questions = dbHelper.getFiveQuestions(difficulty); //Gets five questions from the database based on the difficulty.

		rc = (RecyclerView) findViewById(R.id.active_quiz_recycler_view);
		layoutManager = new LinearLayoutManager(this);
		rc.setLayoutManager(layoutManager);

		mAdapter = new QuizAdapter(this, questions);
		rc.setAdapter(mAdapter); //Set and show question list on screen.

		mSubmit = (Button) findViewById(R.id.submit_btn); //Submit answers.
		mSubmit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
//				temp_SubmitToast();

				if (isAdapterQuestionsAnswered()) {
				    Intent resultIntent = new Intent(
				    		QuizActivity.this, QuizResultActivity.class);

				    Bundle extra = new Bundle(); //Send questions /w answers and correct answers to the result activity.
				    extra.putSerializable(QUESTIONS_, getQuestions());
				    extra.putSerializable(CHOSEN_ANSWERS, getAdapterChosenAnswers());
					extra.putSerializable(CORRECT_ANSWERS, getCorrectAnswer());

				    resultIntent.putExtra("extra", extra);
				    startActivity(resultIntent);
				    finish();
				} else {
					incomplete_Toast(); //Tell the user that the quiz has not been completed.
				}
			}
		});
	}

	int[] getQuestionIDs() {
		int[] questionIDs = new int[5];
		for (int i = 0; i < questions.size(); i++) {
			questionIDs[i] = questions.get(i).getQuestionID(); //Obtain the question ID for every question.
		}
		return questionIDs;
	}
	String[] getQuestions() {
		String[] res = new String[5];
		for (int i = 0; i < questions.size(); i++) { //Obtains the question statements for every question.
			res[i] = questions.get(i).getQuestionText();
		}
		return res;
	}

	String[] getCorrectAnswer() { //Obtains the correct answers from all the questions.
		String[] res = new String[5];
		for (int i = 0; i < questions.size(); i++) {
			res[i] = questions.get(i).getCorrectAnswer();
		}
		return res;
	}
	String[] getAdapterChosenAnswers() {
		return mAdapter.getChosenAnswers();
	}
	boolean isAdapterQuestionsAnswered() {
		return mAdapter.isAllQuestionsAnswered();
	}

	void incomplete_Toast() {
		Toast.makeText(this,
				"Complete all the questions before submitting",
				Toast.LENGTH_SHORT).show();
	}
	void temp_SubmitToast() {
		Toast.makeText(this,"Quiz submit", Toast.LENGTH_SHORT).show();
	}
}
