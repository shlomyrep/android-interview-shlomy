package my.interview.example.Activity;


import android.annotation.SuppressLint;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import my.interview.example.Process.AppHelper;
import my.interview.example.Process.MovieModel;

/**
 * Created by Nackson on 5/17/2016.
 */
@SuppressLint("ValidFragment")
public class FragMovieInfo extends Fragment {
    public static final String K_PICKED_MOVIE = "pickedMovie";
    public static final String K_WITH_IMAGE = "includeImage";

    private View mView;
    private TextView mTxTitle;
    private TextView mTxSynopsis;
    private TextView mRate;
    private TextView mReleaseDate;
    private ImageView imageView;
    private boolean withImage;

    public static FragMovieInfo getInstance(MovieModel m, boolean withImage) {
        FragMovieInfo instance = new FragMovieInfo();
        Bundle args = new Bundle();
        args.putSerializable(K_PICKED_MOVIE, m);
        args.putBoolean(K_WITH_IMAGE, withImage);
        instance.setArguments(args);
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(my.interview.example.R.layout.frag_info_movie, container, false);
        MovieModel chosenMovie = (MovieModel) getArguments().getSerializable(K_PICKED_MOVIE);

        withImage = getArguments().getBoolean(K_WITH_IMAGE);
        mTxTitle = (TextView) mView.findViewById(my.interview.example.R.id.more_info_title);
        mTxSynopsis = (TextView) mView.findViewById(my.interview.example.R.id.more_info_synopsis);
        imageView = (ImageView) mView.findViewById(my.interview.example.R.id.more_info_image);
        mRate = (TextView) mView.findViewById(my.interview.example.R.id.more_info_rate);
        mReleaseDate = (TextView) mView.findViewById(my.interview.example.R.id.more_info_releaseDate);

        if (!withImage) {
            imageView.setVisibility(View.GONE);
        }

        initData(chosenMovie);
        return mView;
    }

    private void initData(MovieModel model) {
        try {
            String title = model.getMovieName();
            String imageUrl = model.getMovieImage();
            String synopsis = model.getMovieSynopsis();
            String releaseDate = model.getReleaseDate();
//            String trailerId = model.getMovieTrailers();
            double rate = model.getRate();
            mTxTitle.setText(title);
            mTxSynopsis.setText(synopsis);
            String finalRate = "<b>" + rate + "</b>/10";
            mRate.setText(Html.fromHtml(finalRate));
            mReleaseDate.setText(releaseDate);
//            startActivity(new Intent(getActivity() , TrailerActivity.class).putExtra("MOVIE_ID" , trailerId));
            AppHelper.displayImageGlide(getActivity(), imageUrl, imageView);
        } catch (Exception e) {

            AppHelper.Logger(e.toString());

        }
    }

}

