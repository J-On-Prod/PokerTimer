package com.example.pokertimer.model;

import android.graphics.Color;

import java.io.Serializable;
import java.lang.invoke.VolatileCallSite;

public class Token implements Serializable, Comparable<Token> {

    private Integer value = 1;
    private Integer number = 1;
    private ColorToken color = ColorToken.GREY;

    public Token() {}

    public Token(int value, int number, ColorToken color) {
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

    public ColorToken getColor() {
        return color;
    }

    public String getColorToString() {
        return color.getName();
    }

    public void setColor(ColorToken color) {
        this.color = color;
    }

    public int getTotalValue() {
        return this.value * this.number;
    }

    @Override
    public int compareTo(Token o) {
        return this.value - o.value;
    }
}
