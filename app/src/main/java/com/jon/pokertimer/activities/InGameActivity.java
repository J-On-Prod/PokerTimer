package com.jon.pokertimer.activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.jon.pokertimer.R;
import com.jon.pokertimer.model.Game;
import com.jon.pokertimer.model.Level;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class InGameActivity extends AppCompatActivity {

    private static final int RESOLUTION_PROGRESS_BAR = 500;

    private Game game;

    private TextView levelValue;
    private TextView smallBlindValue;
    private TextView bigBlindValue;
    private TextView timerLevel;
    private TextView timerFinish;
    private Button playPauseButton;
    private ProgressBar progressBarLevel;

    private Timer timer;
    private MediaPlayer bip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_in_game);

        getIntentGame();

        createBlindTimerTexts();
        createButtonCountToken();
        createButtonPlayPause();
        createButtonSkipToNextLevel();
        createButtonCardRank();
        this.bip = MediaPlayer.create(this, R.raw.bip);
        if (!game.isPauseTimer()) {
            game.setTimeDurationInOpen();
        }
        if (game.isInitTimer()) {
            this.startTimer();
        }
    }

    private void createBlindTimerTexts() {
        levelValue = findViewById(R.id.textCurrentLevelVal);
        smallBlindValue = findViewById(R.id.textCurrentSB);
        bigBlindValue = findViewById(R.id.textCurrentBB);
        timerLevel = findViewById(R.id.textTimerLevel);
        timerFinish = findViewById(R.id.textTimerFinishEstimate);

        playPauseButton = findViewById(R.id.buttonStartPause);

        progressBarLevel = findViewById(R.id.progressBarLevel);
        progressBarLevel.setMax(RESOLUTION_PROGRESS_BAR);
        progressBarLevel.setIndeterminate(false);

        updateScreenLevel();
    }

    private void getIntentGame() {
        game = (Game) getIntent().getSerializableExtra("Game");
    }

    private void createButtonCountToken() {
        Button playPauseButton = findViewById(R.id.buttonCountToken);
        playPauseButton.setOnClickListener(v -> {
            game.setLastChangeScreen();
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

    private void createButtonCardRank() {
        Button cardRankButton = findViewById(R.id.buttonCardRank);
        cardRankButton.setOnClickListener(view -> {
            game.setLastChangeScreen();
            Intent intent = new Intent(getApplicationContext(), HandRankingActivity.class);
            intent.putExtra("Game", game);
            startActivity(intent);
        });
    }

    private void touchPlayPause() {
        if (!game.isInitTimer()) {
            game.startGameTimer();
            this.startTimer();
        } else {
            game.changePlayPauseState();
        }
    }

    private void touchChangeLevel() {
        incrementLevel();
    }

    private void touchCardsRank() {

    }

    public void incrementLevel() {
        game.incrementLevel();
        updateLevel();
    }

    private void updateLevel() {
        game.resetDurationGame();
        updateScreenLevel();
    }

    private void updateScreenLevel() {
        Level currentLevel = game.getCurrentLevel();
        String levelSelectToString = String.valueOf(game.getLevelSelect()+1);
        levelValue.setText(levelSelectToString);
        smallBlindValue.setText(currentLevel.getSmallBlindToString());
        bigBlindValue.setText(currentLevel.getBigBlindToString());
        updateTimerLevel(game.getLeftTimeLevel());
        updateTimerGlobal(game.getLeftTimeGame());
    }

    private String addZero(long duration) {
        if (duration < 10) {
            return "0" + duration;
        }
        return String.valueOf(duration);
    }

    private String convertSecondsToString(long seconds) {
        if (seconds < 0) {
            return "00:00:00";
        }
        long hours = (seconds / 3600);
        long minutes = (seconds / 60) % 60;
        long secondsConv = seconds % 60;
        return addZero(hours) + ":" + addZero(minutes) + ":" + addZero(secondsConv);
    }

    public void updateTimerGlobal(long timeRemaining) {
        timerFinish.setText(convertSecondsToString(timeRemaining));
    }

    public void updateTimerLevel(long timeRemaining) {
        int percentageLevel = (int) ((game.getCurrentLevel().ratioLeftTime(timeRemaining)) * RESOLUTION_PROGRESS_BAR);
        progressBarLevel.setProgress(percentageLevel);
        timerLevel.setText(convertSecondsToString(timeRemaining));
    }

    private boolean currentLevelIsFinish() {
        return (game.getLeftTimeLevel()) < 0;
    }

    private void playBip() {
        bip.start();
    }

    public void startTimer() {
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> {
                    if (!game.isPauseTimer()) {
                        if (currentLevelIsFinish()) {
                            incrementLevel();
                            playBip();
                        }
                        game.incrementDurationGame();
                        updateTimerLevel(game.getLeftTimeLevel());
                        updateTimerGlobal(game.getLeftTimeGame());
                    }
                });
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
        game.getCurrentLevel();
    }
}

