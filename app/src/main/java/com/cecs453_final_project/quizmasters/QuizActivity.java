package com.cecs453_final_project.quizmasters;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
	private int difficulty;

	private RecyclerView rc;
	private RecyclerView.Adapter mAdapter;
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
		ArrayList<Question> questions = randomFiveQuestions(difficulty);

		rc = (RecyclerView) findViewById(R.id.active_quiz_recycler_view);
		layoutManager = new LinearLayoutManager(this);
		rc.setLayoutManager(layoutManager);

		mAdapter = new activeQuizAdapter(questions);
		rc.setAdapter(mAdapter);

		mSubmit = (Button) findViewById(R.id.submit_btn);
		mSubmit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				//TODO: submit the items from questions somehow

				temp_SubmitToast();
			}
		});
	}

	void temp_SubmitToast() {
		Toast.makeText(this, "Quiz submit", Toast.LENGTH_SHORT).show();
	}
}
