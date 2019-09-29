package com.moviewatcher.mvw.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.chrisbanes.photoview.PhotoView;
import com.moviewatcher.mvw.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.moviewatcher.mvw.helper.Constant.MOVIE_PATH;

public class ImageViewerActivity extends AppCompatActivity {

    @BindView(R.id.image_imageViewer)
    PhotoView imageViewer;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer);
        url = getIntent().getStringExtra(MOVIE_PATH);
        ButterKnife.bind(this);
        Picasso.get().load(url).into(imageViewer);

    }
}
