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
import com.example.pokertimer.model.TokenAdapter;

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
            game = (Game) getIntent().getSerializableExtra("Game");
        }

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.listTokens);

        this.tokenAdapter = new TokenAdapter(game.getTokenList());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(tokenAdapter);

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