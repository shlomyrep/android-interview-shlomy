package my.interview.example.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import my.interview.example.Process.MovieModel;

/**
 * Created by Nackson on 5/17/2016.
 */
public abstract class AbsBaseAdapter extends BaseAdapter {

    protected Activity mContext;
    protected ArrayList<MovieModel> mMovies;
    protected LayoutInflater mInflater;

    public AbsBaseAdapter(Activity mContext, ArrayList<MovieModel> mMovies) {
        this.mContext = mContext;
        this.mMovies = mMovies;
        if(this.mMovies==null) {
            this.mMovies = new ArrayList<>();
        }
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mMovies.size();
    }
    @Override
    public Object getItem(int position) {
        return position;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolderItem{
        TextView mTitle;
        ImageView mImage;
        ImageView mFavorite;
    }

}
