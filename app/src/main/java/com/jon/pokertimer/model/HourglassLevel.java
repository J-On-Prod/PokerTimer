package com.jon.pokertimer.model;

import android.util.Log;

import com.ankushgrover.hourglass.Hourglass;
import com.jon.pokertimer.activities.InGameActivity;

import java.time.LocalDate;

public class HourglassLevel extends Hourglass {

    InGameActivity gameActivity;

    public HourglassLevel(long timeInMillis, long intervalInMillis, InGameActivity inGameActivity) {
        super(timeInMillis, intervalInMillis);
        this.gameActivity = inGameActivity;
        gameActivity.updateTimerLevel(timeInMillis);
    }

    @Override
    public void onTimerTick(long timeRemaining) {
        gameActivity.updateTimerLevel(timeRemaining);
    }

    @Override
    public void onTimerFinish() {
        gameActivity.incrementLevel();
    }
}
