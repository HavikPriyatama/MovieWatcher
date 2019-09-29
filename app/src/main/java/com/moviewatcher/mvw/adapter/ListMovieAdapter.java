package com.moviewatcher.mvw.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.moviewatcher.mvw.R;
import com.moviewatcher.mvw.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.moviewatcher.mvw.helper.Constant.IMAGE_SMALL_PATH;

public class ListMovieAdapter extends BaseAdapter {

    private List<Movie> movies;
    private Picasso picasso;
    private Context context;

    public ListMovieAdapter(List<Movie> movies, Picasso picasso, Context context) {
        this.movies = movies;
        this.picasso = picasso;
        this.context = context;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return movies.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       if (convertView == null){
           convertView = LayoutInflater.from(context).inflate(R.layout.item_list_movie, parent, false);
       }
        ImageView movieImg = convertView.findViewById(R.id.movieImg);
        TextView movieTitle = convertView.findViewById(R.id.movieTitle);
        TextView overview = convertView.findViewById(R.id.overview);

        String date =  movies.get(position).getRelease_date().isEmpty()? "" : " (" +movies.get(position).getRelease_date().substring(0,4) +")";
        String title = movies.get(position).getTittle() + date;
        String url = IMAGE_SMALL_PATH + movies.get(position).getPoster_path();

        movieTitle.setText(title);
        overview.setText(movies.get(position).getOverview());
        picasso.load(url).into(movieImg);
        return convertView;
    }

    public void addMovies(List<Movie> newMovies){
        movies.addAll(newMovies);
        notifyDataSetChanged();
    }

}
