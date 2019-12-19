package com.example.android.done;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;


public class Customize extends Fragment {


    SharedViewModel viewModel;
    NavController navController;
    private CheckBox monday;
    private CheckBox tuesday;
    private CheckBox wednesday;
    private CheckBox thursday;
    private CheckBox friday;
    private CheckBox saturday;
    private CheckBox sunday;
    private ArrayList<String> input = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_customize, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        monday = view.findViewById(R.id.monday);
        tuesday = view.findViewById(R.id.tuesday);
        wednesday = view.findViewById(R.id.wednesday);
        thursday = view.findViewById(R.id.thursday);
        friday = view.findViewById(R.id.friday);
        saturday = view.findViewById(R.id.saturday);
        sunday = view.findViewById(R.id.sunday);

        if (monday.isChecked()) {
            input.add("MONDAY");
        } else {
            input.add("-1");
        }
        if (tuesday.isChecked()) {
            input.add("TUESDAY");
        } else {
            input.add("-1");
        }
        if (wednesday.isChecked()) {
            input.add("WEDNESDAY");
        } else {
            input.add("-1");
        }
        if (thursday.isChecked()) {
            input.add("THURSDAY");
        } else {
            input.add("-1");
        }
        if (friday.isChecked()) {
            input.add("FRIDAY");
        } else {
            input.add("-1");
        }
        if (saturday.isChecked()) {
            input.add("SATURDAY");
        } else {
            input.add("-1");
        }
        if (sunday.isChecked()) {
            input.add("SUNDAY");
        } else {
            input.add("-1");
        }

        navController = Navigation.findNavController(view);
        Button next = (Button) view.findViewById(R.id.custom_next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (monday.isChecked() || tuesday.isChecked() || wednesday.isChecked() || thursday.isChecked() || friday.isChecked() || saturday.isChecked() || sunday.isChecked()) {
                    navController.navigate(R.id.action_customize_to_SetReminders);
                    Log.v("goal name", viewModel.getGoalName());
                    Log.v("motivation ", viewModel.getMotivation());
                    Log.v("deadline", viewModel.getDeadline());


                } else {
                    Toast.makeText(getContext(), "Please select at least one day", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

}
