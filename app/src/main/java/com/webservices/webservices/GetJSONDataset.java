package com.webservices.webservices;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Spinner;

import java.io.IOException;
import java.util.ArrayList;

public class GetJSONDataset extends AsyncTask<Object, String, String> {

    private Context context;
    private String librariesData;
    private String url;

    @Override
    protected String doInBackground(Object... objects) {
        url = (String) objects[0];
        context = (Context) objects[1];

        DownloadURL downloadURL = new DownloadURL();

        try {
            librariesData = downloadURL.readUrl(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return librariesData;
    }

    @Override
    protected void onPostExecute(String s) {
        ArrayList<Library> librariesList;
        LibrariesDataParser parser = new LibrariesDataParser();
        librariesList = parser.parse(s);
        fillRecyclerView(librariesList);
    }

    private void fillRecyclerView(ArrayList<Library> libraries){
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("Libraries", libraries);
        context.startActivity(intent);
    }
}
