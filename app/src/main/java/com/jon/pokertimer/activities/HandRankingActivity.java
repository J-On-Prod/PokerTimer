package com.jon.pokertimer.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.jon.pokertimer.R;
import com.jon.pokertimer.model.Card;
import com.jon.pokertimer.model.HandRankin;
import com.jon.pokertimer.model.HandRankinAdapter;

import java.util.ArrayList;
import java.util.List;

public class HandRankingActivity extends AppCompatActivity {

    ArrayList<HandRankin> handRankinList = new ArrayList<HandRankin>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hand_ranking);

        initHandRankinList();
        recyclerView = findViewById(R.id.recyclerViewHandRankin);
        HandRankinAdapter rankinAdapter = new HandRankinAdapter(handRankinList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(rankinAdapter);
    }

    private String getTitleHand(int id) {
        return getApplicationContext().getString(id);
    }

    private void initHandRankinList() {
        Card[] flushRoyalList = {
            new Card("A", Card.COLOR_HEART),
            new Card("K", Card.COLOR_HEART),
            new Card("Q", Card.COLOR_HEART),
            new Card("J", Card.COLOR_HEART),
            new Card("10", Card.COLOR_HEART),
        };
        HandRankin flushRoyal = new HandRankin(getTitleHand(R.string.flushRoyal), flushRoyalList);
        handRankinList.add(flushRoyal);
        Card[] straightFlushList = {
                new Card("4", Card.COLOR_SPADE),
                new Card("5", Card.COLOR_SPADE),
                new Card("6", Card.COLOR_SPADE),
                new Card("7", Card.COLOR_SPADE),
                new Card("8", Card.COLOR_SPADE),
        };
        HandRankin straightFlush = new HandRankin(getTitleHand(R.string.straightFlush), straightFlushList);
        handRankinList.add(straightFlush);
        Card[] fourList = {
                new Card("7", Card.COLOR_DIAMOND),
                new Card("7", Card.COLOR_SPADE),
                new Card("7", Card.COLOR_HEART),
                new Card("7", Card.COLOR_CLUB),
                new Card("Q", Card.COLOR_DIAMOND),
        };
        HandRankin four = new HandRankin(getTitleHand(R.string.fourOfKind), fourList);
        handRankinList.add(four);
        Card[] fullList = {
                new Card("A", Card.COLOR_DIAMOND),
                new Card("A", Card.COLOR_SPADE),
                new Card("A", Card.COLOR_HEART),
                new Card("9", Card.COLOR_CLUB),
                new Card("9", Card.COLOR_DIAMOND),
        };
        HandRankin full = new HandRankin(getTitleHand(R.string.fullHouse), fullList);
        handRankinList.add(full);
        Card[] flushList = {
                new Card("6", Card.COLOR_DIAMOND),
                new Card("7", Card.COLOR_DIAMOND),
                new Card("8", Card.COLOR_DIAMOND),
                new Card("9", Card.COLOR_DIAMOND),
                new Card("10", Card.COLOR_DIAMOND),
        };
        HandRankin flush = new HandRankin(getTitleHand(R.string.flush), flushList);
        handRankinList.add(flush);
        Card[] straightList = {
                new Card("8", Card.COLOR_CLUB),
                new Card("9", Card.COLOR_CLUB),
                new Card("10", Card.COLOR_CLUB),
                new Card("J", Card.COLOR_CLUB),
                new Card("Q", Card.COLOR_CLUB),
        };
        HandRankin straight = new HandRankin(getTitleHand(R.string.straight), straightList);
        handRankinList.add(straight);
        Card[] threeList = {
                new Card("4", Card.COLOR_DIAMOND),
                new Card("4", Card.COLOR_SPADE),
                new Card("4", Card.COLOR_CLUB),
                new Card("Q", Card.COLOR_DIAMOND),
                new Card("9", Card.COLOR_HEART),
        };
        HandRankin three = new HandRankin(getTitleHand(R.string.threeOfKind), threeList);
        handRankinList.add(three);
        Card[] twoPairList = {
                new Card("8", Card.COLOR_DIAMOND),
                new Card("8", Card.COLOR_SPADE),
                new Card("K", Card.COLOR_CLUB),
                new Card("K", Card.COLOR_DIAMOND),
                new Card("4", Card.COLOR_HEART),
        };
        HandRankin twoPair = new HandRankin(getTitleHand(R.string.twoPair), twoPairList);
        handRankinList.add(twoPair);
        Card[] pairList = {
                new Card("5", Card.COLOR_DIAMOND),
                new Card("5", Card.COLOR_SPADE),
                new Card("J", Card.COLOR_CLUB),
                new Card("K", Card.COLOR_DIAMOND),
                new Card("7", Card.COLOR_HEART),
        };
        HandRankin pair = new HandRankin(getTitleHand(R.string.twoPair), pairList);
        handRankinList.add(pair);
        Card[] highCardList = {
                new Card("A", Card.COLOR_DIAMOND),
                new Card("5", Card.COLOR_SPADE),
                new Card("J", Card.COLOR_CLUB),
                new Card("K", Card.COLOR_DIAMOND),
                new Card("7", Card.COLOR_HEART),
        };
        HandRankin highCard = new HandRankin(getTitleHand(R.string.twoPair), highCardList);
        handRankinList.add(highCard);
    }
}