package com.example.android.done;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MyGoal extends Fragment {


   private TextView myGoalName;
   private TextView myDeadline;
   private TextView myPriority;
   private TextView myMotivation;
   MyGoalViewModel myGoalViewModel;
   private Goal goal;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_goal, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myGoalName = view.findViewById(R.id.my_goal_name);
        myPriority = view.findViewById(R.id.my_priority);
        myDeadline = view.findViewById(R.id.my_deadline);
        myMotivation = view.findViewById(R.id.my_motivation);
        myGoalViewModel = ViewModelProviders.of(getActivity()).get(MyGoalViewModel.class);
        goal = myGoalViewModel.getMyGoal();
        myGoalName.setText(goal.getGoalName());
        myPriority.setText(goal.getPriority());
        myDeadline.setText(goal.getDeadline());
        myMotivation.setText(goal.getMotivation());

    }
}
