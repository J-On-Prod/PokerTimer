package com.jon.pokertimer.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jon.pokertimer.R;

import java.util.List;

public class TokenAdapter extends RecyclerView.Adapter<TokenViewHolder> {

    List<Token> tokens;
    private OnTokenListener onTokenListener;

    public TokenAdapter(List<Token> tokens, OnTokenListener onTokenListener) {
        this.tokens = tokens;
        this.onTokenListener = onTokenListener;
    }

    @NonNull
    @Override
    public TokenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.token_item, parent, false);
        return new TokenViewHolder(view, this.onTokenListener);
    }

    public Token get(int position) {
        return this.tokens.get(position);
    }

    @Override
    public void onBindViewHolder(@NonNull TokenViewHolder holder, int position) {
        holder.display(tokens.get(position));
    }

    @Override
    public int getItemCount() {
        return tokens.size();
    }

    public interface OnTokenListener {
        void onTokenClick(int position);
    }
}
