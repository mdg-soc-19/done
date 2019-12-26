package com.example.android.done;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskHolder> {

    private List<Goal> goals = new ArrayList<>();
    private int itemLayout;

    public TaskAdapter(int layoutId) {
        itemLayout = layoutId;


    }

    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        return new TaskHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskHolder holder, int position) {

        Goal currentGoal = goals.get(position);
        holder.goalName.setText(currentGoal.getGoalName());
        holder.motivation.setText(currentGoal.getMotivation());


    }

    @Override
    public int getItemCount() {
        return goals.size();
    }

    public void setTasks(List<Goal> goals) {

        this.goals = goals;
        notifyDataSetChanged();
    }


    public Goal getTaskAt(int position) {
        return goals.get(position);
    }


    class TaskHolder extends RecyclerView.ViewHolder {

        private TextView goalName;
        private TextView motivation;
        private ImageView task_status;


        public TaskHolder(@NonNull View itemView) {
            super(itemView);
            goalName = itemView.findViewById(R.id.task_goal_name);
            motivation = itemView.findViewById(R.id.task_motivation);
            task_status = itemView.findViewById(R.id.task_status);

        }


    }


}
