package ows.boostcourse.roomexample;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.room.Room;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    final AppDatabase db;

    public MainViewModel(@NonNull Application application) {
        super(application);

        db = Room.databaseBuilder(application,AppDatabase.class,"StudentInfo").build();
    }

    public LiveData<List<Student>> getAll(){
        return db.studentDao().getAll();
    }

    public void insertInfo(Student student){
        new InsertAsyncTask(db.studentDao()).execute(student);
    }

    private static class InsertAsyncTask extends AsyncTask<Student,Void,Void>{

        private StudentDao studentDao;

        public InsertAsyncTask(StudentDao studentDao) {
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            studentDao.insert(students[0]);
            return null;
        }
    }

}


