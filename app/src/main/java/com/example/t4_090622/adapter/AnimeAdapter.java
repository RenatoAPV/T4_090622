package com.example.t4_090622.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.t4_090622.AnimeActivity;
import com.example.t4_090622.R;
import com.example.t4_090622.entities.Anime;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AnimeAdapter extends RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder> {
    List<Anime> animes;
    public AnimeAdapter(List<Anime> animes){
        this.animes = animes;
    }


    @NonNull
    @Override
    public AnimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        return new AnimeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimeViewHolder vh, int position) {
        View itemView = vh.itemView;

        Anime anime = animes.get(position);
        TextView tvNombreA = vh.itemView.findViewById(R.id.tvNombreAnime);
        TextView tvDescripA = vh.itemView.findViewById(R.id.tvDescripcion);
        tvNombreA.setText(anime.nombre);
        tvDescripA.setText(anime.desripcion);
        ImageView imageavatar = vh.itemView.findViewById(R.id.imageView);

        Picasso.get().load(anime.link).into(imageavatar);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(itemView.getContext(), AnimeActivity.class);

                String animeJson = new Gson().toJson(anime);
                intent.putExtra("Anime", animeJson);

                itemView.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return animes.size();
    }

    class AnimeViewHolder extends RecyclerView.ViewHolder{

        public AnimeViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}