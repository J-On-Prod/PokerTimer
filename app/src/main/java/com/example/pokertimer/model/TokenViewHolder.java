package com.example.pokertimer.model;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokertimer.R;

public class TokenViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView valueToken;
    private TextView numberToken;
    private TextView colorToken;
    private View colorTokenView;

    public TokenViewHolder(@NonNull View itemView) {
        super(itemView);

        valueToken = itemView.findViewById(R.id.valueToken);
        numberToken = itemView.findViewById(R.id.numberToken);
        colorToken = itemView.findViewById(R.id.colorToken);
        colorTokenView = itemView.findViewById(R.id.colorTokenView);
    }

    void display(Token token) {
        valueToken.setText(token.getValueToString());
        numberToken.setText(token.getNumberToString());
        colorToken.setText(token.getColorToString());
        colorTokenView.setBackgroundColor(token.getColor());
    }

    @Override
    public void onClick(View v) {
        this.getLayoutPosition();
    }
}
