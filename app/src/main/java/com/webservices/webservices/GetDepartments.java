package com.webservices.webservices;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GetDepartments extends AsyncTask<Object, String, String> {

    private Context context;
    private String departmentsData;
    private Spinner spinner;
    private String url;

    @Override
    protected String doInBackground(Object... objects) {
        spinner = (Spinner) objects[0];
        url = (String) objects[1];
        context = (Context) objects[2];

        DownloadURL downloadURL = new DownloadURL();

        try {
            departmentsData = downloadURL.readUrl(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return departmentsData;
    }

    @Override
    protected void onPostExecute(String s) {
        ArrayList<String> departmentList;
        DepartmentsDataParser parser = new DepartmentsDataParser();
        departmentList = parser.parse(s);
        Log.d("nearbyplacesdata","called parse method");
        fillSpinnerDepartments(departmentList);
    }

    private void fillSpinnerDepartments(ArrayList<String> departmentList){

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, departmentList);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

}
