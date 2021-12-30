package com.example.pokertimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.pokertimer.model.Game;

public class ChangeTokenActivity extends AppCompatActivity {

    Game game;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_token);

        Intent i = getIntent();
        game = (Game) i.getSerializableExtra("Game");
        position = i.getIntExtra("Position", 0);


    }
}