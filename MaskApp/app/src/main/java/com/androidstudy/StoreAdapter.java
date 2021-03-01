package com.androidstudy;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.androidstudy.databinding.ItemStoreBinding;
import com.androidstudy.model.Store;

import java.util.ArrayList;
import java.util.List;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.StoreViewHolder>{

    private List<Store> sItems = new ArrayList<>();

    // 아이템 뷰의 정보를 가지고 있는 클래스
    static class StoreViewHolder extends RecyclerView.ViewHolder{
        ItemStoreBinding binding;
        public StoreViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemStoreBinding.bind(itemView);
        }
    }

    public void updateItems(List<Store> items){
        sItems = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_store,parent,false);
        return new StoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreViewHolder holder, int position) {
        Store store = sItems.get(position);
        holder.binding.setStore(store);
    }

    @Override
    public int getItemCount() {
        return sItems.size();
    }

    @BindingAdapter("remainStat")
    public static void setRemainStat(TextView textView, Store store){
        switch (store.getRemainStat()){
            case "plenty" :
                textView.setText("충분");
                break;
            case "some":
                textView.setText("여유");
                break;
            case "few":
                textView.setText("매진 임박");
                break;
            case "empty":
                textView.setText("재고 없음");
                break;
            default:
        }
    }

    @BindingAdapter("count")
    public static void setCount(TextView textView, Store store){
        switch (store.getRemainStat()){
            case "plenty" :
                textView.setText("100개 이상");
                break;
            case "some":
                textView.setText("30개 이상");
                break;
            case "few":
                textView.setText("2개 이상");
                break;
            case "empty":
                textView.setText("1개 이하");
                break;
            default:
        }
    }

    @BindingAdapter("color")
    public static void setColor(TextView textView, Store store){
        switch (store.getRemainStat()){
            case "plenty" :
                textView.setTextColor(Color.GREEN);
                break;
            case "some":
                textView.setTextColor(Color.YELLOW);
                break;
            case "few":
                textView.setTextColor(Color.RED);
                break;
            case "empty":
                textView.setTextColor(Color.GRAY);
                break;
            default:
        }
    }


}
