package com.example.hw2meyermemorygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    private String flips;
    private String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        // Get intent extras
        Intent i = getIntent();
        flips = i.getStringExtra("flips");
        time = i.getStringExtra("time");

        // Set text
        TextView textFlipTotal = findViewById(R.id.textViewFlipsTotal);
        textFlipTotal.setText("Total Flips: " + flips);
        TextView textTimeTotal = findViewById(R.id.textViewTimeTotal);
        textTimeTotal.setText("Total Time: " + time + " seconds");

        // Back button
        Button buttonBack = findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityMenu();
            }
        });
    }

    public void openActivityMenu() {
        Intent i = new Intent(this, MenuActivity.class);
        startActivity(i);
    }
}