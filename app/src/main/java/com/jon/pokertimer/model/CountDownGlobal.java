package com.jon.pokertimer.model;

import android.os.CountDownTimer;

import com.jon.pokertimer.activities.InGameActivity;

public class CountDownGlobal extends CountDownTimer {

    private InGameActivity inGameActivity;

    public CountDownGlobal(long millisInFuture, long countDownInterval, InGameActivity activity) {
        super(millisInFuture, countDownInterval);
        this.inGameActivity = activity;
    }

    @Override
    public void onTick(long l) {
        inGameActivity.updateTimer();
    }

    @Override
    public void onFinish() {

    }
}
