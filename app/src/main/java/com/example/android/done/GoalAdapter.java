package com.example.android.done;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.done.model.Date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class GoalAdapter extends RecyclerView.Adapter<GoalAdapter.GoalHolder> {


    private int itemLayout;
    private List<Goal> goals = new ArrayList<>();

    public GoalAdapter(int layoutId) {
        itemLayout = layoutId;
    }



    @NonNull
    @Override
    public GoalHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.goal_item , parent , false);
        return new GoalHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GoalHolder holder, int position) {

        Goal currentGoal = goals.get(position);
        holder.textViewGoalName.setText(currentGoal.getGoalName());
        holder.textViewDeadline.setText(currentGoal.getDeadline());
        holder.textViewPriority.setText(currentGoal.getPriority());


    }

    @Override
    public int getItemCount() {


        return goals.size();
    }

    public void setGoals(List<Goal> goals)
    {
        this.goals = goals;
        notifyDataSetChanged();
    }

    public Goal getNoteAT(int position)
    {
        return goals.get(position);
    }

    public void removeGoal ( int position)
    {
        goals.remove(position);
        notifyItemRemoved(position);
    }


    public void restoreGoal ( Goal goal , int position)

    {
        goals.add(position , goal);
        notifyItemInserted(position);
    }

    class GoalHolder extends RecyclerView.ViewHolder{

        private TextView textViewGoalName;
        private TextView textViewDeadline;
        private TextView textViewPriority;

        public GoalHolder(@NonNull View itemView) {
            super(itemView);
            textViewGoalName = itemView.findViewById(R.id.goa_name_text_view);
            textViewDeadline = itemView.findViewById(R.id.deadline_text_view);
            textViewPriority = itemView.findViewById(R.id.priority_text_view);
        }
    }
}
