package com.jon.pokertimer.model;

import android.util.Log;

import com.ankushgrover.hourglass.Hourglass;
import com.jon.pokertimer.activities.InGameActivity;

public class HourglassGlobal extends Hourglass {

    private InGameActivity gameActivity;

    public HourglassGlobal(long timeInMillis, long intervalInMillis, InGameActivity inGameActivity) {
        super(timeInMillis, intervalInMillis);
        this.gameActivity = inGameActivity;
        gameActivity.updateTimerGlobal(timeInMillis);
    }

    @Override
    public void onTimerTick(long timeRemaining) {
        gameActivity.updateTimerGlobal(timeRemaining);
    }

    @Override
    public void onTimerFinish() {

    }
}
