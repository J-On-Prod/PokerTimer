package com.example.pokertimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.example.pokertimer.model.Game;

import java.util.Collections;

public class SetupOptionActivity extends AppCompatActivity {

    Game game;
    EditText editNbPlayer;
    EditText editDurationGame;
    EditText editDurationLevel;
    EditText editSmallBlind;
    EditText editLevelBtwPauses;
    EditText editPercentageBankToken;
    Switch switchCalculation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_option);

        game = (Game) getIntent().getSerializableExtra("Game");
        if (game.getStartSmallBlind() == -1) {
            game.generateSmallBlind();
        }

        editNbPlayer = (EditText) findViewById(R.id.editNbPlayer);
        editDurationGame = (EditText) findViewById(R.id.editDurationGame);
        editDurationLevel = (EditText) findViewById(R.id.editDurationLevel);
        editSmallBlind = (EditText) findViewById(R.id.editSmallBlind);
        editLevelBtwPauses = (EditText) findViewById(R.id.editDefaultPauses);
        editPercentageBankToken = (EditText) findViewById(R.id.editPercentageBank);
        switchCalculation = (Switch) findViewById(R.id.switchCalculation);

        editNbPlayer.setText(game.getNbPlayerToString());
        editDurationGame.setText(game.getDurationPlayToString());
        editDurationLevel.setText(game.getDefaultDurationLevelToString());
        editSmallBlind.setText(game.getStartSmallBlindToString());
        editLevelBtwPauses.setText(game.getPauseEveryLevelToString());
        editPercentageBankToken.setText(game.getPercentageBankToken());

        switchCalculation.setChecked(game.getCalculateGame());
        switchCalculation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((Switch) v).isChecked();
                game.setCalculateGame(checked);
            }
        });

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