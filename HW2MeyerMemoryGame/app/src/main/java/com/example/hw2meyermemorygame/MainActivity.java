package com.example.hw2meyermemorygame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Table setup
    private TableLayout tl;
    private int tableRowCount;
    private int tableColCount;

    // Card setup
    private Button[][] cardArray;
    private Button[][] matchArray;

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

        // Card setup
        cardArray = new Button[tableRowCount][tableColCount];
        generateCards();
    }

    private void generateCards() {

        int buttonCount = tableRowCount * tableColCount;
        int matchCount = buttonCount / 2;

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

