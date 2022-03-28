package com.jon.pokertimer.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jon.pokertimer.R;

import java.util.List;

public class HandRankinAdapter extends RecyclerView.Adapter<HandRankinViewHolder> {

    List<HandRankin> handRankinList;

    public HandRankinAdapter(List<HandRankin> handRankinList) {
        this.handRankinList = handRankinList;
    }

    @NonNull
    @Override
    public HandRankinViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.hand_rankin_item, parent, false);
        return new HandRankinViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HandRankinViewHolder holder, int position) {
        holder.display(handRankinList.get(position));
    }

    @Override
    public int getItemCount() {
        return handRankinList.size();
    }
}
