package com.example.pokertimer.model;

public class Level {

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
}
