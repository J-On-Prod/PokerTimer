package com.example.pokertimer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.example.pokertimer.model.Game;
import com.example.pokertimer.model.Token;
import com.example.pokertimer.model.TokenAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Game game = new Game();
    TokenAdapter tokenAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.listTokens);

        this.tokenAdapter = new TokenAdapter(game.getTokenList());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(tokenAdapter);
    }

    public void createToken(View view) {
        this.game.addToken();
        tokenAdapter.notifyItemChanged(this.game.getTokenList().size()-1);
    }
}