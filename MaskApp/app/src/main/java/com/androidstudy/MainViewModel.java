package com.androidstudy;

import android.annotation.SuppressLint;
import android.location.Location;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.LocationDistance;
import com.androidstudy.model.Store;
import com.androidstudy.model.StoreInfo;
import com.androidstudy.repository.MaskService;
import com.google.android.gms.location.FusedLocationProviderClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import dagger.hilt.android.lifecycle.HiltViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

@HiltViewModel
public class MainViewModel extends ViewModel {
    private static final String TAG = MainViewModel.class.getSimpleName();

    public MutableLiveData<List<Store>> itemLiveData = new MutableLiveData<>();
    public MutableLiveData<Boolean> loadingLiveData = new MutableLiveData<>();

    public FusedLocationProviderClient fusedLocationProviderClient;
    private final MaskService service;

    public Location location;

    @Inject
    public MainViewModel(FusedLocationProviderClient fusedLocationProviderClient, MaskService service) {
        this.fusedLocationProviderClient = fusedLocationProviderClient;
        this.service = service;
    }

    @SuppressLint("MissingPermission")
    public void performAction() {
        fusedLocationProviderClient.getLastLocation()
                .addOnFailureListener(e -> {
                    Log.e(TAG,"performAction : "+ e.getCause());
                })
                .addOnSuccessListener(location1 -> {
                    if (location1 != null) {
                        // Logic to handle location object
                        Log.d(TAG,"getLatitude : " + location1.getLatitude());
                        Log.d(TAG,"getLongitude : "+location1.getLongitude());

                        location.setLatitude(37.188078);
                        location.setLongitude(127.043002);
                        this.location = location1;
                        fetchStoreInfo();
                    }
                });
    }

    public void fetchStoreInfo(){
        loadingLiveData.setValue(true);

        // 로딩 시작
        service.fetchStoreInfo(location.getLatitude(), location.getLongitude())
                .enqueue(new Callback<StoreInfo>() {
                    @Override
                    public void onResponse(Call<StoreInfo> call, Response<StoreInfo> response) {
                        Log.d(TAG, "onResponse");
                        List<Store> items = response.body().getStores()
                                .stream()
                                .filter(item -> item.getRemainStat() != null)
                                .filter(item -> !item.getRemainStat().equals("empty"))
                                .collect(Collectors.toList());

                        for(Store item : items){
                            double distance = LocationDistance.distance(location.getLatitude(),location.getLongitude()
                                    ,item.getLat(),item.getLng(),"km");
                            item.setDistance(distance);
                        }
                        Collections.sort(items);
                        itemLiveData.postValue(items);

                        // 로딩 끝
                        loadingLiveData.postValue(false);
                    }

                    @Override
                    public void onFailure(Call<StoreInfo> call, Throwable t) {
                        Log.e(TAG, t.getMessage());
                        itemLiveData.postValue(Collections.emptyList());

                        // 로딩 끝
                        loadingLiveData.postValue(false);
                    }
                });
    }
}
