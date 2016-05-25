package my.interview.example.Activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import my.interview.example.Database.DbHandler;
import my.interview.example.Process.GetMovieRequest;
import my.interview.example.Process.MovieModel;
import my.interview.example.R;

public class MainActivity extends AppCompatActivity implements FragMovieGrid.FragmentConnection, GetMovieRequest.onLoadedFinished {

    private GetMovieRequest mMovieRequest;
    private DbHandler mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDb = new DbHandler(this);
        if (savedInstanceState == null) {
            mMovieRequest = new GetMovieRequest(this);
            mMovieRequest.execute();
        }
    }

    public boolean isTablet() {
        return findViewById(R.id.container_more_info) != null;
    }

    @Override
    public void onDataChosen(MovieModel data) {
        if (isTablet() && getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

            getSupportFragmentManager().beginTransaction().add(R.id.container_more_info, new FragMovieInfo().getInstance(data, true)).commit();
        } else {
            Intent intent = new Intent(MainActivity.this, MoreInfoActivity.class);
            Bundle extras = new Bundle();
            extras.putSerializable(FragMovieInfo.K_PICKED_MOVIE, data);
            intent.putExtras(extras);
            startActivity(intent);
        }
    }

    @Override
    public void onDataLongClick() {
//        Intent intent = new Intent(MainActivity.this,FragMovieFavorite.class);
//        startActivity(intent);
    }

    @Override
    public void onFinishLoadMovies(ArrayList<MovieModel> list) {
        ArrayList<MovieModel> dbMovieList = mDb.getMovies();
        for (MovieModel movieModel : list) {
            if (!dbMovieList.contains(movieModel)) {
                movieModel.setId(mDb.addMovie(movieModel));
                dbMovieList.add(movieModel);
            }
        }
        getFragmentManager().beginTransaction().add(R.id.container_grid_view, FragMovieGrid.getInstance(dbMovieList)).commit();
    }

    @Override
    public void noConnection() {
        ArrayList<MovieModel> list = mDb.getMovies();
        if (!list.isEmpty()) {
            getFragmentManager().beginTransaction().add(R.id.container_grid_view, FragMovieGrid.getInstance(list)).commit();
        }
    }


}

