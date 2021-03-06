package com.jon.pokertimer.model;

import java.io.Serializable;

public class Token implements Serializable, Comparable<Token> {

    private Integer value = 1;
    private Integer number = 1;
    private ColorToken color = ColorToken.GREY;

    public Token() {
    }

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

    public void addNumber(int number) {
        this.number += number;
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
        return color.getDefaultName();
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
