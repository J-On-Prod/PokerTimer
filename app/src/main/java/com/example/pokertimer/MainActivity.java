package com.example.pokertimer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pokertimer.model.Game;
import com.example.pokertimer.model.TokenAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Game game;
    TokenAdapter tokenAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (game == null) {
            game = new Game();
        }

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.listTokens);

        this.tokenAdapter = new TokenAdapter(game.getTokenList());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(tokenAdapter);

        Button setupOptionButton = (Button) findViewById(R.id.setupOptionButton);
        setupOptionButton.setOnClickListener(v -> {
            Bundle b = new Bundle();
            b.putParcelable("Game", game);
            Intent intent = new Intent(getApplicationContext(), SetupOptionActivity.class);
            intent.putExtra("BundleGame", b);
            startActivity(intent);
        });
    }

    public void createToken(View view) {
        game.addToken();
        tokenAdapter.notifyItemChanged(this.game.getTokenList().size()-1);
    }
}