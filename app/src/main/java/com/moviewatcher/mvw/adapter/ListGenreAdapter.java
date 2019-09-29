package com.moviewatcher.mvw.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moviewatcher.mvw.R;
import com.moviewatcher.mvw.activity.DetailMovieActivity;
import com.moviewatcher.mvw.model.Genre;
import com.moviewatcher.mvw.utils.IntentManager;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.moviewatcher.mvw.helper.Constant.GENRE_VIEW;
import static com.moviewatcher.mvw.helper.Constant.MOVIE_ID;

public class ListGenreAdapter extends RecyclerView.Adapter<ListGenreAdapter.ListGenreAdapterVH> {

    private List<Genre> genres;
    private Picasso picasso;
    private IntentManager intentManager;

    public ListGenreAdapter(List<Genre> genres, Picasso picasso, IntentManager intentManager) {
        this.genres = genres;
        this.picasso = picasso;
        this.intentManager = intentManager;
    }

    @NonNull
    @Override
    public ListGenreAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_genre, parent, false);
        return new ListGenreAdapterVH(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListGenreAdapterVH holder, int position) {
        holder.textGenre.setText(genres.get(position).getName());
        picasso.load(genres.get(position).getBackdropImage()).into(holder.backImage);
        holder.itemList.setOnClickListener(__ -> {
            intentManager.getListMovie(genres.get(position).getId(), "" ,genres.get(position).getName(), GENRE_VIEW);
        });
    }

    @Override
    public int getItemCount() {
        return genres.size();
    }


    public class ListGenreAdapterVH extends RecyclerView.ViewHolder {

        @BindView(R.id.text_genre)
        TextView textGenre;
        @BindView(R.id.imageBackList)
        ImageView backImage;
        @BindView(R.id.item_list_genre)
        FrameLayout itemList;

        public ListGenreAdapterVH(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
