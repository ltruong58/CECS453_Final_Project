package com.cecs453_final_project.quizmasters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class resultQuizAdapter extends RecyclerView.Adapter<resultQuizAdapter.rQ_viewHolder> {

	class rQ_viewHolder extends RecyclerView.ViewHolder {

		resultQuizAdapter mAdapter;
		TextView questionTV, chosenTV, actualTV;
		rQ_viewHolder(View v, resultQuizAdapter adapter) {
			super(v);
			mAdapter = adapter;
			questionTV = (TextView) v.findViewById(R.id.question_name);
			chosenTV = (TextView) v.findViewById(R.id.chosen_answer);
			actualTV = (TextView) v.findViewById(R.id.actual_answer);
		}
	}

	private final String[] mChosen;
	private final ArrayList<Question> mQuestions;
	private LayoutInflater mInflater;

	resultQuizAdapter(Context context, String[] chosenAnswers, ArrayList<Question> questions) {
		mInflater = LayoutInflater.from(context);
		mChosen = chosenAnswers;
		mQuestions = questions;
	}

	@NonNull @Override
	public rQ_viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View mItemView = mInflater.inflate(R.layout.result_block, parent, false);
		return new rQ_viewHolder(mItemView, this);
	}

	@Override
	public void onBindViewHolder(@NonNull rQ_viewHolder holder, int pos) {
		Question q = mQuestions.get(pos);
		holder.questionTV.setText(q.getQuestionText());
		holder.chosenTV.setText(mChosen[pos]);
		holder.actualTV.setText(q.getCorrectAnswer());
	}

	@Override
	public int getItemCount() {
		return mQuestions.size();
	}

}
