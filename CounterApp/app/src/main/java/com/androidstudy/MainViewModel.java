package com.androidstudy;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
   private SavedStateHandle savedStateHandle;
   private int count = 0;
   public MutableLiveData<Integer> countLiveData = new MutableLiveData<>();

   public MainViewModel(SavedStateHandle savedStateHandle) {
      this.savedStateHandle = savedStateHandle;
      count = savedStateHandle.get("count");
   }

   public void increaseCount(){
      count++;
      countLiveData.setValue(count);
      savedStateHandle.set("count",count);
   }

   public void decreaseCount(){
      count--;
      countLiveData.setValue(count);
      savedStateHandle.set("count",count);
   }
}
