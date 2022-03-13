package com.jon.pokertimer.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jon.pokertimer.R;
import com.jon.pokertimer.model.Game;
import com.jon.pokertimer.model.TokenAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SetupTokenActivity extends AppCompatActivity implements TokenAdapter.OnTokenListener {

    Game game;
    TokenAdapter tokenAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_token);

        if (!gameIntentExist()) {
            game = new Game();
        } else {
            getIntentData();
        }

        createListToken();
        createAddButton();
        createSetupGameButton();
    }

    private Boolean gameIntentExist() {
        Bundle extras = getIntent().getExtras();
        return extras != null && extras.containsKey("Game");
    }

    private void getIntentData() {
        Intent i = getIntent();
        game = (Game) i.getSerializableExtra("Game");

    }

    private void createListToken() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.listTokens);
        this.tokenAdapter = new TokenAdapter(game.getTokenList(), this);
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

    @Override
    public void onTokenClick(int position) {
        Intent intent = new Intent(getApplicationContext(), ChangeTokenActivity.class);
        intent.putExtra("Game", game);
        intent.putExtra("Position", position);
        startActivity(intent);
    }
}