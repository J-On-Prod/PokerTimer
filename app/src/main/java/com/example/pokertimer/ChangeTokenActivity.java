package com.example.pokertimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.pokertimer.model.ColorToken;
import com.example.pokertimer.model.ColorTokenAdapter;
import com.example.pokertimer.model.Game;
import com.example.pokertimer.model.Token;

import java.util.ArrayList;

public class ChangeTokenActivity extends AppCompatActivity {

    Game game;
    Token token;
    int position;

    ColorTokenAdapter colorTokenAdapter;
    ArrayList<ColorToken> colorTokensList;

    Spinner spinner;
    EditText editValue;
    EditText editNumberToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_token);

        getIntentData();
        createColorTokenList();

        createSpinner();
        createEditValue();
        createEditNumberToken();
        createBackTokenButton();
        createValidTokenButton();
    }

    private void getIntentData() {
        Intent i = getIntent();
        game = (Game) i.getSerializableExtra("Game");
        position = i.getIntExtra("Position", 0);
        token = game.getToken(position);
    }

    private void createColorTokenList() {
        colorTokensList = new ArrayList<ColorToken>();

        for (ColorToken colorTested : ColorToken.listAllColor) {
            if (!game.isColorExist(colorTested)) {
                colorTokensList.add(colorTested);
            }
        }

    }

    private void createSpinner() {
        spinner = (Spinner) findViewById(R.id.spinnerColor);
        colorTokenAdapter = new ColorTokenAdapter(ChangeTokenActivity.this, colorTokensList);
        spinner.setAdapter(colorTokenAdapter);
        spinner.setOnItemClickListener((parent, view, position1, id) -> {
            token.setColor(colorTokensList.get(position1));
        });
    }

    private void createEditValue() {
        editValue = findViewById(R.id.editValueToken);
        editValue.setText(token.getValueToString());
    }

    private void createEditNumberToken() {
        editNumberToken = findViewById(R.id.editNumberTokens);
        editNumberToken.setText(token.getNumberToString());
    }

    private void passIntent() {
        Intent intent = new Intent(getApplicationContext(), SetupTokenActivity.class);
        intent.putExtra("Game", game);
        startActivity(intent);
    }

    private void createBackTokenButton() {
        Button b = findViewById(R.id.backTokenButton);
        b.setOnClickListener(v -> passIntent());
    }

    private void setAllValueOnToken() {
        ColorToken colorToken = colorTokensList.get(spinner.getBaseline());
        token.setColor(colorToken);
        int val = Integer.parseInt(editValue.getText().toString());
        token.setValue(val);
        int nbToken = Integer.parseInt(editNumberToken.getText().toString());
        token.setValue(nbToken);
    }

    private void createValidTokenButton() {
        Button b = findViewById(R.id.backTokenButton);
        b.setOnClickListener(v -> {
            setAllValueOnToken();
            game.setToken(position, token);
            passIntent();
        });
    }

}
