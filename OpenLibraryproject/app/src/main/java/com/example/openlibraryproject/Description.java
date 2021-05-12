package com.example.openlibraryproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Description extends AppCompatActivity {

    private TextView tv_desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        tv_desc = (TextView) findViewById(R.id.tv_desc);

        Intent intent = getIntent();

        tv_desc.setText(intent.getStringExtra("desc"));
        //API for description is unstable, changing formats in several ways
        //the API documentation says the same thing and tells the developer to try and not use it
    }
}