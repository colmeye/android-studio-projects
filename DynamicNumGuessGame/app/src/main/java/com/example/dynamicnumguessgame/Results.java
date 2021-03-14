package com.example.dynamicnumguessgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Results extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        // Get the final score, right ans, and wrong ans from last activity and display it
        Intent in = getIntent();
        String scoreStr = in.getStringExtra("score");
        String rightStr = in.getStringExtra("right");
        String wrongStr = in.getStringExtra("wrong");

        TextView txtFinalScore = findViewById(R.id.textFinalScore);
        TextView txtRightAns = findViewById(R.id.textRightAnswers);
        TextView txtWrongAns = findViewById(R.id.textWrongAnswers);

        txtFinalScore.setText("Final Score: " + scoreStr);
        txtRightAns.setText("Right Answers: " + rightStr);
        txtWrongAns.setText("Wrong Answers: " + wrongStr);


        // Back button
        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                openActivitySettings();
            }
        });
    }

    public void openActivitySettings() {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }

}