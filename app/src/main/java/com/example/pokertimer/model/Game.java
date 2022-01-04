package com.example.pokertimer.model;

import static java.lang.Math.round;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Game implements Serializable {

    private ArrayList<Token> tokenList;
    private ArrayList<Token> tokensPerPlayer;
    private ArrayList<Level> levelList;
    private Integer nbPlayer = 4;
    private Integer defaultDurationLevel = 10;
    private Integer durationGame = 240;
    private Integer pauseEveryLevel = 5;
    private Integer percentageBankToken = 15;
    private Integer startSmallBlind = -1;
    private Integer totalToken = -1;
    private Integer totalCave = -1;
    private Integer totalValuePerPlayer = -1;
    private Integer totalNbPerPlayer = -1;
    private boolean calculateGame = true;

    private HashMap<Integer, Integer> tmpTime = new HashMap<Integer, Integer>();

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
        tokenList.add(new Token(1, 120, ColorToken.WHITE));
        tokenList.add(new Token(5, 60, ColorToken.BLUE));
        tokenList.add(new Token(25, 60, ColorToken.RED));
        tokenList.add(new Token(100, 60, ColorToken.GREEN));
        tokenList.add(new Token(200, 60, ColorToken.BLACK));

        levelList = new ArrayList<Level>();
    }

    /* TOKENS */

    public ArrayList<Token> getTokenList() {
        return tokenList;
    }

    public void addToken() {
        this.tokenList.add(new Token(1, 60, ColorToken.GREY));
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

    public ArrayList<Token> getTokensPerPlayer() {
        return tokensPerPlayer;
    }

    public boolean isColorExist(ColorToken colorToken) {
        for (Token token : tokenList) {
            if (token.getColor() == colorToken) {
                return true;
            }
        }
        return false;
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
        return getRatioPlay() * this.getDurationGame();
    }

    private int getTotalOfLevelPlay() {
        return getRatioPlay() * (this.durationGame / this.defaultDurationLevel);
    }

    private double getNbSteps() {
        return 1.0 / getTotalOfLevelPlay();
    }

    private double getCoefLevel() {
        double bigBlind = startSmallBlind * 2.0;
        double nbSteps = getNbSteps();
        return Math.pow(getTenthOfCave() / bigBlind, nbSteps);
    }

    private void generateLevels() {
        double coefLevel =  getCoefLevel();
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
        calculateTotalCave();
    }

    private void calculateTotalCave() {
        if (totalValuePerPlayer >= 0) {
            this.totalCave = totalValuePerPlayer * nbPlayer;
        }
    }

    private void splitTokensPlayers() {
        this.calculateTotalValueToken();
        tokensPerPlayer = new ArrayList<Token>();
        totalValuePerPlayer = 0;
        totalNbPerPlayer = 0;
        for (Token token : tokenList) {
            int nbTokenPerPlayer = (int) Math.floor(token.getNumber() * (1 - percentageBankToken) * 0.01);
            totalNbPerPlayer += nbTokenPerPlayer;
            totalValuePerPlayer += nbTokenPerPlayer * token.getValue();
            Token newToken = new Token(nbTokenPerPlayer, token.getValue(), token.getColor());
            tokensPerPlayer.add(newToken);
        }
        calculateTotalCave();
    }

    public void calculateGame() {
        if (calculateGame) {
            this.generateLevels();
            this.splitTokensPlayers();
        }
        tmpTime = new HashMap<Integer, Integer>();
    }

    public ArrayList<Level> getLevelList() {
        return levelList;
    }

    public Level getLevel(int position) {
        return levelList.get(position);
    }

    public String getNbPlayerToString() { return nbPlayer.toString(); }

    public void setNbPlayer(int nbPlayer) {
        this.nbPlayer = nbPlayer;
    }

    public Integer getTotalNbPerPlayer() {
        return totalNbPerPlayer;
    }

    public Integer getTotalValuePerPlayer() {
        return totalValuePerPlayer;
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

    public int getDurationGame() {
        return durationGame;
    }

    public String getDurationPlayToString() {
        return durationGame.toString();
    }

    public void setDurationGame(int durationGame) {
        this.durationGame = durationGame;
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

    public void setPercentageBankToken(Integer percentageBankToken) {
        this.percentageBankToken = percentageBankToken;
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

    public int getTimeLeftGame(int positionLevel) {
        if (tmpTime.containsKey(positionLevel)) {
            return tmpTime.get(positionLevel);
        }
        int res = 0;
        for (int i = positionLevel; i < levelList.size(); i++) {
            res += levelList.get(i).getDuration();
        }
        tmpTime.put(positionLevel, res);
        return res;
    }
}
