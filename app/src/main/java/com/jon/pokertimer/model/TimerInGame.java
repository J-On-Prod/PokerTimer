package com.jon.pokertimer.model;

public class TimerInGame {

    private long getMinutesInMilliseconds(int minutes) {
        return minutes * 60000L;
    }

    private Game game;
    private Level currentLevel;

    private long timeDurationLevel;
    private long timeDurationGame;

    private boolean init = true;
    private boolean pause = true;

    public TimerInGame(Game game) {
        this.game = game;
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
}
