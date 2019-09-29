package com.moviewatcher.mvw.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moviewatcher.mvw.R;
import com.moviewatcher.mvw.activity.DetailMovieActivity;
import com.moviewatcher.mvw.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.moviewatcher.mvw.helper.Constant.IMAGE_SMALL_PATH;
import static com.moviewatcher.mvw.helper.Constant.MOVIE_ID;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.PopularAdapterVH> {

    List<Movie> movies;
    Context context;
    Picasso picasso;

    public PopularAdapter(List<Movie> movies, Context context, Picasso picasso) {
        this.movies = movies;
        this.context = context;
        this.picasso = picasso;
    }

    @NonNull
    @Override
    public PopularAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.abc_popular, parent, false);
        return new PopularAdapterVH(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularAdapterVH holder, int i) {
        String url = IMAGE_SMALL_PATH + movies.get(i).getPoster_path();
        picasso.load(url).into(holder.popularMovie);
        holder.popularMovie.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailMovieActivity.class);
            intent.putExtra(MOVIE_ID, movies.get(i).getId());
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void addPopular(List<Movie> newMovies){
        movies.addAll(newMovies);
        notifyDataSetChanged();
    }

    class PopularAdapterVH extends RecyclerView.ViewHolder {

        @BindView(R.id.image_popular)
        ImageView popularMovie;

        public PopularAdapterVH(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
