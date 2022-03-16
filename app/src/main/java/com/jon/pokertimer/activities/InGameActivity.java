package com.jon.pokertimer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.jon.pokertimer.R;
import com.jon.pokertimer.model.Game;
import com.jon.pokertimer.model.HourglassGlobal;
import com.jon.pokertimer.model.HourglassLevel;
import com.jon.pokertimer.model.Level;

public class InGameActivity extends AppCompatActivity {

    private Game game;

    private Integer levelSelect;
    private Level currentLevel;

    private TextView levelValue;
    private TextView smallBlindValue;
    private TextView bigBlindValue;
    private TextView timerLevel;
    private TextView timerFinish;
    private ProgressBar progressBarLevel;
    private final int resolutionProgressBar = 255;

    private boolean timerRunning = false;

    // https://github.com/groverankush/Hourglass
    private HourglassLevel countDownLevel;
    private HourglassGlobal countDownGlobal;

    private long getSecondsInMilliseconds(int seconds) {
        return seconds * 100L;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_game);

        getIntentGame();

        createBlindTimerTexts();
        createButtonCountToken();
        createButtonPlayPause();
        createButtonSkipToNextLevel();

        startGame();
    }

    private void createBlindTimerTexts() {
        levelValue = findViewById(R.id.textCurrentLevelVal);
        smallBlindValue = findViewById(R.id.textCurrentSB);
        bigBlindValue = findViewById(R.id.textCurrentBB);
        timerLevel = findViewById(R.id.textTimerLevel);
        timerFinish = findViewById(R.id.textTimerFinishEstimate);

        progressBarLevel = findViewById(R.id.progressBarLevel);
        progressBarLevel.setMax(resolutionProgressBar);
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
        if (timerRunning) {
            stopTimers();
        } else {
            startTimers();
        }
    }

    private void startTimers() {
        long timeDurationLevel = getSecondsInMilliseconds(currentLevel.getDuration());
        long timeDurationGame = getSecondsInMilliseconds(game.getDurationGame());
        int countInterval = 1000;
        countDownLevel = new HourglassLevel(timeDurationLevel, countInterval, this);
        countDownLevel.startTimer();
        countDownGlobal = new HourglassGlobal(timeDurationGame, countInterval, this);
        countDownGlobal.startTimer();
        timerRunning = true;
    }

    private void stopTimers() {
        countDownLevel.stopTimer();
        countDownGlobal.stopTimer();
        timerRunning = false;
    }

    private void touchChangeLevel() {
        incrementeLevel();
    }

    private void startGame() {
        levelSelect = 0;
        updateLevel();
    }

    public void incrementeLevel() {
        if (levelSelect+1 < game.getLevelList().size()) {
            levelSelect++;
        }
        updateLevel();
        startTimers();
    }

    private void updateLevel() {

        currentLevel = game.getLevel(levelSelect);

        String levelSelectToString = levelSelect.toString();
        levelValue.setText(levelSelectToString);
        smallBlindValue.setText(currentLevel.getSmallBlindToString());
        bigBlindValue.setText(currentLevel.getBigBlindToString());
    }

    private String convertMlsSecondsToString(long milliseconds) {
        long hours = (milliseconds / 3600000);
        long minutes = (milliseconds / 60000) % 60;
        long seconds = (milliseconds / 1000) % 60;
        return hours + ":" + minutes + ":" + seconds;
    }

    public void updateTimerGlobal(long timeRemaining) {
        timerFinish.setText(convertMlsSecondsToString(timeRemaining));
    }

    public void updateTimerLevel(long timeRemaining) {
        int percentageLevel = (int) ((1 - currentLevel.ratioLeftTime(timeRemaining)) * resolutionProgressBar);
        progressBarLevel.setProgress(percentageLevel);
        timerLevel.setText(convertMlsSecondsToString(timeRemaining));

    }
}

