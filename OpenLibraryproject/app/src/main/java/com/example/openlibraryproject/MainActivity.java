package com.example.openlibraryproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.nio.channels.AsynchronousChannelGroup;

// will need this for later
// Uri builtUri = Uri.parse(INSERT URL FOR FINDING BOOK THRU ID)
//
//MAKE SECOND LISTITEM AND ADAPTER FOR FAVORITES PAGE
//

public class MainActivity extends AppCompatActivity {

    private EditText et_queryBox;
    private Button button_search;
    private Button button_favorites;
    private String query;
    private RecyclerView rv_title_list;
    private LibraryListAdapter mLibraryListAdapter;
    private JSONObject jsonObject;
    private String result;

    //FINISHED METHOD
    public class OpenLibraryTask extends AsyncTask<URL, Void, String>{

        @Override
        protected String doInBackground(URL... urls) {
            String result = null;
            try {
                result = NetworkUtils.getResponseFromHttp(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d("DEBUG", result);
            return result;
        }

        //for Json stuff
        protected void onPostExecute(String s) {
            Log.d("DEBUG", s);
            result = s;

            mLibraryListAdapter = new LibraryListAdapter(result);
            rv_title_list.setAdapter(mLibraryListAdapter);







        }
    }

    //FINISHED METHOD
    public void makeLibrarySearcgQuery(){
        String query = et_queryBox.getText().toString();
        URL LibraryTitleSearchURL= NetworkUtils.buildURLForBook(query);

        new OpenLibraryTask().execute(LibraryTitleSearchURL);

    }

    //FINISHED METHOD
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_queryBox = (EditText) findViewById(R.id.et_queryBox);
        button_search = (Button) findViewById(R.id.button_search);
        rv_title_list = (RecyclerView) findViewById(R.id.rv_title_list);
        button_favorites = (Button) findViewById(R.id.button_favorites);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv_title_list.setLayoutManager(layoutManager);
        rv_title_list.setHasFixedSize(false);


        button_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeLibrarySearcgQuery();
            }
        });

    }
}