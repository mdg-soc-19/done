package com.example.android.done.viewmodels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.TextUtils;
import android.util.Patterns;

import com.android.databinding.library.baseAdapters.BR;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.android.done.model.Date;


public class DateViewModel implements BaseObservable {

    private Date date;

    @Bindable
    public String getUserDate(){
        return date.getDate();
    }

    @Bindable
    public String getUserMonth(){
        return date.getMonth();
    }

    @Bindable
    public String getUserYear(){
        return date.getYear();
    }







}
