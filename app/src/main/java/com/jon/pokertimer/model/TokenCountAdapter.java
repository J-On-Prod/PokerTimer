package com.jon.pokertimer.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jon.pokertimer.R;

import java.util.ArrayList;
import java.util.List;

public class TokenCountAdapter extends RecyclerView.Adapter<TokenCountViewHolder> {

    private final List<Token> tokenList;
    private final TokenCountViewHolder.NotifyCountTextChange notifyCountTextChange;

    public TokenCountAdapter(ArrayList<Token> tokensCount, TokenCountViewHolder.NotifyCountTextChange notifyCountTextChange) {
        this.tokenList = tokensCount;
        this.notifyCountTextChange = notifyCountTextChange;
    }

    @NonNull
    @Override
    public TokenCountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.token_count_item, parent, false);
        return new TokenCountViewHolder(view, notifyCountTextChange);
    }

    @Override
    public void onBindViewHolder(@NonNull TokenCountViewHolder holder, int position) {
        holder.display(tokenList.get(position));
    }

    @Override
    public int getItemCount() {
        return this.tokenList.size();
    }

}
