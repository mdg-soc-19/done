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
        final CheckBox monday = view.findViewById(R.id.monday);
        final CheckBox tuesday = view.findViewById(R.id.tuesday);
        final CheckBox wednesday = view.findViewById(R.id.wednesday);
        final CheckBox thursday = view.findViewById(R.id.thursday);
        final CheckBox friday = view.findViewById(R.id.friday);
        final CheckBox saturday = view.findViewById(R.id.saturday);
        final CheckBox sunday = view.findViewById(R.id.sunday);

        navController = Navigation.findNavController(view);
        Button next = (Button) view.findViewById(R.id.custom_next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (monday.isChecked() || tuesday.isChecked() || wednesday.isChecked() || thursday.isChecked() || friday.isChecked() || saturday.isChecked() || sunday.isChecked()) {
                    navController.navigate(R.id.action_customize_to_SetReminders);
                } else {
                    Toast.makeText(getContext(), "Please select at least one day", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

}
