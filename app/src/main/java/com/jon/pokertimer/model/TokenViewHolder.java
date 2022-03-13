package com.jon.pokertimer.model;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jon.pokertimer.R;

public class TokenViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView valueToken;
    private TextView numberToken;
    private TextView colorToken;
    private View colorTokenView;
    TokenAdapter.OnTokenListener onTokenListener;

    public TokenViewHolder(@NonNull View itemView, TokenAdapter.OnTokenListener onTokenListener) {
        super(itemView);

        valueToken = itemView.findViewById(R.id.valueToken);
        numberToken = itemView.findViewById(R.id.numberToken);
        colorToken = itemView.findViewById(R.id.colorToken);
        colorTokenView = itemView.findViewById(R.id.colorTokenView);
        this.onTokenListener = onTokenListener;

        itemView.setOnClickListener(this);
    }

    void display(Token token) {
        valueToken.setText(token.getValueToString());
        numberToken.setText(token.getNumberToString());
        colorToken.setText(token.getColorToString());
        colorTokenView.setBackgroundColor(token.getColor().getColorValue());
    }

    @Override
    public void onClick(View v) {
        onTokenListener.onTokenClick(getAdapterPosition());
    }
}
