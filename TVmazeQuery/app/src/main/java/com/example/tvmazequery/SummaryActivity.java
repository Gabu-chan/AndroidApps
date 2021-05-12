package com.example.tvmazequery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SummaryActivity extends AppCompatActivity {

    private TextView tv_summary_lg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        tv_summary_lg = (TextView) findViewById(R.id.tv_summary_lg);

        Intent intent = getIntent();

        tv_summary_lg.setText(intent.getStringExtra("summary"));

    }
}