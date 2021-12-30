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

        getIntentData();

        createNbPlayer();
        createDurationGame();
        createDurationLevel();
        createSmallBlind();
        createLevelBetweenPauses();
        createPercentageBankToken();
        createSwitchCalulation();
        createTokenButton();
        createLevelButton();
    }

    private void createNbPlayer() {
        editNbPlayer = (EditText) findViewById(R.id.editNbPlayer);
        editNbPlayer.setText(game.getNbPlayerToString());
    }

    private void createDurationGame() {
        editDurationGame = (EditText) findViewById(R.id.editDurationGame);
        editDurationGame.setText(game.getDurationPlayToString());
    }

    private void createDurationLevel() {
        editDurationLevel = (EditText) findViewById(R.id.editDurationLevel);
        editDurationLevel.setText(game.getDefaultDurationLevelToString());
    }

    private void createSmallBlind() {
        editSmallBlind = (EditText) findViewById(R.id.editSmallBlind);
        editSmallBlind.setText(game.getStartSmallBlindToString());
    }

    private void createLevelBetweenPauses() {
        editLevelBtwPauses = (EditText) findViewById(R.id.editDefaultPauses);
        editLevelBtwPauses.setText(game.getPauseEveryLevelToString());
    }

    private void createPercentageBankToken() {
        editPercentageBankToken = (EditText) findViewById(R.id.editPercentageBank);
        editPercentageBankToken.setText(game.getPercentageBankToken());
    }

    private void createSwitchCalulation() {
        switchCalculation = (Switch) findViewById(R.id.switchCalculation);
        switchCalculation.setChecked(game.getCalculateGame());
        switchCalculation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((Switch) v).isChecked();
                game.setCalculateGame(checked);
            }
        });
    }

    private void createTokenButton() {
        Button setupTokenButton = (Button) findViewById(R.id.buttonBackToken);
        setupTokenButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), SetupTokenActivity.class);
            intent.putExtra("Game", game);
            startActivity(intent);
        });
    }

    private void createLevelButton() {
        Button setupLevelButton = (Button) findViewById(R.id.buttonNextLevel);
        setupLevelButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), SetupLevelActivity.class);
            intent.putExtra("Game", game);
            startActivity(intent);
        });
    }

    private void getIntentData() {
        game = (Game) getIntent().getSerializableExtra("Game");
        if (game.getStartSmallBlind() == -1) {
            game.generateSmallBlind();
        }
    }
}