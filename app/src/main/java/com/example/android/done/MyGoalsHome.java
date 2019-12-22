package com.example.android.done;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.PrimaryKey;

import android.util.Log;
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
import android.app.AlertDialog;
import android.content.DialogInterface;

public class MyGoalsHome extends Fragment implements GoalAdapter.OnGoalListener {

    private GoalAdapter adapter;
    private GoalViewModel mViewModel;
    private RecyclerView recyclerView;
    NavController navController;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_goals_home, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewModel = ViewModelProviders.of(getActivity()).get(GoalViewModel.class);
        recyclerView = view.findViewById(R.id.recycler_view);
        navController = Navigation.findNavController(view);

        observerSetup();
        recyclerSetup();

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback( 0 , ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                int position = viewHolder.getAdapterPosition();
                Goal goal = adapter.getNoteAT(position);

                adapter.removeGoal(position);

                AlertDialog diaBox = AskOption(position , goal);
                diaBox.show();

            }
        }).attachToRecyclerView(recyclerView);








    }

    private void observerSetup()
    {
        mViewModel.getAllGoals().observe(this, new Observer<List<Goal>>() {
            @Override
            public void onChanged(List<Goal> goals) {
                adapter.setGoals(goals);
            }
        });
    }

    private void recyclerSetup()
    {

        adapter = new GoalAdapter(R.layout.goal_item  , this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    private AlertDialog AskOption( int position , Goal goal)
    {
        AlertDialog confrimationDialogBox = new AlertDialog.Builder(getActivity())
                // set message, title, and icon
                .setTitle("Delete")
                .setMessage("Do you want to Delete")


                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {


                        mViewModel.delete(goal);
                        dialog.dismiss();
                    }

                })
                .setNegativeButton("Undo", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        adapter.restoreGoal(goal , position);
                        recyclerView.scrollToPosition(position);
                        dialog.dismiss();

                    }
                })
                .create();

        return confrimationDialogBox;
    }


    @Override
    public void onGoalClick(int position) {

        Goal goalSelected = adapter.getNoteAT(position);
        int id = goalSelected.getId();
        navController.navigate(R.id.action_MyGoalsHome_to_MyGoal);
        Log.d("clciked" , String.valueOf(position));
        Log.d("id:" , String.valueOf(id));


    }
}

