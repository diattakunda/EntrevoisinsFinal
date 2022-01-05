package fr.diattakunda.topquiz.Controller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

import fr.diattakunda.topquiz.Model.Question;
import fr.diattakunda.topquiz.Model.QuestionBank;
import fr.diattakunda.topquiz.R;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mTextView;
    private Button mButton1;
    private Button mButton2;
    private Button mButton3;
    private Button mButton4;
    QuestionBank mQuestionBank = generateQuestions();
    private int mRemainingQuestionCount;
    Question mCurrentQuestion;
    private int mScore;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mTextView = findViewById(R.id.game_activity_textview_question);
        mButton1 = findViewById(R.id.game_activity_button_1);
        mButton2 = findViewById(R.id.game_activity_button_2);
        mButton3 = findViewById(R.id.game_activity_button_3);
        mButton4 = findViewById(R.id.game_activity_button_4);

        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
        mButton4.setOnClickListener(this);

        mCurrentQuestion = mQuestionBank.getCurrentQuestions();

        displayQuestion(mCurrentQuestion);

        mRemainingQuestionCount = 3;
        mScore = 0;


    }
    private void displayQuestion(final Question question){
        mTextView.setText(question.getQuestion());
        mButton1.setText(question.getChoiceList().get(0));
        mButton2.setText(question.getChoiceList().get(1));
        mButton3.setText(question.getChoiceList().get(2));
        mButton4.setText(question.getChoiceList().get(3));
    }


    private final QuestionBank generateQuestions(){
        Question question1 = new Question(
                "Who is the creator of Android?",
                Arrays.asList("Andy Rubin",
                        "Steve Wozniak",
                        "Jake Wharton",
                        "Paul Smith"
                ),
                0
        );

        Question question2 = new Question(
                "When did the first man land on the moon?",
                Arrays.asList(
                        "1958",
                        "1962",
                        "1967",
                        "1969"
                ),
                3
        );

        Question question3 = new Question(
                "What is the house number of The Simpsons?",
                Arrays.asList(
                        "42",
                        "101",
                        "666",
                        "742"
                ),
                3
        );

        return new QuestionBank(Arrays.asList(question1, question2, question3));
    }

    @Override
    public void onClick(View view) {
        int index;
        if (view == mButton1) {
            index = 0;
        }else if (view == mButton2) {
            index = 1;
        }else if (view == mButton3) {
            index = 2;
        }else if (view == mButton4) {
            index = 3;
        } else {
            throw new IllegalStateException("Unknown clicked view :" + view);
        }
        if(index == mQuestionBank.getCurrentQuestions().getAnswerIndex()) {
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            mScore++;
        } else {
            Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show();
        }

        mRemainingQuestionCount--;

        if(mRemainingQuestionCount > 0){
            mCurrentQuestion = mQuestionBank.getNextQuestion();
            displayQuestion(mCurrentQuestion);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("Well done!")
                    .setMessage("Your score is " + mScore)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .create()
                    .show();
        }
    }
}