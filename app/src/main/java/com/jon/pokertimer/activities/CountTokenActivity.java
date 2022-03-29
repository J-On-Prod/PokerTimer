package com.jon.pokertimer.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jon.pokertimer.R;
import com.jon.pokertimer.model.Game;
import com.jon.pokertimer.model.Token;
import com.jon.pokertimer.model.TokenAdapter;
import com.jon.pokertimer.model.TokenCountAdapter;
import com.jon.pokertimer.model.TokenCountViewHolder;

public class CountTokenActivity extends AppCompatActivity implements TokenCountViewHolder.NotifyCountTextChange {

    private Game game;
    private TokenCountAdapter tokenCountAdapter;

    TextView countText;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_token);

        getIntentGame();

        createRecyclerView();
        createButtonReset();
        createButtonBack();
        this.countText = findViewById(R.id.totalTokenView);
    }

    private void createRecyclerView() {
        tokenCountAdapter = new TokenCountAdapter(game.getTokensCount(), this);
        recyclerView = findViewById(R.id.recyclerTokenCount);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(tokenCountAdapter);
    }

    private void createButtonReset() {
        Button buttonReset = findViewById(R.id.buttonCountTokenResetAll);
        buttonReset.setOnClickListener(view -> resetAllNumberTokens());
        buttonReset.setClickable(false);
        buttonReset.setVisibility(View.GONE);
    }

    private void resetAllNumberTokens() {
        game.resetTokenCount();
        tokenCountAdapter.notifyItemRangeChanged(0, this.game.getTokensCount().size()-1);
        countText.setText('0');
    }

    private void createButtonBack() {
        Button buttonBack = findViewById(R.id.buttonBackCountToken);
        buttonBack.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), InGameActivity.class);
            intent.putExtra("Game", game);
            startActivity(intent);
        });
    }

    private void getIntentGame() {
        game = (Game) getIntent().getSerializableExtra("Game");
        game.generateTokenCount();
    }

    @Override
    public void notifyCountChange() {
        int valTotal = 0;
        for (Token token : game.getTokensCount()) {
            valTotal += token.getTotalValue();
        }
        countText.setText(String.valueOf(valTotal));
    }
}