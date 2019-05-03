package com.cecs453_final_project.quizmasters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class QuizResultAdapter extends RecyclerView.Adapter<QuizResultAdapter.rQ_viewHolder> {

	class rQ_viewHolder extends RecyclerView.ViewHolder {

		QuizResultAdapter mAdapter;
		TextView questionTV, chosenTV, actualTV;
		rQ_viewHolder(View v, QuizResultAdapter adapter) {
			super(v);
			mAdapter = adapter;
			questionTV = (TextView) v.findViewById(R.id.question_name);
			chosenTV = (TextView) v.findViewById(R.id.chosen_answer);
			actualTV = (TextView) v.findViewById(R.id.actual_answer);
		}
	}

	private final String[] mQuestions;
	private final String[] mChosen;
	private final ArrayList<Answer> mAnswers;
	private LayoutInflater mInflater;

	QuizResultAdapter(Context context, String[] questions, String[] chosenAnswers, ArrayList<Answer> answers) {
		mInflater = LayoutInflater.from(context);
		mQuestions = questions;
		mChosen = chosenAnswers;
		mAnswers = answers;
	}

	@NonNull @Override
	public rQ_viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View mItemView = mInflater.inflate(R.layout.result_block, parent, false);
		return new rQ_viewHolder(mItemView, this);
	}

	@Override
	public void onBindViewHolder(@NonNull rQ_viewHolder holder, int pos) {
		Answer a = mAnswers.get(pos);
		holder.questionTV.setText(mQuestions[pos]);
		holder.chosenTV.setText(mChosen[pos]);
		holder.actualTV.setText(a.getChosenAnswer());
	}

	@Override
	public int getItemCount() {
		return mAnswers.size();
	}

}