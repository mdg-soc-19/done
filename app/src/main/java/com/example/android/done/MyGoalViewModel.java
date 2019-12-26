package com.example.android.done;

import androidx.lifecycle.ViewModel;

public class MyGoalViewModel extends ViewModel {

    private Goal myGoal;
    private int myId = 0;
    private String myJournal = "";


    public Goal getMyGoal() {
        return myGoal;
    }

    public void setMyGoal(Goal myGoal) {
        this.myGoal = myGoal;
    }

    public int getMyId() {
        return myId;
    }

    public void setMyId(int myId) {
        this.myId = myId;
    }

    public String getMyJournal() {
        return myJournal;
    }

    public void setMyJournal(String myJournal) {
        this.myJournal = myJournal;
    }

}
