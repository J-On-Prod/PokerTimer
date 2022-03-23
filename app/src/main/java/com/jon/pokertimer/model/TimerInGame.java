package com.jon.pokertimer.model;

import java.util.Timer;

public class TimerInGame {

    private static final int COUNT_INTERVAL = 1000;

    private long getMinutesInMilliseconds(int minutes) {
        return minutes * 60000L;
    }

    private Game game;
    private Level currentLevel;

    private UpdateTimer updateTimer;

    private long timeDurationLevel;
    private long timeDurationGame;

    private boolean init = true;
    private boolean pause = true;

    private Timer timer;

    public TimerInGame(Game game, UpdateTimer updateTimer) {
        this.game = game;
        this.updateTimer = updateTimer;
    }

    public void changeCurrentLevel(Level level) {
        currentLevel = level;
    }

    public void touch() {
        if (init) {
            init = false;
        }
    }

    private void updateTimers() {
        timeDurationLevel = getMinutesInMilliseconds(currentLevel.getDuration());
        timeDurationGame = getMinutesInMilliseconds(game.getDurationGameLevel(currentLevel));
    }

    public interface UpdateTimer {
        public void updateTimer(long timeLevel, long timeGame);
    }
}
