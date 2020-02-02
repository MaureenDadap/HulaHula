package com.me.beep.hulahula.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;


@Database(entities = {Player.class}, version = 3)
//@TypeConverters({Converters.class})
public abstract class PlayerDatabase extends RoomDatabase {
    private static final String DB_NAME = "player_db";
    private static PlayerDatabase instance;

    public static synchronized PlayerDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), PlayerDatabase.class, DB_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract PlayerDao playerDao();
}
