package com.example.android.done;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Update;

import java.util.List;

public class GoalRepository {

    private GoalDao goalDao;
    private LiveData<List<Goal>> allGoals;

    public GoalRepository(Application application)
    {
        GoalDatabase database = GoalDatabase.getInstance(application);
        goalDao = database.goalDao();
        allGoals = goalDao.getAllGoals();
    }

    public void insert(Goal goal)
    {
        new InsertGoalAsyncTask(goalDao).execute(goal);

    }

    public void update(Goal goal)
    {
        new UpdateGoalAsyncTask(goalDao).execute(goal);


    }

    public void delete(Goal goal)
    {
        new DeleteGoalAsyncTask(goalDao).execute(goal);

    }

    public LiveData<List<Goal>> getAllGoals()
    {
        return allGoals;
    }

    private static class InsertGoalAsyncTask extends AsyncTask<Goal , Void , Void>
    {
        GoalDao goalDao;

        private InsertGoalAsyncTask(GoalDao goalDao)
        {
            this.goalDao = goalDao;
        }
        @Override
        protected Void doInBackground(Goal... goals) {
            goalDao.insert(goals[0]);
            return null;
        }
    }

    private static class DeleteGoalAsyncTask extends AsyncTask<Goal , Void , Void>
    {
        GoalDao goalDao;

        private DeleteGoalAsyncTask(GoalDao goalDao)
        {
            this.goalDao = goalDao;
        }
        @Override
        protected Void doInBackground(Goal... goals) {
            goalDao.delete(goals[0]);
            return null;
        }
    }

    private static class UpdateGoalAsyncTask extends AsyncTask<Goal , Void , Void>
    {
        GoalDao goalDao;

        private UpdateGoalAsyncTask(GoalDao goalDao)
        {
            this.goalDao = goalDao;
        }
        @Override
        protected Void doInBackground(Goal... goals) {
            goalDao.update(goals[0]);
            return null;
        }
    }


}
