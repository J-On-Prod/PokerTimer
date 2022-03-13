package com.jon.pokertimer.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.jon.pokertimer.model.Game;
import com.jon.pokertimer.model.Token;

import java.util.ArrayList;
import java.util.List;

public class DatabasePokerGame extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "PokerData";

    private static final String TABLE_GAME = "Game";
    private static final String COL_GAME_ID= "ID";
    private static final String COL_GAME_NAME = "NAME";
    private static final String COL_GAME_NB_PLAYER = "NB_PLAYER";
    private static final String COL_GAME_DURATION_LEVEL = "DURATION_LEVEL";
    private static final String COL_GAME_DURATION_GAME = "DURATION_GAME";
    private static final String COL_GAME_PAUSE_LEVEL = "PAUSE_LEVEL";
    private static final String COL_GAME_PERCENTAGE_BANK_TOKEN = "PERCENTAGE_BANK_TOKEN";
    private static final String COL_GAME_START_SMALL_BLIND = "START_SMALL_BLIND";

    private static final String TABLE_TOKEN = "Token";
    private static final String COL_TOKEN_ID = "ID";
    private static final String COL_TOKEN_ID_GAME = "ID_GAME";
    private static final String COL_TOKEN_VALUE = "VALUE";
    private static final String COL_TOKEN_NUMBER = "NB";
    private static final String COL_TOKEN_COLOR= "COLOR";

    public DatabasePokerGame(@Nullable Context context, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, VERSION);

    }

    private void createTableGame(SQLiteDatabase db) {
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append("CREATE TABLE ");
        sBuilder.append(TABLE_GAME);
        sBuilder.append("(");
        sBuilder.append(COL_GAME_ID);
        sBuilder.append(" INTEGER PRIMARY KEY,");
        sBuilder.append(COL_GAME_NAME);
        sBuilder.append(" TEXT,");
        sBuilder.append(COL_GAME_NB_PLAYER);
        sBuilder.append(" INTEGER,");
        sBuilder.append(COL_GAME_DURATION_LEVEL);
        sBuilder.append(" INTEGER,");
        sBuilder.append(COL_GAME_DURATION_GAME);
        sBuilder.append(" INTEGER,");
        sBuilder.append(COL_GAME_PAUSE_LEVEL);
        sBuilder.append(" INTEGER,");
        sBuilder.append(COL_GAME_PERCENTAGE_BANK_TOKEN);
        sBuilder.append(" INTEGER,");
        sBuilder.append(COL_GAME_START_SMALL_BLIND);
        sBuilder.append(" INTEGER)");
        execQuery(db, sBuilder.toString());
    }

    private void createTableToken(SQLiteDatabase db) {
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append("CREATE TABLE ");
        sBuilder.append(TABLE_TOKEN);
        sBuilder.append("(");
        sBuilder.append(COL_TOKEN_ID);
        sBuilder.append("INTEGER PRIMARY KEY,");
        sBuilder.append(COL_TOKEN_ID_GAME);
        sBuilder.append("INTEGER,");
        sBuilder.append(COL_TOKEN_VALUE);
        sBuilder.append("INTEGER,");
        sBuilder.append(COL_TOKEN_NUMBER);
        sBuilder.append("INTEGER,");
        sBuilder.append(COL_TOKEN_COLOR);
        sBuilder.append("TEXT)");
        execQuery(db, sBuilder.toString());
    }

    private void execQuery(SQLiteDatabase db, String query) {
        db.execSQL(query);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTableGame(db);
        createTableToken(db);

        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addGame(Game game) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valGame = new ContentValues();
        valGame.put(COL_GAME_NAME, game.getName());
        valGame.put(COL_GAME_NB_PLAYER, game.getNbPlayer());
        valGame.put(COL_GAME_DURATION_LEVEL, game.getDurationLevel());
        valGame.put(COL_GAME_DURATION_GAME, game.getDurationGame());
        valGame.put(COL_GAME_PAUSE_LEVEL, game.getPauseEveryLevel());
        valGame.put(COL_GAME_PERCENTAGE_BANK_TOKEN, game.getPercentageBankTokenToString());
        valGame.put(COL_GAME_START_SMALL_BLIND, game.getStartSmallBlind());

        db.insert(TABLE_GAME, null, valGame);

        addTokens(game.getTokenList(), 1, db);
        db.close();
    }

    private void addTokens(List<Token> tokenList, int idGame, SQLiteDatabase db) {
        for (Token token : tokenList) {
            ContentValues valToken = new ContentValues();
            valToken.put(COL_TOKEN_ID_GAME, idGame);
            valToken.put(COL_GAME_NAME, token.getColorToString());
            valToken.put(COL_TOKEN_VALUE, token.getValue());

            db.insert(TABLE_TOKEN, null, valToken);
        }
    }

    public Game getGame(int id) {
        return new Game();
    }

    public List<Game> getAllGames() {
        ArrayList<Game> list = new ArrayList<>();
        list.add(new Game());
        return list;
    }
}
