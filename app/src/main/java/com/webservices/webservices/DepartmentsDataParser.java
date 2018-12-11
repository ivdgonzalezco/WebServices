package com.webservices.webservices;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DepartmentsDataParser {

    private String getDepartment(JSONObject departmentJson)
    {
        String departmentName = "";

        Log.d("DepartmentsDataParser","jsonobject ="+departmentJson.toString());


        try {
            if (!departmentJson.isNull("departamento")) {
                departmentName = departmentJson.getString("departamento");
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return departmentName;

    }
    private ArrayList<String> getDepartments(JSONArray jsonArray)
    {
        int count = jsonArray.length();
        ArrayList<String> departmentsList = new ArrayList<>();
        String departmentName = "";

        for(int i = 0; i<count;i++)
        {
            try {
                departmentName = getDepartment((JSONObject) jsonArray.get(i));
                if(!departmentsList.contains(departmentName)){
                    departmentsList.add(departmentName);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return departmentsList;
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
        return getDepartments(jsonArray);
    }
}
