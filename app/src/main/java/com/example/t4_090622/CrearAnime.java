package com.example.t4_090622;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.t4_090622.entities.Anime;
import com.example.t4_090622.factories.RetofitFactory;
import com.example.t4_090622.services.AnimeService;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CrearAnime extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_anime);

        EditText etNombre = findViewById(R.id.etAnimeNombre);
        EditText etDesc = findViewById(R.id.etAnimeDescrip);
        EditText etUrl = findViewById(R.id.etAnimeUrl);


        Button btnRegistrar = findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = RetofitFactory.build();

                AnimeService service = retrofit.create(AnimeService.class);
                Anime anime = new Anime();
                anime.nombre = String.valueOf(etNombre.getText());
                anime.desripcion = String.valueOf(etDesc.getText());
                anime.link = String.valueOf(etUrl.getText());
                if(TextUtils.isEmpty(anime.nombre) || TextUtils.isEmpty(anime.desripcion) || TextUtils.isEmpty(anime.link)){
                    Toast toast = Toast.makeText(getApplicationContext(), "Campo o campos vacios", Toast.LENGTH_SHORT);
                    toast.show();
                    Log.i("Crear_T4_22","Campo o campos vacios");
                }else{
                    Call<Anime> call = service.create(anime);
                    call.enqueue(new Callback<Anime>() {
                        @Override
                        public void onResponse(Call<Anime> call, Response<Anime> response) {
                            if(response.isSuccessful()){
                                Log.i("registro", new Gson().toJson(response.body()));
                            }
                        }

                        @Override
                        public void onFailure(Call<Anime> call, Throwable t) {

                        }
                    });
                }

            }
        });
    }
}