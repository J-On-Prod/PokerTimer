package com.jon.pokertimer.model;

import com.ankushgrover.hourglass.Hourglass;
import com.jon.pokertimer.activities.InGameActivity;

public class HourglassLevel extends Hourglass {

    InGameActivity gameActivity;

    public HourglassLevel(long timeInMillis, long intervalInMillis, InGameActivity inGameActivity) {
        super(timeInMillis, intervalInMillis);
        this.gameActivity = inGameActivity;
    }

    @Override
    public void onTimerTick(long timeRemaining) {
        gameActivity.updateTimerLevel(timeRemaining);
    }

    @Override
    public void onTimerFinish() {
        gameActivity.incrementeLevel();
    }
}
