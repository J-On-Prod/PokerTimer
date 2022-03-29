package com.jon.pokertimer.model;

import static java.lang.Math.round;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

public class Game implements Serializable {

    private ArrayList<Token> tokenList;
    private ArrayList<Token> tokensPerPlayer;
    private ArrayList<Token> tokensCount;
    private ArrayList<Level> levelList;

    private Level currentLevel;

    private Date lastChangeScreen;

    private Integer levelSelect = 0;
    private Integer nbPlayer = 4;
    private Integer durationLevel = 10;
    private Integer durationGame = 120;
    private Integer pauseEveryLevel = 5;
    private Integer percentageBankToken = 15;
    private Integer startSmallBlind = -1;
    private Integer totalToken = -1;
    private Integer totalCave = -1;
    private Integer totalValuePerPlayer = -1;
    private Integer totalNbPerPlayer = -1;

    private long timeDurationLevel = 0;

    private String name = "New game";

    private boolean calculateGame = true;
    private boolean initTimer = false;
    private boolean pauseTimer = true;

    private HashMap<Integer, Integer> tmpTime = new HashMap<Integer, Integer>();

    private static int roundModulo(double val, int modulo) {
        val = round(val);
        int difference = (int) round(val % modulo);
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

    public String getName() {
        return name;
    }

    public Integer getNbPlayer() {
        return nbPlayer;
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

    public void deleteToken(int position) {
        this.tokenList.remove(position);
    }

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
            if (token.getColor().isSameColor(colorToken)) {
                return true;
            }
        }
        return false;
    }

    /* TOKEN COUNT */

    public void generateTokenCount() {
        tokensCount = new ArrayList<>();
        for (Token token : this.tokenList) {
            tokensCount.add(new Token(token.getValue(), 0, token.getColor()));
        }
    }

    public ArrayList<Token> getTokensCount() {
        return tokensCount;
    }

    public void resetTokenCount() {
        for (Token token : this.tokensCount) {
            token.setNumber(0);
        }
    }

    public void addTokenNumber(int position, int number) {
        this.tokensCount.get(position).addNumber(number);
    }

    public void setTokenNumber(int position, int number) {
        this.tokensCount.get(position).setNumber(number);
    }

    /* LEVELS */

    public Integer getDurationLevel() {
        return durationLevel;
    }

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
        int ratioPause = 1 / (this.getPauseEveryLevel() + 1);
        return 1 - ratioPause;
    }

    private int getRealTimePlay() {
        return getRatioPlay() * this.getDurationGame();
    }

    private int getTotalOfLevelPlay() {
        return getRatioPlay() * (this.durationGame / this.durationLevel);
    }

    private double getCoefLevel() {
        double nbSteps = getTotalOfLevelPlay();
        return Math.pow(getTenthOfCave(), 1/nbSteps);
    }

    private void generateLevels() {
        calculateTotalCave();
        double coefLevel = getCoefLevel();
        double increment = startSmallBlind.doubleValue();
        int nbLvl = 0;
        levelList = new ArrayList<Level>();

        while (increment < totalCave) {
            int moduloLevel = nbLvl % (pauseEveryLevel + 1);
            boolean breakGame = moduloLevel == (pauseEveryLevel);
            int durationIncrement = durationLevel * nbLvl;
            Level lvlCreate = new Level(breakGame, durationLevel, durationIncrement, roundValueToken(increment));
            levelList.add(lvlCreate);
            if (!breakGame) {
                increment *= coefLevel;
            }
            nbLvl++;
        }
    }

    private void calculateTotalCave() {
        if (totalValuePerPlayer > 0) {
            this.totalCave = totalValuePerPlayer * nbPlayer;
        }
    }

    private void splitTokensPlayers() {
        this.calculateTotalValueToken();
        tokensPerPlayer = new ArrayList<Token>();
        totalValuePerPlayer = 0;
        totalNbPerPlayer = 0;
        for (Token token : tokenList) {
            int nbTokenPerPlayer = (int) Math.floor((token.getNumber() * (1 - (percentageBankToken * 0.01)))/nbPlayer);
            totalNbPerPlayer += nbTokenPerPlayer;
            totalValuePerPlayer += nbTokenPerPlayer * token.getValue();
            Token newToken = new Token(token.getValue(), nbTokenPerPlayer, token.getColor());
            tokensPerPlayer.add(newToken);
        }
        calculateTotalCave();
    }

    public void calculateGame() {
        tmpTime = new HashMap<Integer, Integer>();
        if (calculateGame) {
            this.splitTokensPlayers();
            this.generateLevels();
        }
    }

    public ArrayList<Level> getLevelList() {
        return levelList;
    }

    public Level getLevel(int position) {
        return levelList.get(position);
    }

    public String getNbPlayerToString() {
        return nbPlayer.toString();
    }

    public void setNbPlayer(int nbPlayer) {
        this.nbPlayer = nbPlayer;
    }

    public String getTotalNbPerPlayerToString() {
        return totalNbPerPlayer.toString();
    }

    public String getTotalValuePerPlayerToString() {
        return totalValuePerPlayer.toString();
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

    public int getDurationGameLevel(Level level) {
        return durationGame - level.getDurationIncrement();
    }

    public String getDurationPlayToString() {
        return durationGame.toString();
    }

    public void setDurationGame(int durationGame) {
        this.durationGame = durationGame;
    }

    public String getDefaultDurationLevelToString() {
        return durationLevel.toString();
    }

    public void setDurationLevel(int durationLevel) {
        this.durationLevel = durationLevel;
    }

    public int getPauseEveryLevel() {
        return pauseEveryLevel;
    }

    public String getPercentageBankTokenToString() {
        return percentageBankToken.toString();
    }

    public void setPercentageBankToken(Integer percentageBankToken) {
        this.percentageBankToken = percentageBankToken;
    }

    public String getPauseEveryLevelToString() {
        return pauseEveryLevel.toString();
    }

    public void setPauseEveryLevel(int pauseEveryLevel) {
        this.pauseEveryLevel = pauseEveryLevel;
    }

    public boolean getCalculateGame() {
        return calculateGame;
    }

    public void setCalculateGame(boolean calculateGame) {
        this.calculateGame = calculateGame;
    }

    public Integer getLevelSelect() {
        return levelSelect;
    }

    public Level getCurrentLevel() {
        if (currentLevel == null) {
            currentLevel = getLevel(levelSelect);
        }
        return currentLevel;
    }

    public void incrementLevel() {
        if (levelSelect+1 < levelList.size()) {
            levelSelect++;
        }
        currentLevel = getLevel(levelSelect);
        resetDurationGame();
    }

    public void resetDurationGame() {
        timeDurationLevel = 0;
    }

    public void incrementDurationGame() {
        timeDurationLevel++;
    }

    public long getLeftTimeLevel() {
        return (currentLevel.getDuration() * 60) - timeDurationLevel;
    }

    public long getLeftTimeGame() {
        return ((durationGame * 60) - (currentLevel.getDurationIncrement() * 60)) - timeDurationLevel;
    }

    public boolean isInitTimer() {
        return this.initTimer;
    }

    public boolean isPauseTimer() {
        return pauseTimer;
    }

    public void changePlayPauseState() {
        this.pauseTimer = !pauseTimer;
    }

    public void startGameTimer() {
        this.initTimer = true;
        this.pauseTimer = false;
    }

    public void setLastChangeScreen() {
        lastChangeScreen = new Date();
    }

    public void setTimeDurationInOpen() {
        if (!pauseTimer && lastChangeScreen != null) {
            long secondsInOtherScreen = ((new Date().getTime()) - lastChangeScreen.getTime()) / 1000;
            long accumulateDuration = secondsInOtherScreen + timeDurationLevel;
            while (accumulateDuration > currentLevel.getDurationInSeconds() ) {
                if ((currentLevel.getDurationInSeconds() - accumulateDuration) < 0) {
                    accumulateDuration -= currentLevel.getDurationInSeconds();
                    incrementLevel();
                }
            }
            timeDurationLevel = currentLevel.getDuration() - accumulateDuration;
        }

    }

}


