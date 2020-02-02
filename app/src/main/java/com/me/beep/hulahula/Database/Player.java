package com.me.beep.hulahula.Database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Player {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "player_name")
    private String playerName;

    @ColumnInfo(name = "player_score")
    private int playerScore;

    @ColumnInfo(name = "player_word")
    private String playerWord;

    public Player(String playerName, int playerScore, String playerWord) {
        this.playerName = playerName;
        this.playerScore = playerScore;
        this.playerWord = playerWord;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }

    public String getPlayerWord() {
        return playerWord;
    }

    public void setPlayerWord(String playerWord) {
        this.playerWord = playerWord;
    }

}
