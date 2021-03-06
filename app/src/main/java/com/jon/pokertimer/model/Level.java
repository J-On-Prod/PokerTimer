package com.jon.pokertimer.model;

import android.util.Log;

import java.io.Serializable;

public class Level implements Serializable {

    private boolean breakGame;
    private Integer duration;
    private Integer durationIncrement;
    private Integer smallBlind;
    private Integer bigBlind;

    public Level() {
        this(false, 10, 1, 2);
    }

    public Level(boolean breakGame, int duration, int durationIncrement) {
        this(breakGame, duration, durationIncrement, 1, 2);
    }

    public Level(boolean breakGame, int duration, int durationIncrement, int smallBlind) {
        this(breakGame, duration, durationIncrement, smallBlind, smallBlind * 2);
    }

    public Level(boolean breakGame, int duration, int durationIncrement, int smallBlind, int bigBlind) {
        this.breakGame = breakGame;
        this.duration = duration;
        this.durationIncrement = durationIncrement;
        this.smallBlind = smallBlind;
        this.bigBlind = bigBlind;
    }

    boolean getBreakGame() {
        return this.breakGame;
    }

    public void setBreakGame(boolean breakGame) {
        this.breakGame = breakGame;
    }

    public Integer getDuration() {
        return duration;
    }

    public Integer getDurationInSeconds() {
        return duration * 60;
    }

    public String getDurationToString() {
        return duration.toString();
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Integer getDurationIncrement() {
        return durationIncrement;
    }

    public String getDurationIncrementToString() {
        return durationIncrement.toString();
    }

    public void setDurationIncrement(int durationIncrement) {
        this.durationIncrement = durationIncrement;
    }

    public String getSmallBlindToString() {
        return smallBlind.toString();
    }

    public void setSmallBlind(int smallBlind) {
        this.smallBlind = smallBlind;
    }

    public String getBigBlindToString() {
        return bigBlind.toString();
    }

    public void setBigBlind(int bigBlind) {
        this.bigBlind = bigBlind;
    }

    public double ratioLeftTime(long timeInMilliseconds) {
        return ((double) timeInMilliseconds) / (duration*60) ;
    }
}
