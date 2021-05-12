package com.example.todo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv_todo_list;
    private FloatingActionButton fab_add_task;
    private Task[] tasks;// remove later
    private TodoListAdapter mTodoListAdapter;
    private SharedPreferences mySharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mySharedPref = getSharedPreferences("myPref",MODE_PRIVATE);
        String todoList = mySharedPref.getString("todoList", "");
        try {
            if (todoList.equals("") ){
                Task.jsonArray = new JSONArray();
            }
            else {
                Task.jsonArray = new JSONArray(todoList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("DEBUG", "todoList" + Task.jsonArray.length());

        rv_todo_list = (RecyclerView) findViewById(R.id.rv_todo_list);
        fab_add_task = (FloatingActionButton) findViewById(R.id.fab_add_task);

        fab_add_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("DEBUG", "+ button clicked");
                Intent intent = new Intent(MainActivity.this, todoSettings.class);

                intent.putExtra("status", "Add");

                startActivity(intent);


            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv_todo_list.setLayoutManager(layoutManager);
        rv_todo_list.setHasFixedSize(true);

        mTodoListAdapter = new TodoListAdapter();
        rv_todo_list.setAdapter(mTodoListAdapter);

                

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int pos = viewHolder.getAdapterPosition();
                Log.d("DEBUG", "SWIPED : " + pos);
                Task.jsonArray.remove(pos);
                final SharedPreferences.Editor editor = mySharedPref.edit();
                editor.putString("todoList", Task.jsonArray.toString());
                editor.apply();
                mTodoListAdapter.notifyDataSetChanged();
            }
        }).attachToRecyclerView(rv_todo_list);


    }
}