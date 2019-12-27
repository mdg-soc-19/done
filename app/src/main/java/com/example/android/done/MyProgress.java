package com.example.android.done;


import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.List;


public class MyProgress extends Fragment {

    GoalViewModel mGoalViewModel;
    private PieChart pieChart;
    int doneGoals;
    int unDoneGoals;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_progress, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mGoalViewModel = ViewModelProviders.of(getActivity()).get(GoalViewModel.class);
        doneGoals = 0;
        unDoneGoals = 0;

        mGoalViewModel.getAllGoals().observe(getActivity(), new Observer<List<Goal>>() {
            @Override
            public void onChanged(List<Goal> goals) {

                for(int i=0;i<goals.size();i++)
                {
                    if(goals.get(i).getStatus()==1)
                    {
                        doneGoals++;
                        Log.e("I got here" , "yes");
                    }
                    else
                    {

                        unDoneGoals++;
                    }
                }
            }
        });

        pieChart = view.findViewById(R.id.PieChart);
        pieChart.getDescription().setEnabled(false);
        pieChart.setDragDecelerationFrictionCoef(0.99f);
        pieChart.setExtraOffsets(5,10,5,10);
        pieChart.setNoDataText("No goal available");
        pieChart.setNoDataTextColor(Color.BLACK);
        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(doneGoals, "Done Goals"));
        entries.add(new PieEntry(unDoneGoals, "Undone Goals"));
        PieDataSet set = new PieDataSet(entries, "");
        set.setSliceSpace(3f);
        set.setSelectionShift(5f);
        set.setColors(Color.rgb(0,153,0) , Color.rgb(255,0,0));
        PieData data = new PieData(set);
        data.setValueTextSize(15f);
        pieChart.setData(data);
        pieChart.invalidate(); // refresh


    }
}
