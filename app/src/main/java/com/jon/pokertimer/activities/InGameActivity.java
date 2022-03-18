package com.jon.pokertimer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

    private static final int COUNT_INTERVAL = 1000;
    private static final int RESOLUTION_PROGRESS_BAR = 500;

    private Game game;

    private Integer levelSelect;
    private Level currentLevel;

    private long timeDurationLevel;
    private long timeDurationGame;

    private TextView levelValue;
    private TextView smallBlindValue;
    private TextView bigBlindValue;
    private TextView timerLevel;
    private TextView timerFinish;
    private ProgressBar progressBarLevel;

    private boolean timerRunning = false;

    // https://github.com/groverankush/Hourglass
    private HourglassLevel countDownLevel;
    private HourglassGlobal countDownGlobal;

    private long getMinutesInMilliseconds(int minutes) {
        return minutes * 60000L;
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
        if (timerRunning) {
            pauseTimers();
        } else {
            resumeTimers();
        }
    }

    private void initTimers() {
        resetTimerLevel();
        resetTimerGame();
        startTimers();
    }

    private void resetTimerLevel() {
        timeDurationLevel = getMinutesInMilliseconds(currentLevel.getDuration());
    }

    private void resetTimerGame() {
        timeDurationGame = getMinutesInMilliseconds(game.getDurationGameLevel(currentLevel));
    }

    private void startTimers() {
        setTimerGame(timeDurationGame);
        setTimerLevel(timeDurationLevel);
    }

    private void setTimerLevel(long timeDurationLevel) {
        if (countDownLevel == null) {
            countDownLevel = new HourglassLevel(timeDurationLevel, COUNT_INTERVAL, this);
        } else {
            countDownLevel.setTime(timeDurationLevel);
        }

    }

    private void setTimerGame(long timeDurationGame) {
        if (countDownGlobal == null) {
            countDownGlobal = new HourglassGlobal(timeDurationGame, COUNT_INTERVAL, this);
        } else {
            countDownGlobal.setTime(timeDurationGame);
        }
    }

    private void resumeTimers() {
        countDownLevel.resumeTimer();
        countDownGlobal.resumeTimer();
        timerRunning = true;
    }

    private void pauseTimers() {
        countDownLevel.pauseTimer();
        countDownGlobal.pauseTimer();
        timerRunning = false;
    }

    private void touchChangeLevel() {
        incrementLevel();
    }

    private void startGame() {
        levelSelect = 0;
        updateLevel();
        initTimers();
    }

    public void incrementLevel() {
        if (levelSelect+1 < game.getLevelList().size()) {
            levelSelect++;
        }
        updateLevel();
        resetTimerGame();
        startTimers();
    }

    private void updateLevel() {

        currentLevel = game.getLevel(levelSelect);

        String levelSelectToString = String.valueOf(levelSelect+1);
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
        Log.d("PokerApp", String.valueOf(percentageLevel));
        progressBarLevel.setProgress(percentageLevel);
        timerLevel.setText(convertMlsSecondsToString(timeRemaining));

    }
}

