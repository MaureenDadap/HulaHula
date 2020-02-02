package com.me.beep.hulahula.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface PlayerDao {

    @Query("SELECT * FROM player")
    List<Player> getAll();

    @Query("SELECT player_name FROM player")
    List<String> getNames();

    @Query("SELECT player_word FROM player")
    List<String> getWords();

    @Insert
    void insertPlayer (Player player);

    @Update
    void updatePlayer (Player player);

    @Delete
    void deletePlayer (Player player);

    @Query("Select count(player_name) from player")
    int countPlayer ();
}
