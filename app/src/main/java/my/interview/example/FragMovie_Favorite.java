package my.interview.example;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import my.interview.example.Database.DbHandler;
import my.interview.example.Process.MovieModel;

/**
 * Created by Nackson on 5/17/2016.
 */
public class FragMovie_Favorite extends AppCompatActivity  {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(getResources().getString(my.interview.example.R.string.favoriteTitle));
        if(savedInstanceState==null) {
            ArrayList<MovieModel> list = new DbHandler(this).getFavoritesMovies();
            getFragmentManager().beginTransaction().add(android.R.id.content, FragMovie_Grid.getInstance(list)).commit();
        }
    }
}
