package com.jon.pokertimer.model;

import android.graphics.Color;

import java.io.Serializable;
import java.util.Locale;

public class ColorToken implements Serializable {

    public static ColorToken WHITE = new ColorToken(Color.LTGRAY, "White", "Blanc");
    public static ColorToken BLUE = new ColorToken(Color.BLUE, "Blue","Bleu");
    public static ColorToken RED = new ColorToken(Color.RED, "Red","Rouge");
    public static ColorToken GREEN = new ColorToken(Color.GREEN, "Green","Vert");
    public static ColorToken BLACK = new ColorToken(Color.DKGRAY, "Black","Noir");
    public static ColorToken YELLOW = new ColorToken(Color.YELLOW, "Yellow","Jaune");
    public static ColorToken CYAN = new ColorToken(Color.CYAN, "Cyan","Cyan");
    public static ColorToken GREY = new ColorToken(Color.GRAY, "Grey","Gris");
    public static ColorToken MAGENTA = new ColorToken(Color.MAGENTA, "Magenta","Magenta");

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
    private final String nameFr;

    public ColorToken(int colorValue, String nameEn, String nameFr) {
        this.colorValue = colorValue;
        this.name = nameEn;
        this.nameFr = nameFr;
    }

    public int getColorValue() {
        return colorValue;
    }

    public String getName() {
        return name;
    }

    public String getDefaultName() {
        switch (Locale.getDefault().getLanguage()) {
            case "en":
                return name;
            case "fr":
                return nameFr;
        }
        return name;
    }

    public Boolean isSameColor(ColorToken other) {
        return this.getName().equals(other.getName());
    }
}
