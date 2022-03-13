package com.jon.pokertimer.activities;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.jon.pokertimer.R;
import com.jon.pokertimer.model.Game;
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
    private long timeLeftLevelMls = getSecondsInMilliseconds(600);
    private CountDownTimer countDownLevel;

    private long getSecondsInMilliseconds(int seconds) {
        return seconds * 100L;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_game);

        getIntentGame();
        createBlindTimerTexts();
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
        countDownLevel = new CountDownTimer(timeDurationLevel, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftLevelMls = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
                incrementeLevel();
            }
        }.start();
        timerRunning = true;
    }

    private void stopTimers() {
        countDownLevel.cancel();
        timerRunning = false;
    }

    private void touchChangeLevel() {
        incrementeLevel();
    }

    private void startGame() {
        levelSelect = 0;
        updateLevel();
    }

    private void incrementeLevel() {
        levelSelect++;
        updateLevel();
        startTimers();
    }

    private void updateLevel() {

        currentLevel = game.getLevel(levelSelect);
        timeLeftLevelMls = currentLevel.getDuration();

        String levelSelectToString = "Niveau : " + levelSelect.toString();
        levelValue.setText(levelSelectToString);
        smallBlindValue.setText(currentLevel.getSmallBlindToString());
        bigBlindValue.setText(currentLevel.getBigBlindToString());
    }

    private String convertMlsSecondsToString(long milliseconds) {
        Long hours = (milliseconds / 3600000) / 1000;
        Long minutes = (milliseconds / 60000 % 3600000) / 1000;
        Long seconds = (milliseconds % 60000) / 1000;
        return hours.toString() + ":" + minutes.toString() + ":" + seconds.toString();
    }

    private void updateTimer() {
        int percentageLevel = (int) ((1 - currentLevel.ratioLeftTime(timeLeftLevelMls)) * resolutionProgressBar);
        progressBarLevel.setProgress(percentageLevel);
        timerLevel.setText(convertMlsSecondsToString(timeLeftLevelMls));
        long timeLeftGlobal = game.getTimeLeftGame(levelSelect) - timeLeftLevelMls;
        timerFinish.setText(convertMlsSecondsToString(timeLeftGlobal));
    }
}

