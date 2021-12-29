package com.example.pokertimer.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Level implements Serializable {

    private boolean breakGame;
    private int duration;
    private int smallBlind;
    private int bigBlind;

    public Level() {
        this.breakGame = false;
        this.duration = 10;
        this.smallBlind = 1;
        this.bigBlind = 2;
    }

    public Level(boolean breakGame, int duration) {
        this.breakGame = breakGame;
        this.duration = duration;
        this.smallBlind = 1;
        this.bigBlind = 2;
    }

    public Level(boolean breakGame, int duration, int smallBlind, int bigBlind) {
        this.breakGame = breakGame;
        this.duration = duration;
        this.smallBlind = smallBlind;
        this.bigBlind = bigBlind;
    }

    protected Level(Parcel in) {
        breakGame = in.readByte() != 0;
        duration = in.readInt();
        smallBlind = in.readInt();
        bigBlind = in.readInt();
    }

    boolean getBreakGame() {
        return this.getBreakGame();
    }

    public void setBreakGame(boolean breakGame) {
        this.breakGame = breakGame;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getSmallBlind() {
        return smallBlind;
    }

    public void setSmallBlind(int smallBlind) {
        this.smallBlind = smallBlind;
    }

    public int getBigBlind() {
        return bigBlind;
    }

    public void setBigBlind(int bigBlind) {
        this.bigBlind = bigBlind;
    }

}
