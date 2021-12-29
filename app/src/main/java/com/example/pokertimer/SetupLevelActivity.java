package com.example.pokertimer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.pokertimer.model.Game;
import com.example.pokertimer.model.LevelAdapter;

public class SetupLevelActivity extends AppCompatActivity {

    private Game game;
    LevelAdapter levelAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_level);

        boolean calculateGame = true;
        game = (Game) getIntent().getSerializableExtra("Game");
        game.calculateGame();
        game.setCalculateGame(false);

        RecyclerView recyclerView = findViewById(R.id.recyclerLevel);

        levelAdapter = new LevelAdapter(game.getLevelList());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(levelAdapter);
    }
}