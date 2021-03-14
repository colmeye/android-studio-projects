package com.example.dynamicnumguessgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    int[] numArray;
    private int score;
    private int rightAnswers;
    private int wrongAnswers;
    private CountDownTimer timer;

    // Buttons
    private TableLayout table;
    private Button[] buttonArray;

    // Intent vars
    private int buttonAmount;
    private int countDownTime;
    private int numberRange;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get intent values
        Intent in = getIntent();
        buttonAmount = Integer.parseInt(in.getStringExtra("buttonAmount"));
        countDownTime = Integer.parseInt(in.getStringExtra("countDownTime"));
        numberRange = Integer.parseInt(in.getStringExtra("numberRange"));

        // Get table
        table = (TableLayout) findViewById(R.id.tableButtons);
        buttonArray = new Button[buttonAmount];
        numArray = new int[buttonAmount];

        for (int j = 0; j < buttonAmount; j++) {
            buttonArray[j] = new Button(this);
            buttonArray[j].setText("Sample");
            table.addView( buttonArray[j] );
        }

        // Roll for new nums and start timer
        roll();
        startTimer();

        // End game button
        Button btnEnd = findViewById(R.id.btnEnd);
        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                timer.cancel();
                openActivityResults();
            }
        });

        // Click any button listener
        for (int q = 0; q < buttonAmount; q++) {

            int finalQ = q;
            buttonArray[q].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    check(numArray[finalQ]);
                }
            });
        }

    }


    private void roll() {
        // Set new random numbers
        Random r = new Random();

        // Assign random numbers
        for (int i = 0; i < buttonAmount; i++)
        {
            numArray[i] = r.nextInt(numberRange);
            buttonArray[i].setText("" + numArray[i]);
        }
    }


    private void check(int numToCheck)
    {
        // Determine the average
        double sum = 0;
        for (int b = 0; b < buttonAmount; b++) {
            sum += numArray[b];
        }
        double average = sum / buttonAmount;

        // Determine the distance from the number selected
        double numToCheckDist = Math.abs(average - numToCheck);

        // Find how close each number is to the average
        double numDist[] = new double[buttonAmount];
        for (int f = 0; f < buttonAmount; f++) {
            numDist[f] = Math.abs(average - numArray[f]);
        }

        // Find the closest number to the average
        double smallestDistance = findMin(numDist);

        // Increase or decrease the score if selected number is also the closest
        if (smallestDistance == numToCheckDist) {
            score++;
            rightAnswers++;
            Toast toast = Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            if (score > 0) {
                score--;
                wrongAnswers++;
            }
            Toast toast = Toast.makeText(this, "Wrong!", Toast.LENGTH_SHORT);
            toast.show();
        }

        // Display score
        TextView txtScore = findViewById(R.id.txtScore);
        txtScore.setText("Score: " + score);

        // Restart time
        restartTimer();

        roll();
    }

    double findMin(double[] input) {
        return Arrays.stream(input).min().getAsDouble();
    }


    private void startTimer() {

        TextView txtTime = findViewById(R.id.txtTime);

        timer = new CountDownTimer( (countDownTime+1) * 1000, 1000) {

            public void onTick(long millisUntilFinished) {
                txtTime.setText("Time: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                timerPenalty();
            }
        }.start();
    }

    private void timerPenalty() {
        // Decrease score
        if (score > 0) {
            score--;
            wrongAnswers++;
        }
        TextView txtScore = findViewById(R.id.txtScore);
        txtScore.setText("Score: " + score);

        // Toast
        Toast toast = Toast.makeText(this, "No time!", Toast.LENGTH_SHORT);
        toast.show();

        // Restart timer
        restartTimer();
    }

    private void restartTimer() {
        timer.cancel();
        timer.start();
    }

    public void openActivityResults() {
        Intent intent = new Intent(this, Results.class);
        intent.putExtra("score", Integer.toString(score) );
        intent.putExtra("right", Integer.toString(rightAnswers) );
        intent.putExtra("wrong", Integer.toString(wrongAnswers) );
        startActivity(intent);
    }


}