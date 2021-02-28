package ows.androidstudy.ui.main;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import ows.androidstudy.data.MyRepository;

@HiltViewModel
public class MainViewModel extends ViewModel {

    private final MyRepository myRepository;

    @Inject
    public MainViewModel(MyRepository myRepository) {
        this.myRepository = myRepository;
    }

    public String getRepositoryHash(){
        return String.valueOf(myRepository.hashCode());
    }
}
