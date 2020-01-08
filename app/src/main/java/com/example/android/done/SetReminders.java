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
import android.util.Log;
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
    private SharedViewModel viewModel;
    private GoalAdapter adapter;
    private TimePicker reminder;
    private Button save;
    NavController navController;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_set_reminders, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        reminder = view.findViewById(R.id.reminder_time_picker);
        mViewModel = ViewModelProviders.of(getActivity()).get(GoalViewModel.class);
        viewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        Calendar calendar = Calendar.getInstance();
        viewModel.setHour(calendar.get(Calendar.HOUR_OF_DAY));
        viewModel.setMinute(calendar.get(Calendar.MINUTE));
        reminder.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                viewModel.setHour(hourOfDay);
                viewModel.setMinute(minute);

            }
        });
        navController = Navigation.findNavController(view);
        save = view.findViewById(R.id.save_button);

        listenerSetup();

    }

    private void clearFields() {
        ArrayList<String> days = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            days.add("-1");
        }
        viewModel.setGoalName("");
        viewModel.setMotivation("");
        viewModel.setDeadline("");
        viewModel.setDays(days);
        viewModel.setHour(0);
        viewModel.setMinute(0);
    }

    private void listenerSetup() {
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputGoalName = viewModel.getGoalName();
                String inputMotivation = viewModel.getMotivation();
                String inputPriority = viewModel.getPriority();
                String inputDeadline = viewModel.getDeadline();
                ArrayList<String> inputDays = viewModel.getDays();
                int inputHour = viewModel.getHour();
                int inputMinute = viewModel.getMinute();

                Goal goal = new Goal(inputGoalName, inputMotivation, inputPriority, inputDeadline, inputHour, inputMinute, inputDays);
                mViewModel.insert(goal);
                clearFields();
                Toast.makeText(getContext(), "Goal saved successfully", Toast.LENGTH_SHORT).show();
                navController.navigate(R.id.action_SetReminders_to_MyGoalsHome);


            }
        });
    }


}

