package com.jon.pokertimer.model;

import android.graphics.Color;

public class Card {


    public static final String COLOR_SPADE = "♠";
    public static final String COLOR_CLUB = "♣";
    public static final String COLOR_HEART = "♥";
    public static final String COLOR_DIAMOND = "♦";


    private final String rank;
    private final String color;

    public Card(String rank, String color) {
        this.rank = rank;
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public int getColorHex() {
        if (this.color.equals(COLOR_HEART) || this.color.equals(COLOR_DIAMOND)) {
            return Color.RED;
        }
        return Color.BLACK;
    }

    public String getRank() {
        return rank;
    }
}
