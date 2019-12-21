package com.example.android.done;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class MyGoal extends Fragment {


    public MyGoal() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_my_goal, container, false);
        setHasOptionsMenu(true);
        return v;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.my_goal_toolbar , menu);
        super.onCreateOptionsMenu(menu , inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit_action:
                Toast.makeText(getContext(), "edit", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.calendar_action:
                Toast.makeText(getContext(), "calendar", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.stats_action:
                Toast.makeText(getContext(), "STATISTICS", Toast.LENGTH_SHORT).show();
                return true;
            default:
            return super.onOptionsItemSelected(item);
        }
    }
}
