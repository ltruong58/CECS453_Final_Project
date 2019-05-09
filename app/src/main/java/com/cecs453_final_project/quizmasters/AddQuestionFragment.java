package com.cecs453_final_project.quizmasters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link AddQuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddQuestionFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    public AddQuestionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddQuestionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddQuestionFragment newInstance(String param1, String param2) {
        AddQuestionFragment fragment = new AddQuestionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final EditText etQuestionText = (EditText) getView().findViewById(R.id.etQuestionText);
        final EditText etCorrectAns = (EditText) getView().findViewById(R.id.etCorrectAns);
        final EditText etAlt1Ans1 = (EditText) getView().findViewById(R.id.etAlternate1);
        final EditText etAlt1Ans2 = (EditText) getView().findViewById(R.id.etAlternate2);
        final EditText etAlt1Ans3 = (EditText) getView().findViewById(R.id.etAlternate3);
        final RadioGroup rgDifficulty = (RadioGroup) getView().findViewById(R.id.rgDificulty);
        final Button btCreateQuestion = (Button) getView().findViewById(R.id.btCreateQuestion);

        btCreateQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String question, corAns, alt1Ans, alt2Ans, alt3Ans;
                int lvl;

                question = etQuestionText.getText().toString();
                corAns = etCorrectAns.getText().toString();
                alt1Ans = etAlt1Ans1.getText().toString();
                alt2Ans = etAlt1Ans2.getText().toString();
                alt3Ans = etAlt1Ans3.getText().toString();

                int selected = rgDifficulty.getCheckedRadioButtonId();

                switch (selected){
                    case R.id.rbt1:
                        lvl = 1;
                        break;
                    case R.id.rbt2:
                        lvl = 2;
                        break;
                    case R.id.rbt3:
                        lvl = 3;
                        break;
                }
                DBHelper dbHelper = new DBHelper(getContext());
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_new_question, container, false);
    }

}
