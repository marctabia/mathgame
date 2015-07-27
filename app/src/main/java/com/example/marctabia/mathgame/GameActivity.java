package com.example.marctabia.mathgame;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class GameActivity extends Activity implements View.OnClickListener {

    int correctAnswer;
    Button buttonChoice1;
    Button buttonChoice2;
    Button buttonChoice3;
    TextView textObjPartA;
    TextView textObjPartB;
    TextView textObjScore;
    TextView textObjLevel;

    int currentScore = 0;
    int currentLevel = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        textObjPartA = (TextView)findViewById(R.id.textPartA);
        textObjPartB = (TextView)findViewById(R.id.textPartB);
        textObjScore = (TextView)findViewById(R.id.textScore);
        textObjLevel = (TextView)findViewById(R.id.textLevel);

        buttonChoice1 = (Button)findViewById(R.id.buttonChoice1);
        buttonChoice2 = (Button)findViewById(R.id.buttonChoice2);
        buttonChoice3 = (Button)findViewById(R.id.buttonChoice3);

        buttonChoice1.setOnClickListener(this);
        buttonChoice2.setOnClickListener(this);
        buttonChoice3.setOnClickListener(this);

        setQuestion();
    }

    @Override
    public void onClick(View view) {
        // declare a new int to be used in all the cases
        int answerGiven = 0;
        switch (view.getId()) {
            case R.id.buttonChoice1:
                answerGiven = Integer.parseInt("" + buttonChoice1.getText());
                break;
            case R.id.buttonChoice2:
                answerGiven = Integer.parseInt("" + buttonChoice2.getText());
                break;
            case R.id.buttonChoice3:
                answerGiven = Integer.parseInt("" + buttonChoice3.getText());
                break;
        }
        updateScoreAndLevel(answerGiven);
        setQuestion();
    }

    void setQuestion() {
        // Generate parts of the question
        int numberRange = currentLevel * 3;
        Random randInt = new Random();

        int partA = randInt.nextInt(numberRange);
        partA++; // we don't want a zero value

        int partB = randInt.nextInt(numberRange);
        partB++;

        correctAnswer = partA * partB;
        int wrongAnswer1 = correctAnswer - 2;
        int wrongAnswer2 = correctAnswer + 2;

        textObjPartA.setText("" + partA);
        textObjPartB.setText("" + partB);

        // set the multi choice buttons
        // a number between 0 and 2
        int buttonLayout = randInt.nextInt(3);
        switch (buttonLayout) {
            case 0:
                buttonChoice1.setText("" + correctAnswer);
                buttonChoice2.setText("" + wrongAnswer1);
                buttonChoice3.setText("" + wrongAnswer2);
                break;
            case 1:
                buttonChoice3.setText("" + correctAnswer);
                buttonChoice2.setText("" + wrongAnswer1);
                buttonChoice1.setText("" + wrongAnswer2);
                break;
            case 2:
                buttonChoice2.setText("" + correctAnswer);
                buttonChoice1.setText("" + wrongAnswer1);
                buttonChoice3.setText("" + wrongAnswer2);
                break;
        }
    }

    void updateScoreAndLevel(int answerGiven) {
        if (isCorrect(answerGiven)) {
            for (int i = 1; i <= currentLevel; i++) {
                currentScore = currentScore + i;
            }

            currentLevel++;
        } else {
            currentScore = 0;
            currentLevel = 1;
        }

        // upsate the two TextViews
        textObjScore.setText("Score: " + currentScore);
        textObjLevel.setText("Level: " + currentLevel);
    }

    boolean isCorrect(int answerGiven) {
        boolean correctTrueOrFalse;
        if (answerGiven == correctAnswer) {
            Toast.makeText(getApplicationContext(), "Well Done!", Toast.LENGTH_LONG).show();
            correctTrueOrFalse = true;
        } else {
            Toast.makeText(getApplicationContext(), "Sorry", Toast.LENGTH_LONG).show();
            correctTrueOrFalse = false;
        }
        return correctTrueOrFalse;
    }

}
