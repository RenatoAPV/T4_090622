package com.example.t4_090622.factories;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetofitFactory {
    public static Retrofit build(){
        return new Retrofit.Builder()
                .baseUrl("https://62a1df9dcc8c0118ef577f0f.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
