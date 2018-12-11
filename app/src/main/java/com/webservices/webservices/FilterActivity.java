package com.webservices.webservices;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TabWidget;

import java.text.Normalizer;
import java.util.ArrayList;

public class FilterActivity extends Activity{

    private static String TAG = "FilterActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        final Spinner departmentsSpinner = findViewById(R.id.departments_spinner);
        final Spinner citiesSpinner = findViewById(R.id.cities_spinner);
        final Spinner libraryStatesSpinner = findViewById(R.id.library_state_spinner);
        final Spinner libraryNaturesSpinner = findViewById(R.id.library_nature_spinner);
        final Spinner libraryTypesSpinner = findViewById(R.id.library_type_spinner);
        Button filterButton = findViewById(R.id.filter_button);

        String url = "https://www.datos.gov.co/resource/p95u-vi7k.json";

        Object dataTransfer[] = new Object[3];
        GetDepartments getDepartments = new GetDepartments();

        dataTransfer[0] = departmentsSpinner;
        dataTransfer[1] = url;
        dataTransfer[2] = this;
        getDepartments.execute(dataTransfer);

        departmentsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String citiesUrl = getCitiesUrl(departmentsSpinner.getSelectedItem().toString().replace(" ", "%20")).replace("[\\p{InCombiningDiacriticalMarks}]", "%C3%A9");
                Object dataTransfer2[] = new Object[3];
                GetCities getCities = new GetCities();

                dataTransfer2[0] = citiesSpinner;
                dataTransfer2[1] = citiesUrl;
                dataTransfer2[2] = FilterActivity.this;

                getCities.execute(dataTransfer2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> statesAdapter = ArrayAdapter.createFromResource(this,
                R.array.library_states, android.R.layout.simple_spinner_item);
        statesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        libraryStatesSpinner.setAdapter(statesAdapter);

        ArrayAdapter<CharSequence> naturesAdapter = ArrayAdapter.createFromResource(this,
                R.array.library_natures, android.R.layout.simple_spinner_item);
        naturesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        libraryNaturesSpinner.setAdapter(naturesAdapter);

        ArrayAdapter<CharSequence> typesAdapter = ArrayAdapter.createFromResource(this,
                R.array.library_types, android.R.layout.simple_spinner_item);
        typesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        libraryTypesSpinner.setAdapter(typesAdapter);

        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String department =  cleanString(departmentsSpinner.getSelectedItem().toString().toUpperCase()).replace(" ", "%20");
                String city = cleanString(citiesSpinner.getSelectedItem().toString().toUpperCase()).replace(" ", "%20");
                String libraryState = cleanString(libraryStatesSpinner.getSelectedItem().toString().toUpperCase()).replace(" ", "%20");
                String libraryNature = cleanString(libraryNaturesSpinner.getSelectedItem().toString().toUpperCase()).replace(" ", "%20");
                String libraryType = cleanString(libraryTypesSpinner.getSelectedItem().toString().toUpperCase()).replace(" ", "%20");

                String librariesUrl = getJSONDatasetUrl(department, city, libraryState, libraryNature, libraryType);

                Object dataTransfer3[] = new Object[2];
                GetJSONDataset getCities = new GetJSONDataset();

                dataTransfer3[0] = librariesUrl;
                dataTransfer3[1] = FilterActivity.this;

                getCities.execute(dataTransfer3);

            }
        });

    }

    private String getCitiesUrl(String department)
    {

        StringBuilder citiesUrl = new StringBuilder("https://www.datos.gov.co/resource/p95u-vi7k.json?");
        citiesUrl.append("departamento="+department);

        Log.d(TAG, "Departments url = "+citiesUrl.toString());

        return citiesUrl.toString();
    }

    private String getJSONDatasetUrl(String department, String city, String libraryState, String libraryNature, String libraryType)
    {

        StringBuilder librariesUrl = new StringBuilder("https://www.datos.gov.co/resource/in3j-awgi.json?");

        if (department != null){
            librariesUrl.append("departamento=" + department + "&");
        }
        if (city != null){
            librariesUrl.append("municipio=" + city + "&");
        }
        if (libraryState != null){
            librariesUrl.append("estado_de_la_biblioteca=" + libraryState + "&");
        }
        if (libraryNature != null){
            librariesUrl.append("naturaleza_de_la_biblioteca=" + libraryNature + "&");
        }
        if (libraryType != null){
            librariesUrl.append("tipo_de_biblioteca=" + libraryType + "&");
        }

        Log.d(TAG, "Libraries url = "+librariesUrl.toString());

        return librariesUrl.toString();
    }

    public static String cleanString(String texto) {
        texto = Normalizer.normalize(texto, Normalizer.Form.NFD);
        texto = texto.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return texto;
    }
}
