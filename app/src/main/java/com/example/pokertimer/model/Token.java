package com.example.pokertimer.model;

import android.graphics.Color;
import android.os.Build;

import androidx.annotation.RequiresApi;

public class Token {

    private Integer value;
    private Integer number;
    private Integer color;

    public Token() {
        this.value = 1;
        this.number = 1;
        this.color = Color.BLACK;
    }

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
}
