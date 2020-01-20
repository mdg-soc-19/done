package com.example.android.done;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskHolder> {

    private List<Goal> goals = new ArrayList<>();
    private int itemLayout;
    private OnTaskListener mOnTaskListener;


    public TaskAdapter(int layoutId , OnTaskListener onTaskListener) {
        itemLayout = layoutId;
        this.mOnTaskListener = onTaskListener;


    }

    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        return new TaskHolder(itemView , mOnTaskListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskHolder holder, int position) {


        Goal currentGoal = goals.get(position);
        holder.goalName.setText(currentGoal.getGoalName());
        holder.motivation.setText(currentGoal.getMotivation());

        if (currentGoal.getTaskStatus()==1)
        {
            holder.checkBox.setChecked(true);
        }
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    mOnTaskListener.onTaskChecked(position , true);
                }
                else
                {
                    mOnTaskListener.onTaskChecked(position , false);
                }
            }
        });


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


    class TaskHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView goalName;
        private TextView motivation;
        private ImageView task_status;
        private CheckBox checkBox;
        private OnTaskListener onTaskListener;


        public TaskHolder(@NonNull View itemView , OnTaskListener onTaskListener) {
            super(itemView);
            goalName = itemView.findViewById(R.id.task_goal_name);
            motivation = itemView.findViewById(R.id.task_motivation);
            task_status = itemView.findViewById(R.id.task_status);
            checkBox = itemView.findViewById(R.id.task_check);
            this.onTaskListener = onTaskListener;
            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            onTaskListener.onTaskClick(getAdapterPosition());
        }
    }

    public interface OnTaskListener {

        void onTaskClick (int position);
        void onTaskChecked (int position , boolean isChecked);
    }


}
