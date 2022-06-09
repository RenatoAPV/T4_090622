package com.example.t4_090622;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.t4_090622.adapter.AnimeAdapter;
import com.example.t4_090622.dao.AnimeDao;
import com.example.t4_090622.database.AppDatabase;
import com.example.t4_090622.entities.Anime;
import com.example.t4_090622.factories.RetofitFactory;
import com.example.t4_090622.services.AnimeService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    List<Anime> animes = new ArrayList<>();

    AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = AppDatabase.getDatabase(getApplicationContext());
        
        Retrofit retrofit = RetofitFactory.build();

        AnimeService service = retrofit.create(AnimeService.class);
        Call<List<Anime>> call = service.GetAnimes();

        call.enqueue(new Callback<List<Anime>>() {
            @Override
            public void onResponse(Call<List<Anime>> call, Response<List<Anime>> response) {
                if(response.isSuccessful()){
                    animes = response.body();

                    //saveIndataBase(animes);

                    AnimeAdapter adapter = new AnimeAdapter(animes);

                    RecyclerView rv = findViewById(R.id.rvAnimes);
                    rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    rv.setHasFixedSize(true);
                    rv.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Anime>> call, Throwable t) {
                Log.e("VJ_EX","Error de conectividad");
            }
        });
    }

    private void saveIndataBase(List<Anime> animes){
        AnimeDao dao = db.animeDao();
        for (Anime anime : animes){
            dao.create(anime);
        }
    }
}