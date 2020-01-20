package com.example.android.done;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = Goal.class, version = 1, exportSchema = false)
@TypeConverters(Converter.class)

public abstract class GoalDatabase extends RoomDatabase {

    private static GoalDatabase instance;

    public abstract GoalDao goalDao();

    public static synchronized GoalDatabase getInstance(Context context) //instantiating it only if null
    {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), GoalDatabase.class, "goal_database").fallbackToDestructiveMigration().build();
        }

        return instance;
    }

}
