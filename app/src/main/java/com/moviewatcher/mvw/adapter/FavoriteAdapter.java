package com.moviewatcher.mvw.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
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

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteAdapterVH> {

    List<Movie> movies;
    Context context;

    public FavoriteAdapter(List<Movie> movies, Context context) {
        this.movies = movies;
        this.context = context;
    }

    @NonNull
    @Override
    public FavoriteAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite, parent, false);
        return new FavoriteAdapterVH(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapterVH holder, int i) {
        String url = IMAGE_SMALL_PATH + movies.get(i).getPoster_path();
        holder.titleFavorite.setText(movies.get(i).getTittle());
        holder.overviewFavorite.setText(movies.get(i).getOverview());
        Picasso.get().load(url).into(holder.imageFavorite);
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailMovieActivity.class);
                intent.putExtra(MOVIE_ID,movies.get(i).getId());
                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class FavoriteAdapterVH extends RecyclerView.ViewHolder{

        @BindView(R.id.image_favorite)
        ImageView imageFavorite;
        @BindView(R.id.title_favorite)
        TextView titleFavorite;
        @BindView(R.id.overview_favorite)
        TextView overviewFavorite;
        @BindView(R.id.constrainLayout_favorite)
        ConstraintLayout constraintLayout;

        public FavoriteAdapterVH(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
