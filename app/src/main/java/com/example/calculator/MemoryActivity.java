package com.example.calculator;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MemoryActivity extends AppCompatActivity {
    private TextView tvMemory;
    public static String LAST_OPERATION = "LAST_OPERATION";
    public static String OPERATION = "OPERATION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);
        tvMemory = findViewById(R.id.tvMemory);
        SharedPreferences memory = getSharedPreferences(LAST_OPERATION, MODE_PRIVATE);
        String tvmem = memory.getString(OPERATION, "");
        tvMemory.setText(tvmem);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}