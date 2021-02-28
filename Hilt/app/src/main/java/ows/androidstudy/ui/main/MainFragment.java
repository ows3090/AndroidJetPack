package ows.androidstudy.ui.main;

import android.content.Intent;
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
import ows.androidstudy.ui.second.SecondActivity;

@AndroidEntryPoint
public class MainFragment extends Fragment {

    private MainViewModel activityViewModel;
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
        return inflater.inflate(R.layout.fragment_main, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        view.findViewById(R.id.button_activity).setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), SecondActivity.class);
            startActivity(intent);
        });

        view.findViewById(R.id.button_fragment).setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_mainFragment_to_secondFragment);
        });

        Log.d("msg","MainFragment repo : "+myRepository.hashCode()+"");
        Log.d("msg","MainFragment app : "+applicationHash);
        Log.d("msg","MainFragment activity : "+activityHash);

        // viewModel은 Fragment마다 다르지만 Repository는 같은 싱글톤 객체로 주입됨.
        Log.d("msg","MainFragment ViewModel : "+mainViewModel.getRepositoryHash());

    }
}