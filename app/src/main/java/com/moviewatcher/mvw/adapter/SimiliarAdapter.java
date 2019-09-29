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

public class SimiliarAdapter extends RecyclerView.Adapter<SimiliarAdapter.SimiliarAdapterVH> {

    private Context context;
    private List<Movie> movies;

    public SimiliarAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public SimiliarAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_similiar, parent,false);
        return new SimiliarAdapterVH(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull SimiliarAdapterVH holder, int i) {
        String pathPoster = IMAGE_SMALL_PATH + movies.get(i).getPoster_path();
        Picasso.get().load(pathPoster).into(holder.imageSimiliar);
        holder.imageSimiliar.setOnClickListener(v -> {
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

    class SimiliarAdapterVH extends RecyclerView.ViewHolder {

        @BindView(R.id.image_similiar)
        ImageView imageSimiliar;

        public SimiliarAdapterVH(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

