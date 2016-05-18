package my.interview.example.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import my.interview.example.Process.MovieModel;

/**
 * Created by Nackson on 5/17/2016.
 */
public class DbHandler {

    private SQLiteDatabase mDb;
    private DbHelper mHelper;

    public DbHandler(Context mContext) {
        mHelper = new DbHelper(mContext);
    }

    private DbHandler open() {
        mDb = mHelper.getWritableDatabase();
        return this;
    }
    private void close(){
        mHelper.close();
    }

    public void addMovie(MovieModel movie){
        try {
            open();
            addSingleMovie(movie);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close();
        }
    }

    private void addSingleMovie(MovieModel movie){
        ContentValues cv = new ContentValues();
        cv.put(DbConstant.MOVIE_ID , movie.getMovieId());
        cv.put(DbConstant.MOVIE_NAME , movie.getMovieName());
        cv.put(DbConstant.MOVIE_URL , movie.getMovieUrl());
        cv.put(DbConstant.MOVIE_RELEASE_DATE, movie.getReleaseDate());
        cv.put(DbConstant.MOVIE_SYNOPSIS, movie.getMovieSynopsis());
        cv.put(DbConstant.MOVIE_TRAILERS , movie.getMovieTrailers());
        cv.put(DbConstant.MOVIE_IMAGE, movie.getMovieImage());
        cv.put(DbConstant.MOVIE_RATE, movie.getRate());
        int isFavorite = movie.isFavorite()?1:0;
        cv.put(DbConstant.FAVORITE,isFavorite);
        mDb.insert(DbConstant.MOVIE_TABLE,null,cv);
    }

    public void markAsFavorite(int id , boolean favorite){
        try {
            open();
            int isFavorite = favorite?1:0;
            ContentValues cv = new ContentValues();
            cv.put(DbConstant.FAVORITE, isFavorite);
            mDb.update(DbConstant.MOVIE_TABLE, cv, DbConstant.ID+"="+id, null);
        }catch (Exception e){}
        finally {
            close();
        }
    }

    public ArrayList<MovieModel> getFavoritesMovies(){
        try {
            open();
            Cursor c =  mDb.rawQuery("SELECT * FROM " + DbConstant.MOVIE_TABLE + " WHERE " + DbConstant.FAVORITE + " LIKE '" + 1 + "%' ", null);
            return getData(c);
        }catch (Exception e){}
        finally {
            close();
        }
        return null;
    }

    public void addMovies(ArrayList<MovieModel> list){
        if(getMovies().contains(list)){
            return;
        }
        removeAll();
        try {
            open();
            for (MovieModel m : list) {
                addSingleMovie(m);
            }
        }catch (Exception e){}
        finally {
            close();
        }
    }

    public void removeAll(){
        try {
            open();
            mDb.delete(DbConstant.MOVIE_TABLE,null,null);
        }catch (Exception e){
        }finally {
            close();
        }
    }

    public ArrayList<MovieModel> getMovies(){
        try {
            open();
            Cursor cursor = mDb.query(DbConstant.MOVIE_TABLE, null , null ,null,null,null,null);
            return getData(cursor);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            close();
        }
    }

    private ArrayList<MovieModel> getData(Cursor c){
        ArrayList<MovieModel> list = new ArrayList<>();
        while (c.moveToNext()){
            MovieModel model = new MovieModel(
                    c.getInt(c.getColumnIndex(DbConstant.ID)),
                    c.getLong(c.getColumnIndex(DbConstant.MOVIE_ID)),
                    c.getString(c.getColumnIndex(DbConstant.MOVIE_NAME)),
                    c.getString(c.getColumnIndex(DbConstant.MOVIE_SYNOPSIS)),
                    c.getString(c.getColumnIndex(DbConstant.MOVIE_RELEASE_DATE)),
                    c.getDouble(c.getColumnIndex(DbConstant.MOVIE_RATE)),
                    c.getString(c.getColumnIndex(DbConstant.MOVIE_IMAGE)),
                    c.getString(c.getColumnIndex(DbConstant.MOVIE_URL)),
                    c.getString(c.getColumnIndex(DbConstant.MOVIE_TRAILERS)),
                    c.getInt   (c.getColumnIndex(DbConstant.FAVORITE))==1);
            list.add(model);
        }
        return list;
    }

}
