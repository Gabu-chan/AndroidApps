package com.example.githubquery;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private EditText queryBox;
    private Button searchButton;
    private String query;
    private RecyclerView rView;
    private GithubListAdapter mGithubListAdapter;
    private JSONObject jsonObject;
    private String result;
    //private TextView resultBox;


    public class GithubQueryTask extends AsyncTask<URL, Void, String>{


        @Override
        protected String doInBackground(URL... urls) {
            String result = null;
            try {
                result = NetworkUtils.getResponseFromHttpUrl(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            //resultBox.setText(s);
            Log.d("DEBUG", s);
            result = s;

            mGithubListAdapter = new GithubListAdapter(s);
            rView.setAdapter(mGithubListAdapter);
            jsonObject = null;
            try {
                jsonObject = new JSONObject(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }


            JSONArray jsonArray = null;
            try {
                jsonArray = jsonObject.getJSONArray("items");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            for(int i = 0; i < jsonArray.length(); i++){
                try {
                    JSONObject item = jsonArray.getJSONObject(i);
                    String desc = item.get("description").toString();
                    String url = item.get("html_url").toString();
                    Log.d("JSON", desc + ":::" + url);
                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }


            Log.d("JSON", jsonArray.length() + "");




        }
    }

    public void makeGithubSearchQuery(){
        String query = queryBox.getText().toString();
        URL githubSearchURL = NetworkUtils.buildURL(query, "stars");

        new GithubQueryTask().execute(githubSearchURL);

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queryBox = (EditText) findViewById(R.id.queryBox);
        searchButton = (Button) findViewById(R.id.Button1);
        //resultBox = (TextView) findViewById(R.id.resultBox);
        rView = (RecyclerView) findViewById(R.id.rView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rView.setLayoutManager(layoutManager);
        rView.setHasFixedSize(true);




        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeGithubSearchQuery();
            }
        });

    }
}