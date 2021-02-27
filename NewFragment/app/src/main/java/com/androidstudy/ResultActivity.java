package com.androidstudy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        findViewById(R.id.button3).setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.putExtra("data","Hello");
            setResult(Activity.RESULT_OK,intent);
        });
    }
}