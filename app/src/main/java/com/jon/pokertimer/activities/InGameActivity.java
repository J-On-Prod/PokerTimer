package com.jon.pokertimer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.jon.pokertimer.R;
import com.jon.pokertimer.model.Game;
import com.jon.pokertimer.model.Level;
import com.jon.pokertimer.model.TimerInGame;

public class InGameActivity extends AppCompatActivity {

    private static final int COUNT_INTERVAL = 1000;
    private static final int RESOLUTION_PROGRESS_BAR = 500;

    private Game game;
    private TimerInGame timerInGame;

    private TextView levelValue;
    private TextView smallBlindValue;
    private TextView bigBlindValue;
    private TextView timerLevel;
    private TextView timerFinish;
    private ProgressBar progressBarLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_game);

        getIntentGame();

        createBlindTimerTexts();
        createButtonCountToken();
        createButtonPlayPause();
        createButtonSkipToNextLevel();

        timerInGame = new TimerInGame(game);
    }

    private void createBlindTimerTexts() {
        levelValue = findViewById(R.id.textCurrentLevelVal);
        smallBlindValue = findViewById(R.id.textCurrentSB);
        bigBlindValue = findViewById(R.id.textCurrentBB);
        timerLevel = findViewById(R.id.textTimerLevel);
        timerFinish = findViewById(R.id.textTimerFinishEstimate);

        progressBarLevel = findViewById(R.id.progressBarLevel);
        progressBarLevel.setMax(RESOLUTION_PROGRESS_BAR);
        progressBarLevel.setIndeterminate(false);
    }

    private void getIntentGame() {
        game = (Game) getIntent().getSerializableExtra("Game");
    }

    private void createButtonCountToken() {
        Button playPauseButton = findViewById(R.id.buttonCountToken);
        playPauseButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), CountTokenActivity.class);
            intent.putExtra("Game", game);
            startActivity(intent);
        });
    }

    private void createButtonPlayPause() {
        Button playPauseButton = findViewById(R.id.buttonStartPause);
        playPauseButton.setOnClickListener(v -> touchPlayPause());
    }

    private void createButtonSkipToNextLevel() {
        Button nextButton = findViewById(R.id.buttonIncrementLevel);
        nextButton.setOnClickListener(v -> touchChangeLevel());
    }

    private void touchPlayPause() {
        timerInGame.touch();
    }

    private void touchChangeLevel() {
        incrementLevel();
    }

    public void incrementLevel() {
        game.incrementLevel();
        updateLevel();
    }

    private void updateLevel() {

        Level currentLevel = game.getCurrentLevel();
        timerInGame.changeCurrentLevel(currentLevel);
        String levelSelectToString = String.valueOf(game.getLevelSelect()+1);
        levelValue.setText(levelSelectToString);
        smallBlindValue.setText(currentLevel.getSmallBlindToString());
        bigBlindValue.setText(currentLevel.getBigBlindToString());
    }

    private String addZero(long duration) {
        if (duration < 10) {
            return "0" + duration;
        }
        return String.valueOf(duration);
    }

    private String convertMlsSecondsToString(long milliseconds) {
        long hours = (milliseconds / 3600000);
        long minutes = (milliseconds / 60000) % 60;
        long seconds = (milliseconds / 1000) % 60;
        return addZero(hours) + ":" + addZero(minutes) + ":" + addZero(seconds);
    }

    public void updateTimerGlobal(long timeRemaining) {
        timerFinish.setText(convertMlsSecondsToString(timeRemaining));
    }

    public void updateTimerLevel(long timeRemaining) {
        int percentageLevel = (int) ((currentLevel.ratioLeftTime(timeRemaining)) * RESOLUTION_PROGRESS_BAR);
        progressBarLevel.setProgress(percentageLevel);
        timerLevel.setText(convertMlsSecondsToString(timeRemaining));

    }
}

