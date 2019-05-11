package com.cecs453_final_project.quizmasters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display questions
 * TODO: Replace the implementation with code for your data type.
 */
public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {

    private final List<Question> mQuestions;
    private int max_length;

    public QuestionAdapter() {
        mQuestions = new ArrayList<>();
    }

    @Override
    public QuestionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_question, parent, false);
        max_length = parent.getContext().getResources().getInteger(R.integer.max_max_length) - 3;

        return new QuestionAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final QuestionAdapter.ViewHolder holder, final int position) {
        holder.mItem = mQuestions.get(position);
        holder.question.setText(modifyString(holder.mView, mQuestions.get(position).getQuestionText()));
        holder.btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Delete question
                DBHelper dbHelper = new DBHelper(v.getContext());
                 dbHelper.deleteQuestion(holder.mItem.getQuestionID());
                 mQuestions.remove(position);
                notifyDataSetChanged();
            }
        });

        holder.question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), QuestionDetailsActivity.class);
                intent.putExtra("id", holder.mItem.getQuestionID());
                view.getContext().startActivity(intent);
                ((Activity) view.getContext()).finish();
            }
        });

    }

    private String modifyString(View v, String originalString) {
//        System.out.println("max length: " + max_length);
        if (originalString.length() > max_length){
            return v.getContext().getString(R.string.too_long_text, originalString.substring(0, max_length));
        } else return originalString;
    }

    public void addItem(Question question) {
        mQuestions.add(question);
        notifyDataSetChanged();
    }

    public void addItems(List<Question> questions) {
        mQuestions.addAll(questions);
        notifyDataSetChanged();
    }

    public void clearAll() {
        mQuestions.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mQuestions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView question;
        public final ImageButton btDelete;
        public Question mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            question = (TextView) view.findViewById(R.id.tvQuestion);
            btDelete = (ImageButton) view.findViewById(R.id.iBtDelete);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + question.getText() + "'";
        }
    }
}