package com.example.pokertimer.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokertimer.R;

import java.util.List;

public class TokenAdapter extends RecyclerView.Adapter<TokenViewHolder> {

    List<Token> tokens;

    public TokenAdapter(List<Token> tokens) {
        this.tokens = tokens;
    }

    @NonNull
    @Override
    public TokenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.token_item, parent, false);
        return new TokenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TokenViewHolder holder, int position) {
        holder.display(tokens.get(position));
    }

    @Override
    public int getItemCount() {
        return tokens.size();
    }
}
