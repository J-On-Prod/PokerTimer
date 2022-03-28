package com.jon.pokertimer.model;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jon.pokertimer.R;

public class TokenCountViewHolder extends RecyclerView.ViewHolder {

    Token token;
    NotifyCountTextChange notifyCountTextChange;

    View colorSquare;
    TextView colorText;
    TextView valueText;
    EditText countText;

    public TokenCountViewHolder(@NonNull View itemView, NotifyCountTextChange notifyCountTextChange) {
        super(itemView);
        this.notifyCountTextChange = notifyCountTextChange;
    }

    private void createAllButtons() {
        Button addOne = itemView.findViewById(R.id.buttonAdd1);
        addOne.setOnClickListener(view -> token.addNumber(1));
        Button addFive = itemView.findViewById(R.id.buttonAdd5);
        addFive.setOnClickListener(view -> token.addNumber(5));
        Button addTen = itemView.findViewById(R.id.buttonAdd10);
        addTen.setOnClickListener(view -> token.addNumber(10));
        Button resetButton = itemView.findViewById(R.id.buttonResetToken);
        resetButton.setOnClickListener(view -> token.setNumber(0));
    }

    private void setAllText() {
        colorSquare = this.itemView.findViewById(R.id.colorTokenView2);
        colorText = this.itemView.findViewById(R.id.colorToken2);
        valueText = this.itemView.findViewById(R.id.tokenCountVal);
        countText = this.itemView.findViewById(R.id.tokenCountNumber);
        countText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                notifyCountTextChange.notifyCountChange();
            }
        });
    }

    public void display(Token token) {
        this.token = token;
        createAllButtons();
        setAllText();
        colorSquare.setBackgroundColor(token.getColor().getColorValue());
        colorText.setText(token.getColor().getName());
        valueText.setText(token.getValueToString());
        countText.setText(token.getNumberToString());
    }

    public interface NotifyCountTextChange {
        public void notifyCountChange();
    }
}
