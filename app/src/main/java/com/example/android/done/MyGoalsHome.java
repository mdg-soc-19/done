package com.example.android.done;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.PrimaryKey;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

public class MyGoalsHome extends Fragment {

    private GoalViewModel mViewModel;
    private GoalAdapter adapter;
    private TextView goalName;
    private TextView motivation;
    private Spinner priority;
    private CalendarView deadline;
    private TimePicker reminder;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_goals_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        goalName = getView().findViewById(R.id.goa_name_text_view);
        motivation = getView().findViewById(R.id.motivation_edit_text);
        priority = getView().findViewById(R.id.priority_text_view);
        deadline = getView().findViewById(R.id.calendar_view);
        reminder = getView().findViewById(R.id.reminder_time_picker);

        listenerSetup();
        observerSetup();
        recyclerSetup();

    }

    private void clearFields() {
        goalName.setText("");
        motivation.setText("");
    }

    private void listenerSetup() {

        Button saveButton = getView().findViewById(R.id.save_button);


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String inputGoalName = goalName.getText().toString();
                String inputMotivation = motivation.getText().toString();
                String inputPriority = priority.getSelectedItem().toString();
                Long inputDeadline = deadline.getDate();
                int inputHour = reminder.getCurrentHour();
                int inputMinute = reminder.getCurrentMinute();

                if (!inputGoalName.equals("") && !inputMotivation.equals("")) {
                    Goal goal = new Goal(inputGoalName,inputMotivation,inputPriority,inputDeadline,inputHour,inputMinute);
                    mViewModel.insert(goal);
                    clearFields();
                } else {
                    Toast.makeText(getContext(),"Incomplete Information" , Toast.LENGTH_SHORT).show();

                }
            }
        });




    }

    private void observerSetup() {

        mViewModel.getAllGoals().observe(this, new Observer<List<Goal>>() {
            @Override
            public void onChanged(@Nullable final List<Goal> goals) {
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
