package com.cecs453_final_project.quizmasters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class activeQuizAdapter extends RecyclerView.Adapter<activeQuizAdapter.aQ_ViewHolder> {

	private static String[] chosenAnswers;
	private final ArrayList<Question> mQuestions;
	private LayoutInflater mInflater;

	static class aQ_ViewHolder extends RecyclerView.ViewHolder {
		TextView question_;
		RadioGroup answerGroup;
		RadioButton a1, a2, a3, a4;
		int selected;
		final activeQuizAdapter mAdapter;

		aQ_ViewHolder(View v, activeQuizAdapter adapter) {
			super(v);
			this.mAdapter = adapter;

			question_ = (TextView) v.findViewById(R.id.question_name);
			answerGroup = (RadioGroup) v.findViewById(R.id.answer_group);
			a1 = (RadioButton) v.findViewById(R.id.answer_one);
			a2 = (RadioButton) v.findViewById(R.id.answer_two);
			a3 = (RadioButton) v.findViewById(R.id.answer_three);
			a4 = (RadioButton) v.findViewById(R.id.answer_four);
			selected = -1;

			answerGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(RadioGroup radioGroup, int rb_id) {
					RadioButton rb = (RadioButton) radioGroup.findViewById(rb_id);
					if (rb.isChecked()) {
						String val = "";
						switch(rb_id) {
							case R.id.answer_one:
								val = (String) rb.getText();
								break;
							case R.id.answer_two:
								val = (String) rb.getText();
								break;
							case R.id.answer_three:
								val = (String) rb.getText();
								break;
							case R.id.answer_four:
								val = (String) rb.getText();
								break;
							default:
								break;
						}
						// get the chosen value set to the location of mPos
						// i.e. (mPos => 0) -> chosenAnswers[0], etc
						int mPos = getLayoutPosition();
						chosenAnswers[mPos] = val;
					}
				}
			});
		}
	}

	activeQuizAdapter(Context context, ArrayList<Question> questions) {
		mInflater = LayoutInflater.from(context);
		mQuestions = questions;

		chosenAnswers = new String[5];
	}

	@NonNull @Override
	public activeQuizAdapter.aQ_ViewHolder
	  onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View mItemView = mInflater.inflate(R.layout.question_block, parent,false);
		return new aQ_ViewHolder(mItemView, this);
	}

	@Override
	public void onBindViewHolder(@NonNull aQ_ViewHolder holder, int pos) {
		Question q = mQuestions.get(pos);

		holder.question_.setText(q.getQuestionText());

		//TODO: change how the answers are obtained
		holder.a1.setText(q.getAnswerById(1));
		holder.a2.setText(q.getAnswerById(2));
		holder.a3.setText(q.getAnswerById(3));
		holder.a4.setText(q.getAnswerById(4));

	}

	@Override
	public int getItemCount() {
		return mQuestions.size();
	}

	public String[] getChosenAnswers() {
		return chosenAnswers;
	}
}
