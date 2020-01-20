package com.example.android.done;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class HomePage extends Fragment implements TaskAdapter.OnTaskListener {

    NavController navController;
    private TaskAdapter adapter;
    private GoalViewModel mViewModel;
    private RecyclerView recyclerView;
    private Dialog helpDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_page, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = ViewModelProviders.of(getActivity()).get(GoalViewModel.class);
        recyclerView = view.findViewById(R.id.home_page_recycler_view);
        navController = Navigation.findNavController(view);

        observerSetup();
        recyclerSetup();


    }

    private void observerSetup() {
        mViewModel.getAllGoals().observe(this, new Observer<List<Goal>>() {
            @Override
            public void onChanged(List<Goal> goals) {


                Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_WEEK);
                String today = "SUNDAY";
                if (day == 2) {
                    today = "MONDAY";
                } else if (day == 3) {
                    today = "TUESDAY";
                } else if (day == 4) {
                    today = "WEDNESDAY";
                } else if (day == 5) {
                    today = "THURSDAY";
                } else if (day == 6) {
                    today = "FRIDAY";
                } else if (day == 7) {
                    today = "SATURDAY";
                }



                List<Goal> todaysTasks = new ArrayList<>();
                for (int i = 0; i < goals.size(); i++) {
                    goals.get(i).setStatus1();
                    if (goals.get(i).getCustomizeConverter().contains(today)&&goals.get(i).getStatus()==0) {
                        todaysTasks.add(goals.get(i));
                    }
                }

                adapter.setTasks(todaysTasks);
            }
        });
    }

    private void recyclerSetup() {

        adapter = new TaskAdapter(R.layout.task_item , this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onTaskClick(int position) {

        Goal goalTaskSelected = adapter.getTaskAt(position);
        AskOption(position , goalTaskSelected).show();


    }

    @Override
    public void onTaskChecked(int position, boolean isChecked) {

        Goal goalTaskSelected = adapter.getTaskAt(position);
        if (isChecked)
        {
            goalTaskSelected.setTaskStatus(1); //task marked as done
            goalTaskSelected.setDoneTask(goalTaskSelected.getDoneTask()+1);  //task for the specific goal ++
            mViewModel.update(goalTaskSelected);
            helpDialog = new Dialog(getContext());
            helpDialog.setContentView(R.layout.congratulatory_dialog);
            helpDialog.show();


        }
        if (!isChecked)
        {
            goalTaskSelected.setTaskStatus(0);  //task marked as undone
            goalTaskSelected.setDoneTask(goalTaskSelected.getDoneTask()-1);  //task for the specific goal --
            mViewModel.update(goalTaskSelected);
        }
    }

    private AlertDialog AskOption( int position , Goal goal) {
        AlertDialog confirmationDialogBox = new AlertDialog.Builder(getActivity())
                // set message, title, and icon
                .setTitle("Goal Information")
                .setMessage("Go to My Goals page")


                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {


                        navController.navigate(R.id.action_HomePage_to_MyGoalsHome);
                        dialog.dismiss();
                    }

                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                })
                .create();

        return confirmationDialogBox;
    }
}
