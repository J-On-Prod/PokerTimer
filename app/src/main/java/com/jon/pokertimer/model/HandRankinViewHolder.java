package com.jon.pokertimer.model;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jon.pokertimer.R;

public class HandRankinViewHolder extends RecyclerView.ViewHolder {

    HandRankin handRankin;

    public HandRankinViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    private void initTitle() {
        TextView titleHandRankin = itemView.findViewById(R.id.handRankinView);
        titleHandRankin.setText(handRankin.getName());
    }

    private void initRank() {
        TextView nameHandRankin_1 = itemView.findViewById(R.id.cardHighView1);
        TextView nameHandRankin_2 = itemView.findViewById(R.id.cardHighView2);
        TextView nameHandRankin_3 = itemView.findViewById(R.id.cardHighView3);
        TextView nameHandRankin_4 = itemView.findViewById(R.id.cardHighView4);
        TextView nameHandRankin_5 = itemView.findViewById(R.id.cardHighView5);

        nameHandRankin_1.setText(handRankin.getCard(0).getRank());
        nameHandRankin_2.setText(handRankin.getCard(1).getRank());
        nameHandRankin_3.setText(handRankin.getCard(2).getRank());
        nameHandRankin_4.setText(handRankin.getCard(3).getRank());
        nameHandRankin_5.setText(handRankin.getCard(4).getRank());

        nameHandRankin_1.setTextColor(handRankin.getCard(0).getColorHex());
        nameHandRankin_2.setTextColor(handRankin.getCard(1).getColorHex());
        nameHandRankin_3.setTextColor(handRankin.getCard(2).getColorHex());
        nameHandRankin_4.setTextColor(handRankin.getCard(3).getColorHex());
        nameHandRankin_5.setTextColor(handRankin.getCard(4).getColorHex());
    }

    private void initColor() {
        TextView colorHandRankin_1 = itemView.findViewById(R.id.cardColorView1);
        TextView colorHandRankin_2 = itemView.findViewById(R.id.cardColorView2);
        TextView colorHandRankin_3 = itemView.findViewById(R.id.cardColorView3);
        TextView colorHandRankin_4 = itemView.findViewById(R.id.cardColorView4);
        TextView colorHandRankin_5 = itemView.findViewById(R.id.cardColorView5);

        colorHandRankin_1.setText(handRankin.getCard(0).getColor());
        colorHandRankin_2.setText(handRankin.getCard(1).getColor());
        colorHandRankin_3.setText(handRankin.getCard(2).getColor());
        colorHandRankin_4.setText(handRankin.getCard(3).getColor());
        colorHandRankin_5.setText(handRankin.getCard(4).getColor());

        colorHandRankin_1.setTextColor(handRankin.getCard(0).getColorHex());
        colorHandRankin_2.setTextColor(handRankin.getCard(1).getColorHex());
        colorHandRankin_3.setTextColor(handRankin.getCard(2).getColorHex());
        colorHandRankin_4.setTextColor(handRankin.getCard(3).getColorHex());
        colorHandRankin_5.setTextColor(handRankin.getCard(4).getColorHex());
    }

    public void display(HandRankin handRankin) {
        this.handRankin = handRankin;
        initTitle();
        initRank();
        initColor();
    }
}
