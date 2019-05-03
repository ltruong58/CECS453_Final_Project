package com.cecs453_final_project.quizmasters.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.cecs453_final_project.quizmasters.DBHelper;
import com.cecs453_final_project.quizmasters.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddNewQuestionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddNewQuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddNewQuestionFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AddNewQuestionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddNewQuestionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddNewQuestionFragment newInstance(String param1, String param2) {
        AddNewQuestionFragment fragment = new AddNewQuestionFragment();
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

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
