package com.moviewatcher.mvw.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.moviewatcher.mvw.R;
import com.moviewatcher.mvw.activity.DetailMovieActivity;
import com.moviewatcher.mvw.model.Movie;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.moviewatcher.mvw.helper.Constant.IMAGE_MEDIUM_PATH;
import static com.moviewatcher.mvw.helper.Constant.MOVIE_ID;


public class TrendingAdapter extends SliderViewAdapter<TrendingAdapter.SliderAdapterVH> {

    Context context;
    List<Movie> movies;
    Picasso picasso;

    public TrendingAdapter(Context context, List<Movie> movies, Picasso picasso) {
        this.context = context;
        this.movies = movies;
        this.picasso = picasso;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trending, parent,false);
        return new SliderAdapterVH(rootView);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, int i) {
        String url = IMAGE_MEDIUM_PATH + movies.get(i).getBackdrop_path();

        picasso.load(url).into(viewHolder.imageViewBackground);
        viewHolder.textViewDescription.setText("Trending : " + movies.get(i).getTittle());
        viewHolder.imageViewBackground.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailMovieActivity.class);
            intent.putExtra(MOVIE_ID, movies.get(i).getId());
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);

        });
    }

    @Override
    public int getCount() {
        return 6;
    }


    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {
        View itemView;
        ImageView imageViewBackground;
        TextView textViewDescription;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.tranding_backimage);
            textViewDescription = itemView.findViewById(R.id.trending_title);
            this.itemView = itemView;
        }
    }
}
