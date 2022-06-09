package com.example.t4_090622;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.t4_090622.entities.Anime;
import com.example.t4_090622.factories.RetofitFactory;
import com.example.t4_090622.services.AnimeService;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AnimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime);

        String animeJson = getIntent().getStringExtra("Anime");
        Anime anime = new Gson().fromJson(animeJson, Anime.class);

        EditText etNombreAct = findViewById(R.id.etAnimeNombreAct);
        EditText etDescACt = findViewById(R.id.etAnimeDescripAct);
        EditText etUrlAct = findViewById(R.id.etAnimeUrlAct);

        Button btnAct = findViewById(R.id.btnActualizarDatos);
        btnAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = RetofitFactory.build();
                Anime anime2 = new Anime();
                AnimeService service = retrofit.create(AnimeService.class);

                anime2.nombre = String.valueOf(etNombreAct.getText());
                anime2.desripcion = String.valueOf(etDescACt.getText());
                anime2.link = String.valueOf(etUrlAct.getText());

                Call<Anime> call = service.update(anime.id,anime2);
                call.enqueue(new Callback<Anime>() {
                    @Override
                    public void onResponse(Call<Anime> call, Response<Anime> response) {
                        if(response.isSuccessful()){
                            Log.i("Actualizar", new Gson().toJson(response.body()));
                        }
                    }

                    @Override
                    public void onFailure(Call<Anime> call, Throwable t) {

                    }
                });
            }
        });
    }
}