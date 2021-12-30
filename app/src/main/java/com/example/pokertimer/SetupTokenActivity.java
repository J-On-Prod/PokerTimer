package com.example.pokertimer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pokertimer.model.Game;
import com.example.pokertimer.model.Token;
import com.example.pokertimer.model.TokenAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SetupTokenActivity extends AppCompatActivity {

    Game game;
    TokenAdapter tokenAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_token);

        if (game == null) {
            game = new Game();
        } else {
            getIntentData();
        }

        createListToken();
        createAddButton();
        createSetupGameButton();
    }

    private void getIntentData() {
        Intent i = getIntent();
        game = (Game) i.getSerializableExtra("Game");
    }

    private void createListToken() {
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.listTokens);
        this.tokenAdapter = new TokenAdapter(game.getTokenList());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(tokenAdapter);
    }

    private void createAddButton() {
        FloatingActionButton addTokenButton = (FloatingActionButton) findViewById(R.id.addTokenButton);
        addTokenButton.setOnClickListener(this::createToken);
    }

    private void createSetupGameButton() {
        Button setupOptionButton = (Button) findViewById(R.id.setupOptionButton);
        setupOptionButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), SetupOptionActivity.class);
            intent.putExtra("Game", game);
            startActivity(intent);
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    public void createToken(View view) {
        game.addToken();
        tokenAdapter.notifyDataSetChanged();
    }
}