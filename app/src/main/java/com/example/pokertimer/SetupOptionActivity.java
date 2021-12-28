package com.example.pokertimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.example.pokertimer.model.Game;

public class SetupOptionActivity extends AppCompatActivity {

    Game game;
    EditText editNbPlayer;
    EditText editDurationGame;
    EditText editDurationLevel;
    EditText editLevelBtwPauses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_option);

        Intent i = getIntent();
        game = i.getBundleExtra("BundleGame").getParcelable("Game");

        editNbPlayer = (EditText) findViewById(R.id.editNbPlayer);
        editDurationGame = (EditText) findViewById(R.id.editDurationGame);
        editDurationLevel = (EditText) findViewById(R.id.editDurationLevel);
        editLevelBtwPauses = (EditText) findViewById(R.id.editLevelBtwPauses);

        editNbPlayer.setText(game.getNbPlayer());
        editDurationGame.setText(game.getDurationPlay());
        editDurationLevel.setText(game.getDefaultDurationLevel());
        editLevelBtwPauses.setText(game.getPauseEveryLevel());
    }
}