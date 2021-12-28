package com.example.pokertimer.model;

import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Game implements Parcelable {

    private ArrayList<Token> tokenList;
    private ArrayList<Level> levelList;
    private int nbPlayer = 4;
    private int defaultDurationLevel = 10;
    private int durationPlay = 240;
    private int pauseEveryLevel = 5;

    public Game() {
        tokenList = new ArrayList<Token>();
        tokenList.add(new Token(1, 120, Color.LTGRAY));
        tokenList.add(new Token(5, 60, Color.BLUE));
        tokenList.add(new Token(25, 60, Color.RED));
        tokenList.add(new Token(100, 60, Color.GREEN));
        tokenList.add(new Token(200, 60, Color.DKGRAY));

        levelList = new ArrayList<Level>();
    }

    protected Game(Parcel in) {
        tokenList = in.readArrayList(null);
        levelList = in.readArrayList(null);
        nbPlayer = in.readInt();
        defaultDurationLevel = in.readInt();
        durationPlay = in.readInt();
        pauseEveryLevel = in.readInt();
    }

    public static final Creator<Game> CREATOR = new Creator<Game>() {
        @Override
        public Game createFromParcel(Parcel in) {
            return new Game(in);
        }

        @Override
        public Game[] newArray(int size) {
            return new Game[size];
        }
    };

    public ArrayList<Token> getTokenList() {
        return tokenList;
    }

    public void addToken() {
        this.tokenList.add(new Token(1, 60, Color.GRAY));
    }

    public void deleteToken(int position) { this.tokenList.remove(position); }

    public Token getToken(int position) {
        return this.tokenList.get(position);
    }

    public void setToken(int position, Token token) {
        this.tokenList.set(position, token);
    }

    public int getNbPlayer() {
        return nbPlayer;
    }

    public void setNbPlayer(int nbPlayer) {
        this.nbPlayer = nbPlayer;
    }

    public int getDurationPlay() {
        return durationPlay;
    }

    public void setDurationPlay(int durationPlay) {
        this.durationPlay = durationPlay;
    }

    public int getDefaultDurationLevel() {
        return defaultDurationLevel;
    }

    public void setDefaultDurationLevel(int defaultDurationLevel) {
        this.defaultDurationLevel = defaultDurationLevel;
    }

    public int getPauseEveryLevel() {
        return pauseEveryLevel;
    }

    public void setPauseEveryLevel(int pauseEveryLevel) {
        this.pauseEveryLevel = pauseEveryLevel;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(tokenList);
        dest.writeList(levelList);
        dest.writeInt(nbPlayer);
        dest.writeInt(defaultDurationLevel);
        dest.writeInt(durationPlay);
        dest.writeInt(pauseEveryLevel);
    }
}
