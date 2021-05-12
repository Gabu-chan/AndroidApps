package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class todoSettings extends AppCompatActivity {

    private Button button_addUpdate;
    private EditText et_task;
    private RadioButton rb_p_low, rb_p_med, rb_p_high;
    private SharedPreferences mySharedPref;
    private LinearLayout ll_rc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_settings);

        mySharedPref = getSharedPreferences("myPref",MODE_PRIVATE);
        final SharedPreferences.Editor editor = mySharedPref.edit();

        button_addUpdate = (Button) findViewById(R.id.button_addUpdate);
        et_task = findViewById(R.id.et_task);
        rb_p_low = findViewById(R.id.rb_p_low);
        rb_p_med = findViewById(R.id.rb_p_med);
        rb_p_high = findViewById(R.id.rb_p_high);
        Intent intent = getIntent();

        final String status = intent.getStringExtra("status");
        final int position = intent.getIntExtra("position", -1);
        if(status.equals("Add")){
            button_addUpdate.setText("Add");
            rb_p_med.setChecked(true);
        }
        else if (status.equals("Update")){
            button_addUpdate.setText("Update");
            try {
                et_task.setText(Task.jsonArray.getJSONObject(position).getString("taskDescrip"));
                switch(Task.jsonArray.getJSONObject(position).getInt("priority")){
                    case 1:
                        rb_p_high.setChecked(true);
                        break;
                    case 2:
                        rb_p_med.setChecked(true);
                        break;
                    case 3:
                        rb_p_low.setChecked(true);
                        break;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        button_addUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("DEBUG", "Apply button clicked");
                Log.d("DEBUG",getSelectedRadio() + "");
                String task_title = et_task.getText() + "";
                int priority = getSelectedRadio();

                JSONArray jsonArray = Task.jsonArray;
                if(status.equals("Add")) {
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("taskDescrip", task_title);
                        jsonObject.put("priority", priority);
                        jsonArray.put(jsonObject);
                        Log.d("DEBUG", jsonArray.toString());
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else if(status.equals("Update")){
                    try {
                        JSONObject jsonObject = jsonArray.getJSONObject(position);
                        jsonObject.put("taskDescrip", task_title);
                        jsonObject.put("priority", priority);
                        Log.d("DEBUG", jsonArray.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                editor.putString("todoList", jsonArray.toString());
                editor.apply();



                finish();
            }
        });






    }

    public int getSelectedRadio(){
        if(rb_p_high.isChecked()){
            return 1;
        }
        else if(rb_p_med.isChecked()){
            return 2;
        }
        else if(rb_p_low.isChecked()){
            return 3;
        }
        else{
            return 2;
        }
    }
}