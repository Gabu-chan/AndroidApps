package com.example.tvmazequery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private Button searchButton;
    private EditText searchBox;
    private RecyclerView rv_list;
    private TvMazeListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchButton = (Button) findViewById(R.id.searchButton);
        searchBox = (EditText) findViewById(R.id.searchBox);
        rv_list = (RecyclerView) findViewById(R.id.rv_tvmaze_list);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv_list.setLayoutManager(layoutManager);
        rv_list.setHasFixedSize(true);


        //URL tvmazeQuery = null;
        //tvmazeQuery = NetworkUtils.buildURLforTvmaze("Test");

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mainText = searchBox.getText().toString();
                URL tvmazeQuery = NetworkUtils.buildURLforTvmaze(mainText);
                new TvmazeQueryTask().execute(tvmazeQuery);
                }
        });

    }

    public class TvmazeQueryTask extends AsyncTask<URL, Void, String>{

        @Override
        protected String doInBackground(URL... urls) {

            String queryResult = "";
            try {
                queryResult = NetworkUtils.getResponseFromHttpUrl(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();

            }
            Log.d("DEBUG", queryResult);
            return queryResult;
        }

        @Override
        protected void onPostExecute(String s) {
            listAdapter = new TvMazeListAdapter(s);
            rv_list.setAdapter(listAdapter);
        }
    }



}