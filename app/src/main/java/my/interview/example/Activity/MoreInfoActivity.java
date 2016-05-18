package my.interview.example.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.klinker.android.sliding.SlidingActivity;

import java.util.concurrent.ExecutionException;

import my.interview.example.Database.DbHandler;
import my.interview.example.FragMovie_Favorite;
import my.interview.example.Frag_Movie_Info;
import my.interview.example.Process.MovieModel;
import my.interview.example.R;

public class MoreInfoActivity extends SlidingActivity implements View.OnClickListener {

    private Bitmap theBitmap;
    private MovieModel data;

    @Override
    public void init(Bundle savedInstanceState) {
        setPrimaryColors(
                getResources().getColor(R.color.colorAccent),
                getResources().getColor(R.color.colorAccent)
        );
        enableFullscreen();
        setContent(R.layout.activity_more_info);
        setFab(getResources().getColor(R.color.colorAccent),R.drawable.ic_start,this);

        Intent intent = getIntent();
        data = (MovieModel) intent.getExtras().getSerializable(Frag_Movie_Info.K_PICKED_MOVIE);
        getSupportFragmentManager().beginTransaction().add(R.id.container_more_info, new Frag_Movie_Info().getInstance(data, false)).commit();
        setTitle(data.getMovieName());

        AsyncTask.THREAD_POOL_EXECUTOR.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    theBitmap = Glide.
                            with(MoreInfoActivity.this).
                            load(data.getMovieImage()).
                            asBitmap().
                            into(100, 200). // Width and height
                            get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setImage(theBitmap);
                    }
                });
            }
        });

    }


    @Override
    public void onClick(View v) {
        new DbHandler(MoreInfoActivity.this).markAsFavorite(data.getId(),true);
        startActivity(new Intent(MoreInfoActivity.this, FragMovie_Favorite.class));
    }


}
