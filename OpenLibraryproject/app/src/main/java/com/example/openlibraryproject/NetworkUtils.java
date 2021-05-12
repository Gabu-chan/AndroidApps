package com.example.openlibraryproject;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {

    final static String OPENLIBRARY_SEARCH_BASE_URL = "http://openlibrary.org/search.json";
    final static String OPENLIBRARY_SEARCH_QUERY = "q";


    //ADD URL BASES FOR BOOK ID
    public static URL buildURLForBook(String query){

        Uri builtUri = Uri.parse(OPENLIBRARY_SEARCH_BASE_URL)
                .buildUpon()
                .appendQueryParameter(OPENLIBRARY_SEARCH_QUERY, query)
                .build();

        URL url = null;

        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }
    public static URL buildURLForSearch(String query){

        Uri builtUri = Uri.parse(OPENLIBRARY_SEARCH_BASE_URL)
                .buildUpon()
                .appendQueryParameter(OPENLIBRARY_SEARCH_QUERY, query)
                .build();

        URL url = null;

        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }
    public static String getResponseFromHttp(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }


}