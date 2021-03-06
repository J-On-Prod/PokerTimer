package com.jon.pokertimer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jon.pokertimer.R;
import com.jon.pokertimer.model.Game;
import com.jon.pokertimer.model.LevelAdapter;

import java.util.Objects;

public class SetupLevelActivity extends AppCompatActivity {

    private Game game;
    private LevelAdapter levelAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_setup_level);

        getIntentData();

        createLevelList();
        createButtonBackOptions();
        createButtonNextLaunchGame();
    }

    private void getIntentData() {
        game = (Game) getIntent().getSerializableExtra("Game");
        game.calculateGame();
        game.setCalculateGame(false);
    }

    private void createLevelList() {
        recyclerView = findViewById(R.id.recyclerLevel);

        levelAdapter = new LevelAdapter(game.getLevelList());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(levelAdapter);
    }

    private void passIntentGame(Class<?> classSelected) {
        Intent intent = new Intent(getApplicationContext(), classSelected);
        intent.putExtra("Game", game);
        startActivity(intent);
    }

    private void createButtonBackOptions() {
        Button buttonBackOption = findViewById(R.id.buttonBackOption);
        buttonBackOption.setOnClickListener(v -> passIntentGame(SetupOptionActivity.class));
    }

    private void createButtonNextLaunchGame() {
        Button buttonNextLaunch = findViewById(R.id.buttonNextLaunch);
        buttonNextLaunch.setOnClickListener(v -> passIntentGame(PrepareGameActivity.class));
    }
}