package com.example.android.done;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MyJournal extends Fragment {

    private EditText journalEntry;
    private MyGoalViewModel myGoalViewModel;
    private GoalViewModel mViewModel;
    private Button saveJournal;
    NavController navController;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_journal, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        myGoalViewModel = ViewModelProviders.of(getActivity()).get(MyGoalViewModel.class);
        mViewModel = ViewModelProviders.of(getActivity()).get(GoalViewModel.class);
        journalEntry = view.findViewById(R.id.journal_edit_text);
        journalEntry.setText(myGoalViewModel.getMyJournal());
        saveJournal = view.findViewById(R.id.save_journal);
        saveJournal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String updatedJournalEntry = journalEntry.getText().toString();
                myGoalViewModel.setMyJournal(updatedJournalEntry);
                Goal myGoal = myGoalViewModel.getMyGoal();
                myGoal.setJournal(updatedJournalEntry);
                mViewModel.update(myGoal);
                navController.navigate(R.id.action_MyJournal_to_MyGoal);
                Toast.makeText(getContext() , "Journal entry saved" , Toast.LENGTH_SHORT).show();



            }
        });


    }
}
