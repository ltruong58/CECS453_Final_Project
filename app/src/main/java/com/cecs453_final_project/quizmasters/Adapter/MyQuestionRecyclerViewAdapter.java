package com.cecs453_final_project.quizmasters.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.cecs453_final_project.quizmasters.DBHelper;
import com.cecs453_final_project.quizmasters.Model.Question;
import com.cecs453_final_project.quizmasters.R;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display questions
 * TODO: Replace the implementation with code for your data type.
 */
public class MyQuestionRecyclerViewAdapter extends RecyclerView.Adapter<MyQuestionRecyclerViewAdapter.ViewHolder> {

    private final List<Question> mQuestions;

    public MyQuestionRecyclerViewAdapter() {
        mQuestions = new ArrayList<>();
    }

    @Override
    public MyQuestionRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_question, parent, false);
        return new MyQuestionRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyQuestionRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.mItem = mQuestions.get(position);
        holder.question.setText(mQuestions.get(position).getQuestionText());
        holder.btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Delete question
                DBHelper dbHelper = new DBHelper(v.getContext());
                 dbHelper.deleteQuestion(holder.mItem.getQuestionID());
            }
        });

    }

    public  void addItem(Question route) {
        mQuestions.add(route);
        notifyDataSetChanged();
    }

    public  void addItems(List<Question> routes) {
        mQuestions.addAll(routes);
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
