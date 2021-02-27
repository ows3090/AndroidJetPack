package com.androidstudy;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import java.util.ArrayList;
import java.util.Map;


public class FirstFragment extends Fragment {

    ImageView imageView;

    // 기존에는 RequestCode로 구별하였다면, 명시적으로 동작을 처리하도록 분
    ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
        @Override
        public void onActivityResult(Uri result) {
            imageView.setImageURI(result);
        }
    });

    ActivityResultLauncher<Intent> getStartActivityForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getData()!=null){
                Intent intent = result.getData();
                if(intent.getStringExtra("data")!=null){
                    Log.d("FirstFragment","result : "+intent.getStringExtra("data"));
                }
            }
        }
    });


    ActivityResultLauncher<String> requestPermission = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
        @Override
        public void onActivityResult(Boolean result) {
            if(result){
                Toast.makeText(requireContext(),"성공",Toast.LENGTH_LONG).show();
            }
        }
    });

    ActivityResultLauncher<String[]> requestMultiplePermission = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback<Map<String, Boolean>>() {
        @Override
        public void onActivityResult(Map<String, Boolean> result) {
            if(result.get(Manifest.permission.ACCESS_FINE_LOCATION)){
                Toast.makeText(requireContext(),"ACCESS_FINE_LOCATION 성공",Toast.LENGTH_LONG).show();
            }

            if(result.get(Manifest.permission.ACCESS_COARSE_LOCATION)){
                Toast.makeText(requireContext(),"ACCESS_COARSE_LOCATION 성공",Toast.LENGTH_LONG).show();
            }
        }
    });

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageView = view.findViewById(R.id.imageView);

        view.findViewById(R.id.button).setOnClickListener(v -> {
//            Bundle bundle = new Bundle();
//            bundle.putString("data","Hello");
//            getParentFragmentManager().setFragmentResult("requestKey",bundle);
//            Navigation.findNavController(v).navigate(R.id.action_firstFragment_to_secondFragment);

            // MIME TYPE
            //mGetContent.launch("image/*");

            // startForActivitiy 최
//            Intent intent = new Intent(requireContext(),ResultActivity.class);
//            getStartActivityForResult.launch(intent);신

            //requestPermission.launch(Manifest.permission.ACCESS_FINE_LOCATION);
            String[] permission = {Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION};
            requestMultiplePermission.launch(permission);
        });
    }
}