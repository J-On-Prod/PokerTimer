package com.jon.pokertimer.model;

import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jon.pokertimer.R;

public class TokenCountViewHolder extends RecyclerView.ViewHolder {

    TokenCountAdapter.OnTokenCountListener onTokenCountListener;
    public TokenCountViewHolder(@NonNull View itemView, TokenCountAdapter.OnTokenCountListener onTokenCountListener) {
        super(itemView);
    }

    private void createAllButtons() {
        Button addOne = itemView.findViewById(R.id.buttonAdd1);
        addOne.setOnClickListener(view -> {});
        Button addFive = itemView.findViewById(R.id.buttonAdd5);
        addFive.setOnClickListener(view -> {});
        Button addTen = itemView.findViewById(R.id.buttonAdd10);
        addTen.setOnClickListener(view -> {});
    }

    public void display(Token token) {
    }
}
