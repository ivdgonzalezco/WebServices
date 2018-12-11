package com.webservices.webservices;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.io.IOException;
import java.util.ArrayList;

public class GetCities extends AsyncTask<Object, String, String> {

    private Context context;
    private String citiesData;
    private Spinner spinner;
    private String url;

    @Override
    protected String doInBackground(Object... objects) {
        spinner = (Spinner) objects[0];
        url = (String) objects[1];
        context = (Context) objects[2];

        DownloadURL downloadURL = new DownloadURL();

        try {
            citiesData = downloadURL.readUrl(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return citiesData;
    }

    @Override
    protected void onPostExecute(String s) {
        ArrayList<String> citiesList;
        CitiesDataParser parser = new CitiesDataParser();
        citiesList = parser.parse(s);
        fillSpinnerCities(citiesList);
    }

    private void fillSpinnerCities(ArrayList<String> cityList){

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, cityList);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

}