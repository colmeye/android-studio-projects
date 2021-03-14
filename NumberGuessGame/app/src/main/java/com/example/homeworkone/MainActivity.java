package com.example.homeworkone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    private int num1, num2, num3, num4;
    private int score;
    private CountDownTimer timer;

    private void roll() {
        // Set new random numbers
        Random r = new Random();
        num1 = r.nextInt(20);
        num2 = r.nextInt( 20 );
        num3 = r.nextInt(20);
        num4 = r.nextInt( 20 );

        // Change the text in the buttons
        Button btnOne = findViewById(R.id.btnOne);
        btnOne.setText("" + num1);

        Button btnTwo = findViewById(R.id.btnTwo);
        btnTwo.setText("" + num2);

        Button btnThree = findViewById(R.id.btnThree);
        btnThree.setText("" + num3);

        Button btnFour = findViewById(R.id.btnFour);
        btnFour.setText("" + num4);
    }
    
    public void clickBtnOne(View v) {
        check(num1);
    }

    public void clickBtnTwo(View v) {
        check(num2);
    }

    public void clickBtnThree(View v) {
        check(num3);
    }

    public void clickBtnFour(View v) {
        check(num4);
    }

    private void check(int numToCheck)
    {
        double average = (num1 + num2 + num3 + num4) / 4.0;
        double numToCheckDist = Math.abs(average - numToCheck);

        // Find how close each number is to the average
        double num1Dist = Math.abs(average - num1);
        double num2Dist = Math.abs(average - num2);
        double num3Dist = Math.abs(average - num3);
        double num4Dist = Math.abs(average - num4);

        // Find the closest number to the average
        double smallestDistance = Math.min( Math.min(num1Dist, num2Dist), Math.min(num3Dist, num4Dist));

        // Increase or decrease the score if selected number is also the closest
        if (smallestDistance == numToCheckDist) {
            score++;
            Toast toast = Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            if (score > 0) {
                score--;
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


    private void startTimer() {

        TextView txtTime = findViewById(R.id.txtTime);

        timer = new CountDownTimer(11000, 1000) {

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        roll();
        startTimer();

    }


}