package com.example.pokertimer.model;

import android.graphics.Color;

import java.io.Serializable;

public class Token implements Serializable, Comparable<Token> {

    private Integer value = 1;
    private Integer number = 1;
    private Integer color = Color.DKGRAY;

    public Token() {}

    public Token(int value, int number, int color) {
        this.value = value;
        this.number = number;
        this.color = color;
    }

    public int getValue() {
        return value;
    }

    public String getValueToString() {
        return value.toString();
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getNumber() {
        return number;
    }

    public String getNumberToString() {
        return number.toString();
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getColor() {
        return color;
    }

    public String getColorToString() {
        return color.toString();
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public int compareTo(Token o) {
        return this.value - o.value;
    }
}
