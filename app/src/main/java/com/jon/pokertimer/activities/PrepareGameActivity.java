package com.jon.pokertimer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jon.pokertimer.R;
import com.jon.pokertimer.model.Game;
import com.jon.pokertimer.model.TokenAdapter;

public class PrepareGameActivity extends AppCompatActivity implements TokenAdapter.OnTokenListener {

    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prepare_game);

        getIntentGame();
        createRecyclerTokenPerPlayer();
        createSetTotalTokenPerPlayer();
        createButtonStartGame();
    }

    private void getIntentGame() {
        game = (Game) getIntent().getSerializableExtra("Game");
    }

    private void createButtonStartGame() {
        Button startGameButton = findViewById(R.id.buttonStartGame);
        startGameButton.setOnClickListener(v -> touchStartGameButton());
        Button backButton = findViewById(R.id.backTokenPerPlayer);
        backButton.setOnClickListener(v -> touchBackGameButton());
    }

    private void createRecyclerTokenPerPlayer() {
        RecyclerView recyclerView = findViewById(R.id.recyclerTokenPerPlayer);
        TokenAdapter tokenAdapter = new TokenAdapter(game.getTokensPerPlayer(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(tokenAdapter);
    }

    private void createSetTotalTokenPerPlayer() {
        TextView nbTokenPerPlayer = findViewById(R.id.textNbTokenPerPlayer);
        nbTokenPerPlayer.setText(game.getTotalNbPerPlayerToString());
        TextView valueTokenPerPlayer = findViewById(R.id.textValueTokenPerPlayer);
        valueTokenPerPlayer.setText(game.getTotalValuePerPlayerToString());
    }

    private void touchBackGameButton() {
        Intent intent = new Intent(getApplicationContext(), SetupLevelActivity.class);
        intent.putExtra("Game", game);
        startActivity(intent);
    }

    private void touchStartGameButton() {
        Intent intent = new Intent(getApplicationContext(), InGameActivity.class);
        intent.putExtra("Game", game);
        startActivity(intent);
    }

    @Override
    public void onTokenClick(int position) {

    }
}