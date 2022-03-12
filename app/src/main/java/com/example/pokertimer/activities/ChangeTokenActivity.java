package com.example.pokertimer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pokertimer.R;
import com.example.pokertimer.model.ColorToken;
import com.example.pokertimer.model.ColorTokenAdapter;
import com.example.pokertimer.model.Game;
import com.example.pokertimer.model.Token;

import java.util.ArrayList;

public class ChangeTokenActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

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
        createRemoveTokenButton();
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
        colorTokensList.add(token.getColor());
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
        spinner.setOnItemSelectedListener(this);
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

    private void createRemoveTokenButton() {
        Button b = findViewById(R.id.removeToken);
        b.setOnClickListener(v -> {
            game.deleteToken(position);
            passIntent();
        });
    }

    private void updateValueOnToken() {
        int val = Integer.parseInt(editValue.getText().toString());
        token.setValue(val);
        int nbToken = Integer.parseInt(editNumberToken.getText().toString());
        token.setValue(nbToken);
    }

    private void createValidTokenButton() {
        Button b = findViewById(R.id.validTokenButton);
        b.setOnClickListener(v -> {
            updateValueOnToken();
            game.setToken(position, token);
            passIntent();
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        ColorToken colorSelect = colorTokensList.get(i);
        token.setColor(colorSelect);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // Anything here
    }
}
