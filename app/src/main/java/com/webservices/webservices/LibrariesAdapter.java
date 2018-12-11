package com.webservices.webservices;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class LibrariesAdapter extends RecyclerView.Adapter<LibrariesAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Library> libraries;

    public LibrariesAdapter(Context context, ArrayList<Library> libraries) {
        this.context = context;
        this.libraries = libraries;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.library_list, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        final Library library = libraries.get(i);

        myViewHolder.libraryName.setText(library.getName());
        myViewHolder.libraryCity.setText(library.getCity());
        myViewHolder.libraryDepartment.setText(library.getDepartment());
        myViewHolder.libraryAddress.setText(library.getAddress());
        myViewHolder.libraryState.setText(library.getState());
        myViewHolder.libraryNature.setText(library.getNature());
        myViewHolder.libraryType.setText(library.getType());
        myViewHolder.libraryPhone.setText(library.getPhone());
    }

    @Override
    public int getItemCount() {
        return libraries.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView libraryName,
                libraryCity,
                libraryDepartment,
                libraryAddress,
                libraryState,
                libraryNature,
                libraryType,
                libraryPhone;

        public MyViewHolder(View view) {
            super(view);
            libraryName = view.findViewById(R.id.library_name);
            libraryCity = view.findViewById(R.id.library_city);
            libraryDepartment = view.findViewById(R.id.library_department);
            libraryAddress = view.findViewById(R.id.library_address);
            libraryState = view.findViewById(R.id.library_state);
            libraryNature = view.findViewById(R.id.library_nature);
            libraryType = view.findViewById(R.id.library_type);
            libraryPhone = view.findViewById(R.id.library_phone);
        }
    }
}
