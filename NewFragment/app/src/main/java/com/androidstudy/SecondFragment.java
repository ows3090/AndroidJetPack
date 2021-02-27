package com.androidstudy;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

public class SecondFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_second,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        getParentFragmentManager().setFragmentResultListener("requestKey",this, (requestKey, result) -> {
//            String data = result.getString("data","");
//            Toast.makeText(getContext(),data,Toast.LENGTH_LONG).show();
//        });

        view.findViewById(R.id.button2).setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_secondFragment_to_thirdFragment);
        });
    }
}