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
    public LiveData<List<Student>> students;
    public String name;
    public String university;

    public MainViewModel(@NonNull Application application) {
        super(application);

        db = Room.databaseBuilder(application,AppDatabase.class,"StudentInfo").build();
        students = getAll();
    }

    // Databinding 하는 부분에서 LiveData 호출 시 무한 루프 발생
    private LiveData<List<Student>> getAll(){
        return db.studentDao().getAll();
    }

    public void insertInfo(String name, String university){
        new InsertAsyncTask(db.studentDao()).execute(new Student(name, university));
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


