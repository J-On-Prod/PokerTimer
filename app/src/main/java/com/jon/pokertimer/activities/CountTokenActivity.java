package com.jon.pokertimer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jon.pokertimer.R;
import com.jon.pokertimer.model.Game;
import com.jon.pokertimer.model.TokenAdapter;
import com.jon.pokertimer.model.TokenCountAdapter;

public class CountTokenActivity extends AppCompatActivity implements TokenCountAdapter.OnTokenCountListener {

    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_token);

        getIntentGame();

        createRecyclerView();
        createButtonBack();
    }

    private void createRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerTokenCount);
        TokenCountAdapter tokenCountAdapter = new TokenCountAdapter(game.getTokensCount(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(tokenCountAdapter);
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
    public void onTokenCountClick(int position) {

    }
}