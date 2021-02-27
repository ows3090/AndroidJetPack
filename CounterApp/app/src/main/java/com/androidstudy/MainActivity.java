package com.androidstudy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.androidstudy.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = (ActivityMainBinding)DataBindingUtil.setContentView(this,
                R.layout.activity_main);

        if(viewModel == null){
            Log.d(TAG,"onCreate");
            viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        }
        binding.setViewmodel(viewModel);
        binding.setLifecycleOwner(this);
    }
}