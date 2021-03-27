package com.example.hw2meyermemorygame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

public class MainActivity extends AppCompatActivity {

    // Table setup
    private TableLayout tl;
    private int tableRowCount;
    private int tableColCount;

    // Card setup
    private Button[][] cardArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Table setup
        tl = findViewById(R.id.tableOfCards);
        tableRowCount = 3;
        tableColCount = 3;

        // Card setup
        cardArray = new Button[tableRowCount][tableColCount];
        generateCards();

    }

    private void generateCards() {

        // For each row
        for (int i = 0; i < tableRowCount; i++) {

            // Create a new row and add it to the table!
            TableRow tr = new TableRow(this);
            tl.addView(tr);

            // For each column
            for (int q = 0; q < tableColCount; q++) {

                // Create a button for the row and column
                cardArray[i][q] = new Button(this);
                cardArray[i][q].setText("Test");

                // Add that button to the current row
                tr.addView(cardArray[i][q]);

            }

        }

    }
}

