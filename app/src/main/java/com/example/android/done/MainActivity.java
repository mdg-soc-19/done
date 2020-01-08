package com.example.android.done;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.view.View;
import android.app.DialogFragment;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    NavController navController;
    GoalViewModel goalViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
        goalViewModel = ViewModelProviders.of(this).get(GoalViewModel.class);
        Calendar calendar = Calendar.getInstance();
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        SharedPreferences settings = getSharedPreferences("PREFS" , 0);
        int lastDay = settings.getInt("day", 0);
        if (lastDay != currentDay)
        {

            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("day" , currentDay);
            editor.commit();

            goalViewModel.getAllGoals().observe(this, new Observer<List<Goal>>() {
                @Override
                public void onChanged(List<Goal> goals) {
                    for (int i =0; i<goals.size();i++){
                        Goal goal = goals.get(i);
                        goal.setTaskStatus(0);
                        goalViewModel.update(goal);
                    }


                }
            });


        }



    }


}
