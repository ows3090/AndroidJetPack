package ows.androidstudy.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import ows.androidstudy.R;
import ows.androidstudy.data.MyRepository;
import ows.androidstudy.di.ActivityHash.ActivityHash;
import ows.androidstudy.di.qualifier.AppHash;


@AndroidEntryPoint
public class SecondFragment extends Fragment {

    private MainViewModel mainViewModel;

    @Inject
    MyRepository myRepository;

    @AppHash
    @Inject
    String applicationHash;

    @ActivityHash
    @Inject
    String activityHash;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        view.findViewById(R.id.button).setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_secondFragment_to_mainFragment);
        });

        Log.d("msg","SecondFragment repo : "+myRepository.hashCode()+"");
        Log.d("msg","SecondFragment app : "+applicationHash);
        Log.d("msg","SecondFragment activity : "+activityHash);
        Log.d("msg","SecondFragment ViewModel : "+mainViewModel.getRepositoryHash());
    }
}