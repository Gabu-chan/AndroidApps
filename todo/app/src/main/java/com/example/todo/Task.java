package com.example.todo;

import org.json.JSONArray;

public class Task {
    private String taskDescrip;
    private int priority;

    public static JSONArray jsonArray;


    public Task(String taskDescrip, int priority) {
        this.taskDescrip = taskDescrip;
        this.priority = priority;
    }

    public String getTaskDescrip() {
        return taskDescrip;
    }

    public int getPriority() {
        return priority;
    }

    public void setTaskDescrip(String taskDescrip) {
        this.taskDescrip = taskDescrip;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }














}
