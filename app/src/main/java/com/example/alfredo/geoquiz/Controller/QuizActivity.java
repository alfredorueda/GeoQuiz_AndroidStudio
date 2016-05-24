package com.example.alfredo.geoquiz.Controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alfredo.geoquiz.Model.Question;
import com.example.alfredo.geoquiz.R;

public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mResetButton;
    private Button mExitButton;
    private TextView mQuestionTextView;
    int acertadas = 0, incorrectas = 0;

    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true)
    };

    private int mCurrentIndex = 0;

    private void updateQuestion() {
        if (mCurrentIndex < mQuestionBank.length){
            int question = mQuestionBank[mCurrentIndex].getTextResId();
            mQuestionTextView.setText(question);
        }else{
            mQuestionTextView.setText("FIN, has acertado "+acertadas+" y has fallado "+incorrectas);
            altButtonsState(true);
        }
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        int messageResId = 0;

        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
            acertadas++;
        } else {
            messageResId = R.string.incorrect_toast;
            incorrectas++;
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
                .show();

        mCurrentIndex++;
        updateQuestion();
    }

    private void altButtonsState(boolean visibility){
        if (visibility){
            mTrueButton.setVisibility(View.GONE);
            mFalseButton.setVisibility(View.GONE);
            mResetButton.setVisibility(View.VISIBLE);
            mExitButton.setVisibility(View.VISIBLE);
        }else{
            mTrueButton.setVisibility(View.VISIBLE);
            mFalseButton.setVisibility(View.VISIBLE);
            updateQuestion();
            mResetButton.setVisibility(View.GONE);
            mExitButton.setVisibility(View.GONE);
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mResetButton = (Button) findViewById(R.id.reset_button);
        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acertadas = 0;
                incorrectas = 0;
                mCurrentIndex = 0;
                altButtonsState(false);
            }
        });

        mExitButton = (Button) findViewById(R.id.exit_button);
        mExitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        updateQuestion();
    }
}
