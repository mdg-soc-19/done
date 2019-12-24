package com.example.android.done;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity (tableName = "goal_table")
public class Goal {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String goalName;

    private String motivation;

    private String priority;

    private String deadline;

    private int hour;

    private int minute;

    private ArrayList<String > customizeConverter;

    private String journal;

    public Goal(String goalName, String motivation, String priority, String deadline, int hour, int minute , ArrayList<String > customizeConverter) {
        this.goalName = goalName;
        this.motivation = motivation;
        this.priority = priority;
        this.deadline = deadline;
        this.hour = hour;
        this.minute = minute;
        this.customizeConverter = customizeConverter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoalName() {
        return goalName;
    }

    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }

    public String getMotivation() {
        return motivation;
    }

    public void setMotivation(String motivation) {
        this.motivation = motivation;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public ArrayList<String> getCustomizeConverter() {
        return customizeConverter;
    }

    public void setCustomizeConverter(ArrayList<String> customizeConverter) {
        this.customizeConverter = customizeConverter;
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }
}
