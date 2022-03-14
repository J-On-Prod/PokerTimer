package com.jon.pokertimer.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jon.pokertimer.R;
import com.jon.pokertimer.activities.CountTokenActivity;

import java.util.ArrayList;
import java.util.List;

public class TokenCountAdapter extends RecyclerView.Adapter<TokenCountViewHolder> {

    private List<Token> tokenList;
    private CountTokenActivity countTokenActivity;
    private OnTokenCountListener onTokenCountClick;

    public TokenCountAdapter(ArrayList<Token> tokensCount, CountTokenActivity countTokenActivity) {
        this.tokenList = tokensCount;
        this.countTokenActivity = countTokenActivity;
    }

    @NonNull
    @Override
    public TokenCountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.token_count_item, parent, false);
        return new TokenCountViewHolder(view, this.onTokenCountClick);
    }

    @Override
    public void onBindViewHolder(@NonNull TokenCountViewHolder holder, int position) {
        holder.display(tokenList.get(position));
    }

    @Override
    public int getItemCount() {
        return this.tokenList.size();
    }

    public interface OnTokenCountListener {
        void onTokenCountClick(int position);
    }
}
