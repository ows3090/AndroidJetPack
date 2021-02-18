package ows.boostcourse.roomexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText mNameEditText;
    private EditText mUniversityEditText;
    private TextView mDbTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNameEditText = findViewById(R.id.name_edit);
        mUniversityEditText = findViewById(R.id.university_edit);
        mDbTextView = findViewById(R.id.db_text);

        AppDatabase db = Room.databaseBuilder(this,AppDatabase.class,"StudentInfo")
                .allowMainThreadQueries()
                .build();

        // UI 갱신 (LiveData)
        db.studentDao().getAll().observe(this, students -> {
            mDbTextView.setText(students.toString());
        });

        Button insertButton = findViewById(R.id.insert_btn);
        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 버튼 클릭시 DB에 insert
                db.studentDao().insert(new Student(mNameEditText.getText().toString(),mUniversityEditText.getText().toString()));
            }
        });

        Button updateButton = findViewById(R.id.update_btn);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.studentDao().update(new Student(mNameEditText.getText().toString(),mUniversityEditText.getText().toString()));
                mDbTextView.setText(db.studentDao().getAll().toString());
            }
        });


    }
}