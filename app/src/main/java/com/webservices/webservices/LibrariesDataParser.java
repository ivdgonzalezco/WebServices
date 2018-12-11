package com.webservices.webservices;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LibrariesDataParser {

    private Library getCity(JSONObject departmentJson)
    {
        String libraryName = "";
        String libraryCity = "";
        String libraryDepartment = "";
        String libraryAddress = "";
        String libraryState = "";
        String libraryNature = "";
        String libraryType = "";
        String libraryPhone = "";

        Log.d("DepartmentsDataParser","jsonobject ="+departmentJson.toString());


        try {
            if (!departmentJson.isNull("nombre_de_la_biblioteca")) {
                libraryName = departmentJson.getString("nombre_de_la_biblioteca");
            }
            if (!departmentJson.isNull("municipio")) {
                libraryCity = departmentJson.getString("municipio");
            }
            if (!departmentJson.isNull("departamento")) {
                libraryDepartment = departmentJson.getString("departamento");
            }
            if (!departmentJson.isNull("direcci_n_de_la_biblioteca")) {
                libraryAddress = departmentJson.getString("direcci_n_de_la_biblioteca");
            }
            if (!departmentJson.isNull("estado_de_la_biblioteca")) {
                libraryState = departmentJson.getString("estado_de_la_biblioteca");
            }
            if (!departmentJson.isNull("naturaleza_de_la_biblioteca")) {
                libraryNature = departmentJson.getString("naturaleza_de_la_biblioteca");
            }
            if (!departmentJson.isNull("tipo_de_biblioteca")) {
                libraryType = departmentJson.getString("tipo_de_biblioteca");
            }
            if (!departmentJson.isNull("tel_fonos_de_contacto")) {
                libraryPhone = departmentJson.getString("tel_fonos_de_contacto");
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return new Library(libraryName, libraryCity, libraryDepartment, libraryAddress, libraryState, libraryNature, libraryType, libraryPhone);

    }
    private ArrayList<Library> getCities(JSONArray jsonArray)
    {
        int count = jsonArray.length();
        ArrayList<Library> citiesList = new ArrayList<>();

        for(int i = 0; i<count;i++)
        {
            try {
                Library library = getCity((JSONObject) jsonArray.get(i));
                citiesList.add(library);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return citiesList;
    }

    public ArrayList<Library> parse(String jsonData)
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
