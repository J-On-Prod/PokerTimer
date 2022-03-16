package com.jon.pokertimer.model;

import android.os.CountDownTimer;

import com.jon.pokertimer.activities.InGameActivity;

public class CountDownLevel extends CountDownTimer {

    private InGameActivity inGameActivity;

    public CountDownLevel(long millisInFuture, long countDownInterval, InGameActivity activity) {
        super(millisInFuture, countDownInterval);
        this.inGameActivity = activity;
    }

    @Override
    public void onTick(long l) {
        inGameActivity.updateTimer();
    }

    @Override
    public void onFinish() {
        inGameActivity.incrementeLevel();
    }
}
