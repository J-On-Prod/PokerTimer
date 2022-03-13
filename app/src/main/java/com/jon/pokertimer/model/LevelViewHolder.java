package com.jon.pokertimer.model;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jon.pokertimer.R;

public class LevelViewHolder extends RecyclerView.ViewHolder {

    TextView durationView;
    TextView durationTotalView;
    TextView textSmallBlindView;
    TextView smallBlindView;
    TextView textBigBlindView;
    TextView bigBlindView;
    TextView pauseView;

    public LevelViewHolder(@NonNull View itemView) {
        super(itemView);

        durationView = (TextView) itemView.findViewById(R.id.textDuration);
        durationTotalView = (TextView) itemView.findViewById(R.id.textIncDuration);
        textSmallBlindView = (TextView) itemView.findViewById(R.id.textSmallBlind);
        smallBlindView = (TextView) itemView.findViewById(R.id.smallBlind);
        textBigBlindView = (TextView) itemView.findViewById(R.id.textBigBlind);
        bigBlindView = (TextView) itemView.findViewById(R.id.bigBlind);
        pauseView = (TextView) itemView.findViewById(R.id.breakGame);

    }

    void display(Level level) {
        int blindVisible = View.VISIBLE;
        int pauseVisible = View.INVISIBLE;
        if (level.getBreakGame()) {
            blindVisible = View.INVISIBLE;
            pauseVisible = View.VISIBLE;
        }
        pauseView.setVisibility(pauseVisible);
        textSmallBlindView.setVisibility(blindVisible);
        textBigBlindView.setVisibility(blindVisible);
        smallBlindView.setVisibility(blindVisible);
        bigBlindView.setVisibility(blindVisible);

        durationView.setText(level.getDurationToString());
        durationTotalView.setText(level.getDurationIncrementToString());
        smallBlindView.setText(level.getSmallBlindToString());
        bigBlindView.setText(level.getBigBlindToString());
    }
}
