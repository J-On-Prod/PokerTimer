package com.jon.pokertimer.model;

public class HandRankin {

    private final Card[] cardList;
    private final String name;

    public HandRankin(String name, Card[] cardList) {
        this.name = name;
        this.cardList = cardList;
    }

    public String getName() {
        return name;
    }

    public Card[] getCardList() {
        return cardList;
    }

    public Card getCard(int position) {
        return this.cardList[position];
    }
}
