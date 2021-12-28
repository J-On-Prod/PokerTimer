package com.example.pokertimer.model;

import android.graphics.Color;

import java.util.ArrayList;

public class Game {

    private ArrayList<Token> tokenList;
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
    }

    public ArrayList<Token> getTokenList() {
        return tokenList;
    }

    public void addToken() {
        this.tokenList.add(new Token(1, 60, Color.GRAY));
    }

    public Token getToken(int position) {
        return this.tokenList.get(position);
    }

    public void setToken(int position, Token token) {
        this.tokenList.set(position, token);
    }
}
