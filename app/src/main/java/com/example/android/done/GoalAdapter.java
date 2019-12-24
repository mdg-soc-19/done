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
    private OnGoalListener mOnGoalListener;


    public GoalAdapter(int layoutId , OnGoalListener onGoalListener) {
        itemLayout = layoutId;
        this.mOnGoalListener = onGoalListener;

    }


    @NonNull
    @Override
    public GoalHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.goal_item, parent, false);
        return new GoalHolder(itemView ,mOnGoalListener);
    }

    @Override
    public void onBindViewHolder(@NonNull GoalHolder holder, int position) {

        Goal currentGoal = goals.get(position);
        holder.textViewGoalName.setText(currentGoal.getGoalName());
        if(!currentGoal.getDeadline().trim().isEmpty())
        {
            String date = currentGoal.getDeadline();
            String[] parts = date.split("/");

            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);

            String deadline = day+ "/" + (month +1 )+ "/" + year;
            holder.textViewDeadline.setText(deadline);
        }

        holder.textViewPriority.setText(currentGoal.getPriority());


    }

    @Override
    public int getItemCount() {


        return goals.size();
    }

    public void setGoals(List<Goal> goals) {
        this.goals = goals;
        notifyDataSetChanged();
    }

    public Goal getNoteAT(int position) {
        return goals.get(position);
    }

    public void removeGoal(int position) {
        goals.remove(position);
        notifyItemRemoved(position);
    }


    public void restoreGoal(Goal goal, int position) {
        goals.add(position, goal);
        notifyItemInserted(position);
    }

    class GoalHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView textViewGoalName;
        private TextView textViewDeadline;
        private TextView textViewPriority;
        OnGoalListener onGoalListener;


        public GoalHolder(@NonNull View itemView, OnGoalListener onGoalListener) {
            super(itemView);
            textViewGoalName = itemView.findViewById(R.id.goa_name_text_view);
            textViewDeadline = itemView.findViewById(R.id.deadline_text_view);
            textViewPriority = itemView.findViewById(R.id.priority_text_view);

            this.onGoalListener = onGoalListener;
            itemView.setOnClickListener(this);


        }


        @Override
        public void onClick(View v) {

            onGoalListener.onGoalClick(getAdapterPosition());
        }
    }

    public interface OnGoalListener {

        void onGoalClick(int position);
    }


}
