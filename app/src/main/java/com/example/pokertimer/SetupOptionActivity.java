package com.example.pokertimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
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

        game = (Game) getIntent().getSerializableExtra("Game");

        editNbPlayer = (EditText) findViewById(R.id.editNbPlayer);
        editDurationGame = (EditText) findViewById(R.id.editDurationGame);
        editDurationLevel = (EditText) findViewById(R.id.editDurationLevel);
        editLevelBtwPauses = (EditText) findViewById(R.id.editLevelBtwPauses);

        editNbPlayer.setText(game.getNbPlayerToString());
        editDurationGame.setText(game.getDurationPlayToString());
        editDurationLevel.setText(game.getDefaultDurationLevelToString());
        editLevelBtwPauses.setText(game.getPauseEveryLevelToString());

        Button setupTokenButton = (Button) findViewById(R.id.buttonBackToken);
        setupTokenButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), SetupTokenActivity.class);
            intent.putExtra("Game", game);
            startActivity(intent);
        });

        Button setupLevelButton = (Button) findViewById(R.id.buttonNextLevel);
        setupLevelButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), SetupLevelActivity.class);
            intent.putExtra("Game", game);
            startActivity(intent);
        });
    }
}