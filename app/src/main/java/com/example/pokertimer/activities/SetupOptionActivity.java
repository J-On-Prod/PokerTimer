package com.example.pokertimer.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.example.pokertimer.R;
import com.example.pokertimer.model.Game;

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
        editPercentageBankToken.setText(game.getPercentageBankTokenToString());
    }

    private void createSwitchCalulation() {
        switchCalculation = (Switch) findViewById(R.id.switchCalculation);
        switchCalculation.setChecked(game.getCalculateGame());
        switchCalculation.setOnClickListener(v -> {
            boolean checked = ((Switch) v).isChecked();
            game.setCalculateGame(checked);
        });
    }

    private void setAllValueGame() {
        int nbPlayerEdit = Integer.parseInt(editNbPlayer.getText().toString());
        game.setNbPlayer(nbPlayerEdit);
        int durationGameEdit = Integer.parseInt(editDurationGame.getText().toString());
        game.setDurationGame(durationGameEdit);
        int durationLevelEdit = Integer.parseInt(editDurationLevel.getText().toString());
        game.setDefaultDurationLevel(durationLevelEdit);
        int smallBlindEdit = Integer.parseInt(editSmallBlind.getText().toString());
        game.setStartSmallBlind(smallBlindEdit);
        int pauseEdit = Integer.parseInt(editLevelBtwPauses.getText().toString());
        game.setPauseEveryLevel(pauseEdit);
        int percentageBankEdit = Integer.parseInt(editPercentageBankToken.getText().toString());
        game.setPercentageBankToken(percentageBankEdit);
    }

    private void passIntentGame(Class<?> classSelected) {
        setAllValueGame();
        Intent intent = new Intent(getApplicationContext(), classSelected);
        intent.putExtra("Game", game);
        startActivity(intent);
    }

    private void createTokenButton() {
        Button setupTokenButton = (Button) findViewById(R.id.buttonBackToken);
        setupTokenButton.setOnClickListener(v -> passIntentGame(SetupTokenActivity.class));
    }

    private void createLevelButton() {
        Button setupLevelButton = (Button) findViewById(R.id.buttonNextLevel);
        setupLevelButton.setOnClickListener(v -> passIntentGame(SetupLevelActivity.class));
    }

    private void getIntentData() {
        game = (Game) getIntent().getSerializableExtra("Game");
        if (game.getStartSmallBlind() == -1) {
            game.generateSmallBlind();
        }
    }
}