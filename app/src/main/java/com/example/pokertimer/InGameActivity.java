package com.example.pokertimer;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pokertimer.model.Game;
import com.example.pokertimer.model.Level;

public class InGameActivity extends AppCompatActivity {

    private Game game;
    private Integer levelSelect;
    private Level currentLevel;
    private TextView levelValue;
    private TextView smallBlindValue;
    private TextView bigBlindValue;
    private ProgressBar progressBarLevel;
    private TextView timerLevel;
    private TextView timerFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_game);

        getIntentGame();
        createBlindTimerTexts();
        createButtonPlayPause();
        createButtonSkipToNextLevel();
    }

    private void createBlindTimerTexts() {
        levelValue = findViewById(R.id.textCurrentLevel);
        smallBlindValue = findViewById(R.id.textCurrentSB);
        bigBlindValue = findViewById(R.id.textCurrentBB);
        progressBarLevel = findViewById(R.id.progressBarLevel);
        timerLevel = findViewById(R.id.textTimerLevel);
        timerFinish = findViewById(R.id.textTimerFinishEstimate);
    }

    private void getIntentGame() {
        game = (Game) getIntent().getSerializableExtra("Game");
    }

    private void createButtonPlayPause() {
        Button playPauseButton = findViewById(R.id.buttonStartPause);
        playPauseButton.setOnClickListener(v -> touchPlayPause());
    }

    private void createButtonSkipToNextLevel() {
        Button nextButton = findViewById(R.id.buttonNextLevel);
        nextButton.setOnClickListener(v -> touchChangeLevel());
    }

    private void touchPlayPause() {

    }

    private void touchChangeLevel() {

        updateLevel();
    }

    private void updateLevel() {
        String levelSelectToString = "Niveau : " + levelSelect.toString();
        levelValue.setText(levelSelectToString);
        smallBlindValue.setText(currentLevel.getSmallBlindToString());
        bigBlindValue.setText(currentLevel.getBigBlindToString());
    }

    private void updateTimer() {
        progressBarLevel.setProgress(0);
        timerLevel.setText("");
        timerFinish.setText("");
    }
}

