package com.example.intentprac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class childActivity extends AppCompatActivity {

    private TextView tv1, tv2;
    private Button logButton;
    private SharedPreferences mySharedPref;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);

        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        logButton = (Button) findViewById(R.id.logout_button);

        mySharedPref = getSharedPreferences("myPref",MODE_PRIVATE);
        final SharedPreferences.Editor editor = mySharedPref.edit();

        Intent intent = getIntent();

        tv1.setText(intent.getStringExtra("username"));
        tv2.setText(intent.getStringExtra("password"));

        logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("uname", "");
                editor.apply();
                finish();
            }
        });
    }
}