package com.androidstudy;

import android.util.Log;

import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    String data = "";

    public MainViewModel(){
        Log.d("msg","MainViewModel");
    }
}
