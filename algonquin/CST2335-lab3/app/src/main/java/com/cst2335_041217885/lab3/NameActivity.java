package com.cst2335_041217885.lab3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class NameActivity extends AppCompatActivity {

    private static final String KEY_NAME = "saved_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        TextView textViewWelcome = findViewById(R.id.textViewWelcome);
        Button buttonThankYou = findViewById(R.id.buttonThankYou);
        Button buttonDontCallMe = findViewById(R.id.buttonDontCallMe);

        // Retrieve the name passed from MainActivity
        Intent incoming = getIntent();
        String userName = incoming.getStringExtra(KEY_NAME);

        // Show personalized welcome message
        textViewWelcome.setText("Welcome " + userName + "!");

        // User is happy — return result code 1 to MainActivity
        buttonThankYou.setOnClickListener(view -> {
            setResult(1);
            finish();
        });

        // User wants to change name — return result code 0 to MainActivity
        buttonDontCallMe.setOnClickListener(view -> {
            setResult(0);
            finish();
        });
    }
}