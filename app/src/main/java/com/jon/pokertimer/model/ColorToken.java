package com.jon.pokertimer.model;

import android.graphics.Color;

import java.io.Serializable;

public class ColorToken implements Serializable {

    public static ColorToken WHITE = new ColorToken(Color.LTGRAY, "BLANC");
    public static ColorToken BLUE = new ColorToken(Color.BLUE, "BLEU");
    public static ColorToken RED = new ColorToken(Color.RED, "ROUGE");
    public static ColorToken GREEN = new ColorToken(Color.GREEN, "VERT");
    public static ColorToken BLACK = new ColorToken(Color.DKGRAY, "NOIR");
    public static ColorToken YELLOW = new ColorToken(Color.YELLOW, "JAUNE");
    public static ColorToken CYAN = new ColorToken(Color.CYAN, "CYAN");
    public static ColorToken GREY = new ColorToken(Color.GRAY, "GRIS");
    public static ColorToken MAGENTA = new ColorToken(Color.MAGENTA, "MAGENTA");

    public static ColorToken[] listAllColor = {WHITE, BLUE, RED, GREEN, BLACK, YELLOW, CYAN, GREY, MAGENTA};

    public static ColorToken getColorTokenByName(String name) {
        for (ColorToken colorToken : listAllColor) {
            if (colorToken.getName().equals(name)) {
                return colorToken;
            }
        }
        return null;
    }

    private final int colorValue;
    private final String name;

    public ColorToken(int colorValue, String name) {
        this.colorValue = colorValue;
        this.name = name;
    }

    public int getColorValue() {
        return colorValue;
    }

    public String getName() {
        return name;
    }

    public Boolean isSameColor(ColorToken other) {
        return this.getName().equals(other.getName());
    }
}