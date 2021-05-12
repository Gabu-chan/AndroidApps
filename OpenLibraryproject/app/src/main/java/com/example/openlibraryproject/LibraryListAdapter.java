package com.example.openlibraryproject;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class LibraryListAdapter extends RecyclerView.Adapter<LibraryListAdapter.LibraryListAdapterViewHolder>{


    private JSONObject jsonObject;
    private Context context;
    private String[] placeholderlist = {"test1","test2","test3","test4"};


    public LibraryListAdapter(String result){

        try{
            jsonObject = new JSONObject(result);
            Log.d("DEBUG_JSON", jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        /////////////////OBJECT -> ARRAY "DOCS" -> OBJECT -> STRING "TITLE"
        // convert result to placeholder
    }

    //FINISHED METHOD BELOW
    @NonNull
    @Override
    public LibraryListAdapter.LibraryListAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        int layoutIdForListItem = R.layout.activity_library_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem, parent, false);
        LibraryListAdapterViewHolder viewHolder = new LibraryListAdapterViewHolder(view);

        return viewHolder;
    }

    @Override //CHANGE
    public void onBindViewHolder(@NonNull LibraryListAdapter.LibraryListAdapterViewHolder holder, int position) {
        JSONArray jsonArray = null;
        //gets position and does stuff
        //check back here later
        //holder.tv_book_title.setText(placeholderlist[position]);
        try {
            jsonArray = jsonObject.getJSONArray("docs");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject bookItem = null;
        String bookName = "N/A";
        JSONArray authorNameArray;
        try {
            bookItem = jsonArray.getJSONObject(position);
            bookName = bookItem.getString("title");
            authorNameArray = bookItem.getJSONArray("author_name");

            holder.tv_book_title.setText(bookName);
            holder.tv_author.setText("Author : " + authorNameArray.get(0).toString());

            holder.ll_main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("DEBUG", "You Clicked Me");
                    Intent intent = new Intent(context, Description.class);//ask why this doesnt work
                    intent.putExtra("desc", "test");//change test
                    context.startActivity(intent);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override //CHANGE
    public int getItemCount() {
        //PASS IN LIBRARY BOOK LIST ARRAY LENGTH HERE
        JSONArray jsonArray = null;
        try {
            jsonArray = jsonObject.getJSONArray("docs");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonArray.length();
    }




    //DO NOT MODIFY (FINISHED CODE BELOW)
    public class LibraryListAdapterViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout ll_main;
        private TextView tv_book_title, tv_author;
        public LibraryListAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            ll_main = (LinearLayout) itemView.findViewById(R.id.ll_main);
            tv_book_title = (TextView) itemView.findViewById(R.id.tv_book_title);
            tv_author = (TextView) itemView.findViewById(R.id.tv_author);
        }
    }

}
