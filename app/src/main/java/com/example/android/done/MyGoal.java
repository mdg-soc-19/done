package com.example.android.done;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MyGoal extends Fragment {


   private TextView myGoalName;
   private TextView myDeadline;
   private TextView myPriority;
   private TextView myMotivation;
   MyGoalViewModel myGoalViewModel;
   private Goal goal;
   NavController navController;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_goal, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        myGoalName = view.findViewById(R.id.my_goal_name);
        myPriority = view.findViewById(R.id.my_priority);
        myDeadline = view.findViewById(R.id.my_deadline);
        myMotivation = view.findViewById(R.id.my_motivation);
        myGoalViewModel = ViewModelProviders.of(getActivity()).get(MyGoalViewModel.class);
        goal = myGoalViewModel.getMyGoal();
        myGoalName.setText(goal.getGoalName());
        myPriority.setText(goal.getPriority());
        if(!goal.getDeadline().trim().isEmpty())
        {
            String date = goal.getDeadline();
            String[] parts = date.split("/");

            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);

            String dealine1 = day+ "/" + (month +1 )+ "/" + year;
            myDeadline.setText(dealine1);
        }
        myMotivation.setText(goal.getMotivation());
        Button edit = view.findViewById(R.id.edit_button);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                navController.navigate(R.id.action_MyGoal_to_EditGoal);
            }
        });
        Button journal = view.findViewById(R.id.journal_button);
        journal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_MyGoal_to_MyJournal);
            }
        });


    }
}
