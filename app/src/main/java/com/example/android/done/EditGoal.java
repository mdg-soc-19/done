package com.example.android.done;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;


public class EditGoal extends Fragment {


    private EditText editGoalName;
    private EditText editMotivation;
    private Spinner editPriority;
    private CalendarView editDeadline;
    private CheckBox editMonday;
    private CheckBox editTuesday;
    private CheckBox editWednesday;
    private CheckBox editThursday;
    private CheckBox editFriday;
    private CheckBox editSaturday;
    private CheckBox editSunday;
    private TimePicker editReminder;
    MyGoalViewModel myGoalViewModel;
    private Button saveChanges;
    SharedViewModel sharedViewModel;
    private GoalViewModel mViewModel;
    NavController navController;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_edit_goal, container, false);

        String[] values =
                {"A", "B", "C", "D"};
        Spinner spinner = (Spinner) v.findViewById(R.id.edit_priority);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        return v;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        myGoalViewModel = ViewModelProviders.of(getActivity()).get(MyGoalViewModel.class);
        mViewModel = ViewModelProviders.of(getActivity()).get(GoalViewModel.class);
        sharedViewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        navController = Navigation.findNavController(view);
        Goal myGoal = myGoalViewModel.getMyGoal();
        editGoalName = view.findViewById(R.id.edit_goal_name);
        editGoalName.setText(myGoal.getGoalName());
        editMotivation = view.findViewById(R.id.edit_motivation);
        editMotivation.setText(myGoal.getMotivation());
        editPriority = view.findViewById(R.id.edit_priority);
        int selection;
        if (myGoal.getPriority().equals("B")) {
            selection = 1;
        } else if (myGoal.getPriority().equals("C")) {
            selection = 2;
        } else if (myGoal.getPriority().equals("D")) {
            selection = 3;
        } else {
            selection = 0;
        }
        editPriority.setSelection(selection);
        editDeadline = view.findViewById(R.id.edit_deadline);
        sharedViewModel.setDeadline(myGoal.getDeadline());
        if (!myGoal.getDeadline().trim().isEmpty()) {

            String date = myGoal.getDeadline();
            String[] parts = date.split("/");

            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, day);

            long milliTime = calendar.getTimeInMillis();
            editDeadline.setDate(milliTime, true, true);
        }
        editMonday = view.findViewById(R.id.edit_monday);
        editTuesday = view.findViewById(R.id.edit_tuesday);
        editWednesday = view.findViewById(R.id.edit_wednesday);
        editThursday = view.findViewById(R.id.edit_thursday);
        editFriday = view.findViewById(R.id.edit_friday);
        editSaturday = view.findViewById(R.id.edit_saturday);
        editSunday = view.findViewById(R.id.edit_sunday);
        if (myGoal.getCustomizeConverter().get(0).equals("MONDAY")) {
            editMonday.setChecked(true);
        }
        if (myGoal.getCustomizeConverter().get(1).equals("TUESDAY")) {
            editTuesday.setChecked(true);
        }
        if (myGoal.getCustomizeConverter().get(2).equals("WEDNESDAY")) {
            editWednesday.setChecked(true);
        }
        if (myGoal.getCustomizeConverter().get(3).equals("THURSDAY")) {
            editThursday.setChecked(true);
        }
        if (myGoal.getCustomizeConverter().get(4).equals("FRIDAY")) {
            editFriday.setChecked(true);
        }
        if (myGoal.getCustomizeConverter().get(5).equals("SATURDAY")) {
            editSaturday.setChecked(true);
        }
        if (myGoal.getCustomizeConverter().get(6).equals("SUNDAY")) {
            editSunday.setChecked(true);
        }
        editReminder = view.findViewById(R.id.edit_reminder);
        editReminder.setCurrentMinute(myGoal.getMinute());
        sharedViewModel.setMinute(myGoal.getMinute());
        editReminder.setCurrentHour(myGoal.getHour());
        sharedViewModel.setHour(myGoal.getHour());
        editDeadline.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String deadline = dayOfMonth + "/" + month + "/" + year;
                sharedViewModel.setDeadline(deadline);
            }
        });
        editReminder.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                sharedViewModel.setHour(hourOfDay);
                sharedViewModel.setMinute(minute);

            }
        });
        saveChanges = view.findViewById(R.id.save_changes_button);
        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sharedViewModel.setGoalName(editGoalName.getText().toString());
                String inputGoalName = sharedViewModel.getGoalName();
                sharedViewModel.setMotivation(editMotivation.getText().toString());
                String inputMotivation = sharedViewModel.getMotivation();
                sharedViewModel.setPriority(editPriority.getSelectedItem().toString());
                String inputPriority = sharedViewModel.getPriority();
                if(!sharedViewModel.getDeadline().trim().isEmpty())
                {
                    String date = sharedViewModel.getDeadline();
                    String[] parts = date.split("/");

                    int day = Integer.parseInt(parts[0]);
                    int month = Integer.parseInt(parts[1]);
                    int year = Integer.parseInt(parts[2]);

                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, month);
                    calendar.set(Calendar.DAY_OF_MONTH, day);

                    long milliTime = calendar.getTimeInMillis();
                    editDeadline.setDate(milliTime , true , true);
                }
                String inputDeadline = sharedViewModel.getDeadline();
                ArrayList<String > inputdays = new ArrayList<>();
                if (editMonday.isChecked())
                {
                    inputdays.add("MONDAY");
                }
                else
                {
                    inputdays.add("-1");
                }
                if (editTuesday.isChecked())
                {
                    inputdays.add("TUESDAY");
                }
                else
                {
                    inputdays.add("-1");
                }
                if (editWednesday.isChecked())
                {
                    inputdays.add("WEDNESDAY");
                }
                else
                {
                    inputdays.add("-1");
                }
                if (editThursday.isChecked())
                {
                    inputdays.add("THURSDAY");
                }
                else
                {
                    inputdays.add("-1");
                }
                if (editFriday.isChecked())
                {
                    inputdays.add("FRIDAY");
                }
                else
                {
                    inputdays.add("-1");
                }
                if (editSaturday.isChecked())
                {
                    inputdays.add("SATURDAY");
                }
                else
                {
                    inputdays.add("-1");
                }
                if (editSunday.isChecked())
                {
                    inputdays.add("SUNDAY");
                }
                else
                {
                    inputdays.add("-1");
                }
                sharedViewModel.setDays(inputdays);

                ArrayList<String > inputDays = sharedViewModel.getDays();

                int inputHour = sharedViewModel.getHour();
                int inputMinute = sharedViewModel.getMinute();

                Goal updatedGoal = new Goal(inputGoalName,inputMotivation,inputPriority,inputDeadline,inputHour,inputMinute,inputDays);
                updatedGoal.setId(myGoal.getId());
                mViewModel.update(updatedGoal);
                myGoalViewModel.setMyGoal(updatedGoal);
                clearFields();
                navController.navigate(R.id.action_EditGoal_to_MyGoal);
                Toast.makeText(getContext() , "Changes saved" , Toast.LENGTH_SHORT).show();

            }
        });


    }





    private void clearFields() {
        ArrayList<String> days = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            days.add("-1");
        }
        sharedViewModel.setGoalName("");
        sharedViewModel.setMotivation("");
        sharedViewModel.setDeadline("");
        sharedViewModel.setDays(days);
        sharedViewModel.setHour(0);
        sharedViewModel.setMinute(0);
    }

}
