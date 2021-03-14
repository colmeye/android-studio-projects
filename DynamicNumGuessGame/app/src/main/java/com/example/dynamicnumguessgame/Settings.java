package com.example.dynamicnumguessgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class Settings extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinnerBtnCount;
    private Spinner spinnerSeconds;
    private Spinner spinnerRange;

    private RadioGroup radioGroup;
    private RadioButton radioButton;

    private String buttonAmountFinal;
    private String countDownTimeFinal;
    private String numberRangeFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        setupSpinners();

        // Start game button
        Button btnPlay = findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                openActivityGame();
            }
        });
    }

    public void openActivityGame() {

        radioGroup = findViewById(R.id.radioGroup);
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);

        Toast.makeText(this, "Starting " + radioButton.getText() + " Game", Toast.LENGTH_SHORT).show();

        if ( radioButton.getText().equals("Easy") ) {
            buttonAmountFinal = "4";
            countDownTimeFinal = "20";
            numberRangeFinal = "10";
        }
        else if ( radioButton.getText().equals("Medium") ) {
            buttonAmountFinal = "6";
            countDownTimeFinal = "10";
            numberRangeFinal = "20";
        }
        else if ( radioButton.getText().equals("Hard") ) {
            buttonAmountFinal = "8";
            countDownTimeFinal = "5";
            numberRangeFinal = "30";
        }
        else {
            buttonAmountFinal = spinnerBtnCount.getSelectedItem().toString();
            countDownTimeFinal = spinnerSeconds.getSelectedItem().toString();
            numberRangeFinal = spinnerRange.getSelectedItem().toString();
        }

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("buttonAmount", buttonAmountFinal);
        intent.putExtra("countDownTime", countDownTimeFinal);
        intent.putExtra("numberRange", numberRangeFinal);
        startActivity(intent);
    }

    public void setupSpinners() {
        // Setup Button Count Spinner
        spinnerBtnCount = findViewById(R.id.spinnerBtnCount);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.buttonAmounts, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBtnCount.setAdapter(adapter);
        spinnerBtnCount.setOnItemSelectedListener(this);

        // Spinner Seconds Countdown
        spinnerSeconds = findViewById(R.id.spinnerSeconds);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.countdownTimes, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSeconds.setAdapter(adapter1);
        spinnerSeconds.setOnItemSelectedListener(this);

        // Spinner Number Range
        spinnerRange = findViewById(R.id.spinnerRange);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.numberRanges, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRange.setAdapter(adapter2);
        spinnerRange.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //String text = parent.getItemAtPosition(position).toString();
        //Toast.makeText(parent.getContext(), text + " selected", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}