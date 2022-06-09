package com.example.t4_090622.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "animes")
public class Anime {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "nombre")
    public String nombre;
    @ColumnInfo(name = "descripcion")
    public String desripcion;
    @ColumnInfo(name = "link")
    public String link;

    public Anime() {
    }

    public Anime(int id, String nombre, String desripcion, String link) {
        this.id = id;
        this.nombre = nombre;
        this.desripcion = desripcion;
        this.link = link;
    }
}
