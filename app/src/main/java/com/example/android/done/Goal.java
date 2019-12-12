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

    private Long deadline;

    private int rHour;

    private int rMinute;

    private String randomize;

    private ArrayList<String> customize;

    public Goal(String goalName, String motivation, String priority, Long deadline, int rHour, int rMinute) {
        this.goalName = goalName;
        this.motivation = motivation;
        this.priority = priority;
        this.deadline = deadline;
        this.rHour = rHour;
        this.rMinute = rMinute;
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

    public Long getDeadline() {
        return deadline;
    }

    public void setDeadline(Long deadline) {
        this.deadline = deadline;
    }

    public int getrHour() {
        return rHour;
    }

    public void setrHour(int rHour) {
        this.rHour = rHour;
    }

    public int getrMinute() {
        return rMinute;
    }

    public void setrMinute(int rMinute) {
        this.rMinute = rMinute;
    }

    public String getRandomize() {
        return randomize;
    }

    public void setRandomize(String randomize) {
        this.randomize = randomize;
    }

    public ArrayList<String> getCustomze() {
        return customize;
    }

    public void setCustomze(ArrayList<String> customize) {
        this.customize = customize;
    }
}
