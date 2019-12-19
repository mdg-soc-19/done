package com.example.android.done;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
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
    private ArrayList<String > inputdays = new ArrayList<>();
    Button next;


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
        navController = Navigation.findNavController(view);
        next =  view.findViewById(R.id.custom_next);
        viewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        if (monday.isChecked())
        {
            inputdays.add("MONDAY");
        }
        else
        {
            inputdays.add("-1");
        }
        if (tuesday.isChecked())
        {
            inputdays.add("TUESDAY");
        }
        else
        {
            inputdays.add("-1");
        }
        if (wednesday.isChecked())
        {
            inputdays.add("WEDNESDAY");
        }
        else
        {
            inputdays.add("-1");
        }
        if (thursday.isChecked())
        {
            inputdays.add("THURSDAY");
        }
        else
        {
            inputdays.add("-1");
        }
        if (friday.isChecked())
        {
            inputdays.add("FRIDAY");
        }
        else
        {
            inputdays.add("-1");
        }
        if (saturday.isChecked())
        {
            inputdays.add("SATURDAY");
        }
        else
        {
            inputdays.add("-1");
        }
        if (sunday.isChecked())
        {
            inputdays.add("SUNDAY");
        }
        else
        {
            inputdays.add("-1");
        }

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (monday.isChecked() || tuesday.isChecked() || wednesday.isChecked() || thursday.isChecked() || friday.isChecked() || saturday.isChecked() || sunday.isChecked()) {
                    navController.navigate(R.id.action_customize_to_SetReminders);
                    viewModel.setDays(inputdays);

                } else {
                    Toast.makeText(getContext(), "Please select at least one day", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

}
