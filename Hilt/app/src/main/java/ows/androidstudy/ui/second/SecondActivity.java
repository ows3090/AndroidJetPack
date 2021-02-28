package ows.androidstudy.ui.second;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import ows.androidstudy.R;
import ows.androidstudy.data.MyRepository;

@AndroidEntryPoint
public class SecondActivity extends AppCompatActivity {

    @Inject
    MyRepository myRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Log.d("msg",myRepository.hashCode()+"");
    }
}