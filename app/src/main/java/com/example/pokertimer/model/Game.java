package com.example.pokertimer.model;

import static java.lang.Math.round;

import android.graphics.Color;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Game implements Serializable {

    private final ArrayList<Token> tokenList;
    private ArrayList<Token> tokensPerPlayer;
    private ArrayList<Level> levelList;
    private Integer nbPlayer = 4;
    private Integer defaultDurationLevel = 10;
    private Integer durationPlay = 240;
    private Integer pauseEveryLevel = 5;
    private Integer percentageBankToken = 15;
    private Integer startSmallBlind = -1;
    private Integer totalToken = -1;
    private Integer totalCave = -1;
    private Integer totalPerPlayer = -1;
    private boolean calculateGame = true;

    private static int roundModulo(double val, int modulo) {
        int difference = (int) (val % modulo);
        if (difference < modulo / 2) {
            return (int) round(val - difference);
        }
        return (int) round(val + (modulo - difference));
    }

    private static int roundValueToken(double val) {
        int nb_zero = (int) Math.floor(Math.log10(val));
        if (nb_zero == 1) {
            return roundModulo(val, 5);
        } else if (nb_zero > 1) {
            return roundModulo(val, (int) Math.pow(10, nb_zero - 1));
        }
        return (int) round(val);
    }

    public Game() {
        tokenList = new ArrayList<Token>();
        tokenList.add(new Token(1, 120, Color.LTGRAY));
        tokenList.add(new Token(5, 60, Color.BLUE));
        tokenList.add(new Token(25, 60, Color.RED));
        tokenList.add(new Token(100, 60, Color.GREEN));
        tokenList.add(new Token(200, 60, Color.DKGRAY));

        levelList = new ArrayList<Level>();
    }

    /* TOKENS */

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

    /* LEVELS */

    private void calculateTotalValueToken() {
        this.totalToken = 0;
        for (Token token : tokenList) {
            totalToken += token.getTotalValue();
        }
    }

    private int getTenthOfCave() {
        return totalCave / 10;
    }

    private int getRatioPlay() {
        int ratioPause = 1/(this.getPauseEveryLevel()+1);
        return 1-ratioPause;
    }

    private int getRealTimePlay() {
        return getRatioPlay() * this.getDurationPlay();
    }

    private int getTotalOfLevelPlay() {
        return getRatioPlay() * (this.durationPlay / this.defaultDurationLevel);
    }

    private void generateLevels() {
        double bigBlind = startSmallBlind * 2.0;
        double nbSteps = 1.0 / getTotalOfLevelPlay();
        double coefLevel = Math.pow(getTenthOfCave() / bigBlind, nbSteps);

        double increment = startSmallBlind.doubleValue();
        int nbLvl = 0;
        levelList = new ArrayList<Level>();

        while (increment < totalCave) {
            int moduloLevel = nbLvl%(pauseEveryLevel+1);
            boolean breakGame = moduloLevel == (pauseEveryLevel-1);
            int durationIncrement = defaultDurationLevel * nbLvl;
            Level lvlCreate = new Level(breakGame, defaultDurationLevel, durationIncrement, roundValueToken(increment));
            levelList.add(lvlCreate);
            increment *= coefLevel;
            nbLvl++;
        }
    }

    private void splitTokensPlayers() {
        this.calculateTotalValueToken();
        tokensPerPlayer = new ArrayList<Token>();
        totalPerPlayer = 0;
        for (Token token : tokenList) {
            int nbTokenPerPlayer = (int) Math.floor(token.getNumber() * (1 - percentageBankToken) * 0.01);
            totalPerPlayer += nbTokenPerPlayer * token.getValue();
            Token newToken = new Token(nbTokenPerPlayer, token.getValue(), token.getColor());
            tokensPerPlayer.add(newToken);
        }
        this.totalCave = totalPerPlayer * nbPlayer;
    }

    public void calculateGame() {
        if (calculateGame) {
            this.generateLevels();
            this.splitTokensPlayers();
        }
    }

    public ArrayList<Level> getLevelList() {
        return levelList;
    }

    public Level getLevel(int position) {
        return levelList.get(position);
    }

    public void setLevel(int position, Level newLevel) {
        levelList.set(position, newLevel);
    }

    public int getNbPlayer() { return nbPlayer; }

    public String getNbPlayerToString() { return nbPlayer.toString(); }

    public void setNbPlayer(int nbPlayer) {
        this.nbPlayer = nbPlayer;
    }

    public Integer getTotalPerPlayer() {
        return totalPerPlayer;
    }

    public Integer getStartSmallBlind() {
        return startSmallBlind;
    }

    public String getStartSmallBlindToString() {
        return startSmallBlind.toString();
    }

    public void generateSmallBlind() {
        this.sortTokens();
        startSmallBlind = tokenList.get(0).getValue();
    }

    public void setStartSmallBlind(Integer startSmallBlind) {
        this.startSmallBlind = startSmallBlind;
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

    public Integer getPercentageBankToken() {
        return percentageBankToken;
    }

    public void setPauseEveryLevel(Integer pauseEveryLevel) {
        this.pauseEveryLevel = pauseEveryLevel;
    }

    public String getPauseEveryLevelToString() { return pauseEveryLevel.toString(); }

    public void setPauseEveryLevel(int pauseEveryLevel) {
        this.pauseEveryLevel = pauseEveryLevel;
    }

    public boolean getCalculateGame() {
        return calculateGame;
    }

    public void setCalculateGame(boolean calculateGame) {
        this.calculateGame = calculateGame;
    }
}
