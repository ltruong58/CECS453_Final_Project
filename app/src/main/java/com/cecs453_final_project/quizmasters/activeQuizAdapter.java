package com.cecs453_final_project.quizmasters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

public class activeQuizAdapter extends RecyclerView.Adapter<activeQuizAdapter.aQ_ViewHolder> {
	public static class aQ_ViewHolder extends RecyclerView.ViewHolder {
		public TextView question_;
		public RadioButton a1, a2, a3, a4;
		public int selected;
		public aQ_ViewHolder(View v) {
			super(v);
			// TODO: uncomment these after 'R.layout.question_block' is completed
			question_ = (TextView) v.findViewById(/*R.id.question_name*/);
			a1 = (RadioButton) v.findViewById(/*R.id.answer_one*/);
			a2 = (RadioButton) v.findViewById(/*R.id.answer_two*/);
			a3 = (RadioButton) v.findViewById(/*R.id.answer_three*/);
			a4 = (RadioButton) v.findViewById(/*R.id.answer_four*/);
			selected = -1;
		}
	}
	private ArrayList<Question> mQuestions
	public activeQuizAdapter(ArrayList<Question> questions) {
		mQuestions = questions;
	}

	@NonNull
	@Override
	public activeQuizAdapter.aQ_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		// TODO: make 'R.layout.question_block'
		View v = (View) LayoutInflater.from(parent.getContext()
				.inflate(R.layout.question_block, parent, false));
		return new aQ_ViewHolder(v);
	}

	@Override
	public void onBindViewHolder(aQ_ViewHolder holder, int pos) {
		Question q = mQuestions.get(pos);

		holder.question_.setText(q.getQuestionName());

		//TODO: change the ids here
		holder.a1.setText(q.getAnswerById(1));
		holder.a2.setText(q.getAnswerById(2));
		holder.a3.setText(q.getAnswerById(3));
		holder.a4.setText(q.getAnswerById(4));

		//TODO: figure out how the radiobutton listener is going to work here
	}

	@Override
	public int getItemCount() {
		return mQuestions.size();
	}
}
