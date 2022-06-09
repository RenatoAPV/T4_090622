package com.example.t4_090622.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.t4_090622.entities.Anime;

import java.util.List;

@Dao
public interface AnimeDao {
    @Query("SELECT * FROM animes")
    List<Anime> getAll();

    @Query("SELECT * FROM animes WHERE id = :id")
    Anime find(int id);

    @Insert
    void create(Anime anime);
}
