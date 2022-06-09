package com.example.t4_090622.services;

import com.example.t4_090622.entities.Anime;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AnimeService {
    @GET("Anime")
    Call<List<Anime>> GetAnimes();

    @POST("Anime")
    Call<Anime> create(@Body Anime anime);

    @PUT("Anime/{id}")
    Call<Anime> update(@Path("id") int id, @Body Anime anime);
}
