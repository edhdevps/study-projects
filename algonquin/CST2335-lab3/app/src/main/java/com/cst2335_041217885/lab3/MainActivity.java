package com.cst2335_041217885.lab3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String PREFS_FILE = "lab3_prefs";
    private static final String KEY_NAME = "saved_name";
    private static final int REQUEST_NAME = 1;

    private EditText editTextName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        Button buttonNext = findViewById(R.id.buttonNext);

        // Load previously saved name from SharedPreferences
        SharedPreferences prefs = getSharedPreferences(PREFS_FILE, MODE_PRIVATE);
        String savedName = prefs.getString(KEY_NAME, null);

        // Only populate the EditText if a name was previously saved
        if (savedName != null) {
            editTextName.setText(savedName);
        }

        // Send the name to NameActivity and wait for a result
        buttonNext.setOnClickListener(view -> {
            String enteredName = editTextName.getText().toString();
            Intent goToName = new Intent(MainActivity.this, NameActivity.class);
            goToName.putExtra(KEY_NAME, enteredName);
            startActivityForResult(goToName, REQUEST_NAME);
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Save the current name to SharedPreferences whenever the activity pauses
        SharedPreferences prefs = getSharedPreferences(PREFS_FILE, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_NAME, editTextName.getText().toString());
        editor.apply();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check that the result came from NameActivity
        if (requestCode == REQUEST_NAME) {
            if (resultCode == 1) {
                // User is happy — close the app
                finish();
            }
            // If resultCode == 0, stay on this screen so user can change the name
        }
    }
}