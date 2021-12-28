package com.example.pokertimer.model;

import android.graphics.Color;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

public class Token implements Parcelable {

    private Integer value = 1;
    private Integer number = 1;
    private Integer color = Color.DKGRAY;

    public Token() {}

    public Token(int value, int number, int color) {
        this.value = value;
        this.number = number;
        this.color = color;
    }

    protected Token(Parcel in) {
        if (in.readByte() == 0) {
            value = null;
        } else {
            value = in.readInt();
        }
        if (in.readByte() == 0) {
            number = null;
        } else {
            number = in.readInt();
        }
        if (in.readByte() == 0) {
            color = null;
        } else {
            color = in.readInt();
        }
    }

    public static final Creator<Token> CREATOR = new Creator<Token>() {
        @Override
        public Token createFromParcel(Parcel in) {
            return new Token(in);
        }

        @Override
        public Token[] newArray(int size) {
            return new Token[size];
        }
    };

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
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (value == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(value);
        }
        if (number == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(number);
        }
        if (color == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(color);
        }
    }
}
