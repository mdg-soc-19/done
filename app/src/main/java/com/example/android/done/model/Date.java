package com.example.android.done.model;

import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.android.done.R;

public class Date {

        private String date;
        private String month;
        private String year;

        public Date(String date, String month , String year) {
            this.date = date;
            this.month = month;
        }

        public void setDate(String date) {
            this.date = date;
        }


        public String getDate() {
            return date;
        }

    public void setMonth(String month) {
        this.month = month;
    }


        public String getMonth() {
            return month;
        }

    public void setYear(String year) {
        this.year = year;
    }

    public String getYear() {
        return year;
    }


}
