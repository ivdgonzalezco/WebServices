package com.webservices.webservices;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CitiesDataParser {

    private String getCity(JSONObject departmentJson)
    {
        String cityName = "";

        Log.d("DepartmentsDataParser","jsonobject ="+departmentJson.toString());


        try {
            if (!departmentJson.isNull("municipio")) {
                cityName = departmentJson.getString("municipio");
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return cityName;

    }
    private ArrayList<String> getCities(JSONArray jsonArray)
    {
        int count = jsonArray.length();
        ArrayList<String> citiesList = new ArrayList<>();
        String cityName = "";

        for(int i = 0; i<count;i++)
        {
            try {
                cityName = getCity((JSONObject) jsonArray.get(i));
                citiesList.add(cityName);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return citiesList;
    }

    public ArrayList<String> parse(String jsonData)
    {
        JSONArray jsonArray = null;

        Log.d("json data", jsonData);

        try {
            jsonArray = new JSONArray(jsonData) ;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return getCities(jsonArray);
    }
}