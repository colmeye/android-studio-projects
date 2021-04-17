package com.example.hw3meyerenhancedmemorygame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        playListener();
    }

    private void playListener() {
        // Button to start the game
        Button btnPlay = findViewById(R.id.buttonPlay);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                openActivityMain();
            }
        });
    }

    public void openActivityMain() {
        Intent i = new Intent(this, com.example.hw3meyerenhancedmemorygame.MainActivity.class);
        startActivity(i);
    }
}