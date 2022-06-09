package com.example.t4_090622.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.t4_090622.dao.AnimeDao;
import com.example.t4_090622.entities.Anime;

@Database(entities = {Anime.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AnimeDao animeDao();

    public static AppDatabase getDatabase(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "com.example.androidvj20221.database.contacts.db")
                .allowMainThreadQueries()
                .build();
    }
}
