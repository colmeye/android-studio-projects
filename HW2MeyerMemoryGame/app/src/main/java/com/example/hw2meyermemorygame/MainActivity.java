package com.example.hw2meyermemorygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Table setup
    private TableLayout tl;
    private int tableRowCount;
    private int tableColCount;

    // Card setup
    private Button[][] cardArray;
    private Button[][] matchArray;
    private int matchCount;

    // Card clicks
    private Button selectedCardOne = null;
    private Button selectedCardTwo = null;
    private int flips = 0;

    // Emojis
    private int[] emojiUnicodes = new int[]{0x1F34C, 0x1F34D, 0x1F354, 0x1F355, 0x1F357, 0x1F358, 0x1F35F};
    private String getEmojiByUnicode(int unicode){
        return new String(Character.toChars(unicode));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Table setup
        tl = findViewById(R.id.tableOfCards);
        tableRowCount = 3;
        tableColCount = 4;

        // Text setup
        TextView flipsText = findViewById(R.id.textViewFlips);
        flipsText.setText("Flips: " + flips);

        // Card setup
        cardArray = new Button[tableRowCount][tableColCount];
        generateCards();

        // Click listener
        cardClickListener();
    }

    private void cardClickListener() {

        // Click any card listener
        for (int i = 0; i < tableRowCount; i++) {
            for (int q = 0; q < tableColCount; q++) {

                int finalI = i;
                int finalQ = q;

                cardArray[i][q].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        // Increase flips
                        flips++;
                        TextView flipsText = findViewById(R.id.textViewFlips);
                        flipsText.setText("Flips: " + flips);

                        // Select the card
                        if (selectedCardOne == null) {
                            // no first card, so set first card
                            selectedCardOne = cardArray[finalI][finalQ];
                            selectedCardOne.setBackgroundColor(Color.YELLOW);
                        }
                        else if (selectedCardTwo == null) {

                            // no second card, so set second card
                            selectedCardTwo = cardArray[finalI][finalQ];
                            selectedCardTwo.setBackgroundColor(Color.YELLOW);

                            // Check if a match!
                            if (checkMatch(selectedCardOne, selectedCardTwo)) {
                                // They are a match, so disable
                                selectedCardOne.setBackgroundColor(Color.GREEN);
                                selectedCardTwo.setBackgroundColor(Color.GREEN);
                                selectedCardOne.setEnabled(false);
                                selectedCardTwo.setEnabled(false);
                            }
                            else {
                                // Not a match, make red
                                selectedCardOne.setBackgroundColor(Color.RED);
                                selectedCardTwo.setBackgroundColor(Color.RED);
                            }

                            // Check if the game is finished!
                            if (checkFinished()) {
                                openActivityScore();
                            }
                        }
                        else {
                            // Reset background colors
                            selectedCardOne.setBackgroundColor(Color.LTGRAY);
                            selectedCardTwo.setBackgroundColor(Color.LTGRAY);
                            // Clear selection
                            selectedCardOne = null;
                            selectedCardTwo = null;
                            // Select new card
                            selectedCardOne = cardArray[finalI][finalQ];
                            selectedCardOne.setBackgroundColor(Color.YELLOW);
                        }

                    }
                });
            }
        }
    }

    private boolean checkMatch(Button cardOne, Button cardTwo) {
        for (int i = 0; i < matchCount; i++) {

            if (matchArray[i][0].equals(cardOne) && matchArray[i][1].equals(cardTwo)) {
                return true;
            }
            else if (matchArray[i][1].equals(cardOne) && matchArray[i][0].equals(cardTwo)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkFinished() {
        // If a card is enabled, the game isn't over
        for (int i = 0; i < tableRowCount; i++) {
            for (int q = 0; q < tableColCount; q++) {
                if (cardArray[i][q].isEnabled()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void openActivityScore() {
        Intent i = new Intent(this, ScoreActivity.class);
        startActivity(i);
    }


    private void generateCards() {

        int buttonCount = tableRowCount * tableColCount;
        matchCount = buttonCount / 2;

        matchArray = new Button[ matchCount ][2];
        int matchCounter = 0;
        int pairCounter = 0;

        // For each row
        for (int i = 0; i < tableRowCount; i++) {

            // Create a new row and add it to the table!
            TableRow tr = new TableRow(this);
            tl.addView(tr);

            // For each column
            for (int q = 0; q < tableColCount; q++) {

                // Create a button for the row and column
                cardArray[i][q] = new Button(this);
                cardArray[i][q].setBackgroundColor(Color.LTGRAY);

                // Pair that button with another
                if (pairCounter > 1) {
                    pairCounter = 0;
                    matchCounter++;
                }
                matchArray[matchCounter][pairCounter] = cardArray[i][q];
                pairCounter++;

                // Add that button to the current row
                tr.addView(cardArray[i][q]);
            }

        }

        Button[][] shuffledArray = shuffle(matchArray);

        for (int i = 0; i < matchCount; i++) {
            shuffledArray[i][0].setText( getEmojiByUnicode( emojiUnicodes[i] ) );
            shuffledArray[i][1].setText( getEmojiByUnicode( emojiUnicodes[i] ) );
        }


    }


    public Button[][] shuffle(Button[][] a) {
        Random random = new Random();

        for (int i = a.length - 1; i > 0; i--) {
            for (int j = a[i].length - 1; j > 0; j--) {
                int m = random.nextInt(i + 1);
                int n = random.nextInt(j + 1);

                Button temp = a[i][j];
                a[i][j] = a[m][n];
                a[m][n] = temp;
            }
        }

        return a;
    }



}

