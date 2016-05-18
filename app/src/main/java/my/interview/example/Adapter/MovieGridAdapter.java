package my.interview.example.Adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import java.util.ArrayList;

import my.interview.example.Process.AppHelper;
import my.interview.example.Process.MovieModel;
import my.interview.example.R;

/**
 * Created by Nackson on 5/17/2016.
 */
public class MovieGridAdapter extends AbsBaseAdapter {

    public MovieGridAdapter(Activity mContext, ArrayList<MovieModel> mMovies) {
        super(mContext, mMovies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolderItem viewHolder;
        View view = convertView;
        if (view == null) {
            view = mInflater.inflate(R.layout.cell_movie_container, parent, false);
            viewHolder = new ViewHolderItem();
            viewHolder.mImage = (ImageView) view.findViewById(R.id.cell_image_avatar);
            viewHolder.mFavorite = (ImageView) view.findViewById(R.id.star_idicator);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolderItem) convertView.getTag();
        }
        return setData(view, viewHolder, position);
    }

    private View setData(View view, ViewHolderItem viewHolder, int position) {
        MovieModel data = mMovies.get(position);
        if (data.isFavorite()) {
            viewHolder.mFavorite.setVisibility(View.VISIBLE);
        } else {
            viewHolder.mFavorite.setVisibility(View.GONE);
        }
        if (data.getMovieImage() != null) {
            AppHelper.displayImageGlide(mContext, data.getMovieImage(), viewHolder.mImage);
        }
        return view;
    }

}
