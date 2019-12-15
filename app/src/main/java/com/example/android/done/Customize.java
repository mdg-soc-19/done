package com.example.android.done;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;


public class Customize extends Fragment {


    NavController navController;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_customize, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        navController = Navigation.findNavController(view);
        Button next = (Button) view.findViewById(R.id.custom_next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox monday = (CheckBox) getView().findViewById(R.id.monday);
                CheckBox tuesday = (CheckBox) getView().findViewById(R.id.tuesday);
                CheckBox wednesday = (CheckBox) getView().findViewById(R.id.wednesday);
                CheckBox thursday = (CheckBox) getView().findViewById(R.id.thursday);
                CheckBox friday = (CheckBox) getView().findViewById(R.id.friday);
                CheckBox saturday = (CheckBox) getView().findViewById(R.id.saturday);
                CheckBox sunday = (CheckBox) getView().findViewById(R.id.sunday);
                if (monday.isChecked() || tuesday.isChecked() || wednesday.isChecked() || thursday.isChecked() || friday.isChecked() || saturday.isChecked() || sunday.isChecked()) {
                    navController.navigate(R.id.action_customize_to_SetReminders);
                } else {
                    Toast.makeText(getContext(), "Please select at least one day", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

}
