package com.example.android.done;


import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class SetReminders extends Fragment {

    private GoalViewModel mViewModel;
    private GoalAdapter adapter;
    private TextView goalName;
    private TextView motivation;
    private Spinner priority;
    private CalendarView deadline;
    private CheckBox monday;
    private CheckBox tuesday;
    private CheckBox wednesday;
    private CheckBox thursday;
    private CheckBox friday;
    private CheckBox saturday;
    private CheckBox sunday;
    private TimePicker reminder;
    private ArrayList<String> inputDays = new ArrayList<>();
    private Button save;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_set_reminders, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mViewModel = ViewModelProviders.of(getActivity()).get(GoalViewModel.class);
        save = view.findViewById(R.id.save_button);
        monday = view.findViewById(R.id.monday);
        tuesday = view.findViewById(R.id.tuesday);
        wednesday = view.findViewById(R.id.wednesday);
        thursday = view.findViewById(R.id.thursday);
        friday = view.findViewById(R.id.friday);
        saturday = view.findViewById(R.id.saturday);
        sunday = view.findViewById(R.id.sunday);


        if (monday.isChecked()) {
            inputDays.add("MONDAY");
        } else {
            inputDays.add("-1");
        }
        if (tuesday.isChecked()) {
            inputDays.add("TUESDAY");
        } else {
            inputDays.add("-1");
        }
        if (wednesday.isChecked()) {
            inputDays.add("WEDNESDAY");
        } else {
            inputDays.add("-1");
        }

        if (thursday.isChecked()) {
            inputDays.add("THURSDAY");
        } else {
            inputDays.add("-1");
        }
        if (friday.isChecked()) {
            inputDays.add("FRIDAY");
        } else {
            inputDays.add("-1");
        }
        if (saturday.isChecked()) {
            inputDays.add("SATURDAY");
        } else {
            inputDays.add("-1");
        }
        if (sunday.isChecked()) {
            inputDays.add("SUNDAY");
        } else {
            inputDays.add("-1");
        }
        goalName = view.findViewById(R.id.goal_name_edit_text);
        motivation = view.findViewById(R.id.motivation_edit_text);
        priority = view.findViewById(R.id.spinner1);
        deadline = view.findViewById(R.id.calendar_view);
        reminder = view.findViewById(R.id.reminder_time_picker);

        listenerSetup();
        observerSetup();
        recyclerSetup();

    }

    private void clearFields() {
        goalName.setText("");
        motivation.setText("");
        priority.setSelection(0);
        monday.setSelected(false);
        tuesday.setSelected(false);
        wednesday.setSelected(false);
        thursday.setSelected(false);
        friday.setSelected(false);
        saturday.setSelected(false);
        sunday.setSelected(false);
        //need to reset calendarView and reminder_timepicker
    }

    private void listenerSetup() {
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String inputGoalName = goalName.getText().toString();
                String inputMotivation = motivation.getText().toString();
                String inputPriority = priority.getSelectedItem().toString();
                Long inputDeadline = deadline.getDate();
                int inputHour = reminder.getCurrentHour();
                int inputMinute = reminder.getCurrentMinute();

                Goal goal = new Goal(inputGoalName, inputMotivation, inputPriority, inputDeadline, inputHour, inputMinute, inputDays);
                clearFields();

            }
        });

    }

    private void observerSetup() {
        mViewModel.getAllGoals().observe(this, new Observer<List<Goal>>() {
            @Override
            public void onChanged(List<Goal> goals) {

                adapter.setGoals(goals);


            }
        });
    }

    private void recyclerSetup() {
        RecyclerView recyclerView;

        adapter = new GoalAdapter(R.layout.goal_item);
        recyclerView = getView().findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }
}

