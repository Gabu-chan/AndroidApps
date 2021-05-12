package com.example.intentprac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText username, password;
    private Button submit, webButton, mapButton;
    private SharedPreferences mySharedPref;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.et_username);
        password = (EditText) findViewById(R.id.et_password);
        submit = (Button) findViewById(R.id.button);
        webButton = (Button) findViewById(R.id.webbutton);
        mapButton = (Button) findViewById(R.id.openmap);
        mySharedPref = getSharedPreferences("myPref",MODE_PRIVATE);
        final SharedPreferences.Editor editor = mySharedPref.edit();

        String test = mySharedPref.getString("uname", "");
        Log.d("INTENT_DEBUG", test);
        if(!test.equals("")){
            Intent intent = new Intent(MainActivity.this, childActivity.class);
            intent.putExtra("username", test);
            startActivity(intent);
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String validUN = "bob";
                String validPW = "abc";

                String un = username.getText().toString();
                String pass = password.getText().toString();

                if(validUN.equals(un) && pass.equals(validPW)){

                    editor.putString("uname", un);
                    editor.apply();

                    String test = mySharedPref.getString("uname", "");
                    Log.d("INTENT_DEBUG", test);

                    Intent intent = new Intent(MainActivity.this, childActivity.class);
                    intent.putExtra("username", un);
                    intent.putExtra("password", pass);
                    startActivity(intent);

                    username.setText("");
                    password.setText("")
                }



            }
        });
        webButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://google.com";
                Uri webpage = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String geoLocation = "geo:0,0?q=Las Vegas";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri map = Uri.parse(geoLocation);
                intent.setData(map);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });





    }
}