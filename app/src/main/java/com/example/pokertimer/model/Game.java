package com.example.pokertimer.model;

import android.graphics.Color;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Game implements Serializable {

    private final ArrayList<Token> tokenList;
    private final ArrayList<Level> levelList;
    private Integer nbPlayer = 4;
    private Integer defaultDurationLevel = 10;
    private Integer durationPlay = 240;
    private Integer pauseEveryLevel = 5;

    public Game() {
        tokenList = new ArrayList<Token>();
        tokenList.add(new Token(1, 120, Color.LTGRAY));
        tokenList.add(new Token(5, 60, Color.BLUE));
        tokenList.add(new Token(25, 60, Color.RED));
        tokenList.add(new Token(100, 60, Color.GREEN));
        tokenList.add(new Token(200, 60, Color.DKGRAY));

        levelList = new ArrayList<Level>();
    }

    public ArrayList<Token> getTokenList() {
        return tokenList;
    }

    public void addToken() {
        this.tokenList.add(new Token(1, 60, Color.GRAY));
        this.sortTokens();
    }

    private void sortTokens() {
        Collections.sort(this.tokenList);
    }

    public void deleteToken(int position) { this.tokenList.remove(position); }

    public Token getToken(int position) {
        return this.tokenList.get(position);
    }

    public void setToken(int position, Token token) {
        this.tokenList.set(position, token);
    }

    public int getNbPlayer() { return nbPlayer; }

    public String getNbPlayerToString() { return nbPlayer.toString(); }

    public void setNbPlayer(int nbPlayer) {
        this.nbPlayer = nbPlayer;
    }

    public int getDurationPlay() {
        return durationPlay;
    }

    public String getDurationPlayToString() {
        return durationPlay.toString();
    }

    public void setDurationPlay(int durationPlay) {
        this.durationPlay = durationPlay;
    }

    public int getDefaultDurationLevel() {
        return defaultDurationLevel;
    }

    public String getDefaultDurationLevelToString() {
        return defaultDurationLevel.toString();
    }

    public void setDefaultDurationLevel(int defaultDurationLevel) {
        this.defaultDurationLevel = defaultDurationLevel;
    }

    public int getPauseEveryLevel() {
        return pauseEveryLevel;
    }

    public String getPauseEveryLevelToString() { return pauseEveryLevel.toString(); }

    public void setPauseEveryLevel(int pauseEveryLevel) {
        this.pauseEveryLevel = pauseEveryLevel;
    }

}
