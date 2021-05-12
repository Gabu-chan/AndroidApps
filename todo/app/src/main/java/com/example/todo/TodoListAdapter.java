package com.example.todo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.TodoListViewHolder>{

    private Task[] tasks;
    private Context context;

    public TodoListAdapter(){
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public TodoListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        int layoutIDForListItem = R.layout.todo_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIDForListItem, parent, false);
        TodoListViewHolder viewHolder = new TodoListViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TodoListViewHolder holder, final int position) {

        holder.ll_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("DEBUG", "+ button clicked");
                Intent intent = new Intent(context, todoSettings.class);
                intent.putExtra("status", "Update");
                intent.putExtra("position", position);
                context.startActivity(intent);
            }
        });

        int priority = 0;
        try {
            String taskDescription = Task.jsonArray.getJSONObject(position).getString("taskDescrip");
            priority = Task.jsonArray.getJSONObject(position).getInt("priority");
            holder.tv_task_title.setText(taskDescription);
            holder.tv_priority.setText(priority + "");
        }
        catch (JSONException e){
            e.printStackTrace();
        }


        GradientDrawable priorityBack = (GradientDrawable) holder.tv_priority.getBackground();


        switch(priority){
            case 1:
                priorityBack.setColor(Color.parseColor("#FF5607"));
                break;
            case 2:
                priorityBack.setColor(Color.parseColor("#FF9800"));
                break;
            case 3:
                priorityBack.setColor(Color.parseColor("#FFC100"));
                break;
            default:
                break;
        }

    }

    @Override
    public int getItemCount() {
        return Task.jsonArray.length();
    }


    public class TodoListViewHolder extends RecyclerView.ViewHolder{

        TextView tv_task_title,tv_priority, tv_task_date;
        LinearLayout ll_item;

        public TodoListViewHolder(@NonNull View itemView) {
            super(itemView);

            ll_item = (LinearLayout) itemView.findViewById(R.id.ll_item);
            tv_task_title = (TextView) itemView.findViewById(R.id.tv_task_title);
            tv_priority = (TextView) itemView.findViewById(R.id.tv_priority);
            tv_task_date = (TextView) itemView.findViewById(R.id.tv_task_date);
        }
    }
}
