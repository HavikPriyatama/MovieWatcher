package com.moviewatcher.mvw.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moviewatcher.mvw.R;
import com.moviewatcher.mvw.model.Movie;
import com.moviewatcher.mvw.utils.IntentManager;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.moviewatcher.mvw.helper.Constant.IMAGE_SMALL_PATH;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ListMovieAdapterVH> {

    Context context;
    List<Movie> movies;
    Picasso picasso;
    IntentManager intentManager;

    public SearchAdapter(Context context, List<Movie> movies, Picasso picasso, IntentManager intentManager) {
        this.context = context;
        this.movies = movies;
        this.picasso = picasso;
        this.intentManager = intentManager;
    }

    @NonNull
    @Override
    public ListMovieAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_search, parent, false);
        return new ListMovieAdapterVH(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListMovieAdapterVH holder, int i) {
        String url = IMAGE_SMALL_PATH + movies.get(i).getPoster_path();
        holder.movieTitle.setText(movies.get(i).getTittle());
        picasso.load(url).into(holder.movieImage);
        holder.itemList.setOnClickListener(v -> {
            intentManager.getDetailMovie(movies.get(i).getId());
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ListMovieAdapterVH extends RecyclerView.ViewHolder{

        @BindView(R.id.thumbnail_movie)
        ImageView movieImage;
        @BindView(R.id.name_movie)
        TextView movieTitle;
        @BindView(R.id.movie_item_list)
        RelativeLayout itemList;

        public ListMovieAdapterVH(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
