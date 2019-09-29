package com.moviewatcher.mvw.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.moviewatcher.mvw.R;
import com.moviewatcher.mvw.activity.VideoPlayActivity;
import com.moviewatcher.mvw.model.VideoMovie;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.moviewatcher.mvw.helper.Constant.YOUTUBE_API_KEY;
import static com.moviewatcher.mvw.helper.Constant.YOUTUBE_KEY;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.TrailerAdapterVH> {

    Context context;
    List<VideoMovie> videoMovies;

    public TrailerAdapter(Context context, List<VideoMovie> videoMovies) {
        this.context = context;
        this.videoMovies = videoMovies;
    }

    @NonNull
    @Override
    public TrailerAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trailer, parent, false);
        return new TrailerAdapterVH(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerAdapterVH holder, int i) {
        holder.thumbnail.initialize(YOUTUBE_API_KEY, new YouTubeThumbnailView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader youTubeThumbnailLoader) {
                youTubeThumbnailLoader.setVideo(videoMovies.get(i).getKey());
                youTubeThumbnailLoader.setOnThumbnailLoadedListener(new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {
                    @Override
                    public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                        youTubeThumbnailLoader.release();
                    }

                    @Override
                    public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {

                    }
                });
            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });
        holder.thumbnail.setOnClickListener(v -> {
            Intent intent = new Intent(context, VideoPlayActivity.class);
            intent.putExtra(YOUTUBE_KEY,videoMovies.get(i).getKey());
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return videoMovies.size();
    }

    class TrailerAdapterVH extends RecyclerView.ViewHolder{
        @BindView(R.id.thumbnail_trailer)
        YouTubeThumbnailView thumbnail;

        public TrailerAdapterVH(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
