package com.example.android.done;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Motivation extends Fragment {
    NavController navController;
    SharedViewModel viewModel;
    Dialog helpDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_motivation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        navController = Navigation.findNavController(view);
        Button next = (Button) view.findViewById(R.id.next);
        Button help = (Button) view.findViewById(R.id.help);
        helpDialog = new Dialog(getContext());
        final EditText motivation = (EditText) view.findViewById(R.id.motivation_edit_text);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = motivation.getText().toString();
                if (input.trim().isEmpty()) {
                    Toast.makeText(getContext(), "Please enter your motivation", Toast.LENGTH_SHORT).show();
                } else {

                    viewModel.setMotivation(input);

                    navController.navigate(R.id.action_motivation_to_priority2);
                }
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                helpDialog.setContentView(R.layout.motivation_popup);
                helpDialog.show();
            }
        });
    }
}
