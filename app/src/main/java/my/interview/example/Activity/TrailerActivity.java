package my.interview.example.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class TrailerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(my.interview.example.R.layout.activity_trailer);
        String movieId = null;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            movieId = extras.getString("MOVIE_ID");
        }
        if (movieId == null) {
            finish();
        }

        YouTubeFragment fragment = (YouTubeFragment) getSupportFragmentManager().findFragmentById(my.interview.example.R.id.fragment_youtube);
        fragment.setVideoId("jXC42vVS2rs");


    }
}
