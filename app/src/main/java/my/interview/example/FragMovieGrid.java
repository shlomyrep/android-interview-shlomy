package my.interview.example;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

import my.interview.example.Adapter.MovieGridAdapter;
import my.interview.example.Process.AppHelper;
import my.interview.example.Process.MovieModel;

/**
 * Created by Nackson on 5/17/2016.
 */

public class FragMovieGrid extends Fragment implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    public interface FragmentConnection {
        void onDataChosen(MovieModel data);

        void onDataLongClick();
    }

    public static final String K_RESULT_LIST = "resultList";
    private GridView mGrid;
    private MovieGridAdapter mAdapter;
    private ArrayList<MovieModel> list;
    private View mView;
    static FragmentConnection mListener;

    public FragMovieGrid() {
    }


    public static FragMovieGrid getInstance(ArrayList<MovieModel> list) {
        FragMovieGrid instance = new FragMovieGrid();
        Bundle args = new Bundle();
        args.putSerializable(K_RESULT_LIST, list);
        instance.setArguments(args);
        return instance;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(my.interview.example.R.layout.frag_grid_movies, container, false);
        mGrid = (GridView) mView.findViewById(my.interview.example.R.id.grid_movies);
        list = (ArrayList<MovieModel>) getArguments().getSerializable(K_RESULT_LIST);
        refreshData(list);
        return mView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (FragmentConnection) activity;
        } catch (ClassCastException e) {
            AppHelper.Logger(e.toString());

        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(AppHelper.SAVE_INSTANCE_LIST, list);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            list = (ArrayList<MovieModel>) savedInstanceState.getSerializable(AppHelper.SAVE_INSTANCE_LIST);
            refreshData(list);
        }
    }

    private void refreshData(ArrayList<MovieModel> list) {
        if (mAdapter == null && mGrid != null) {
            mAdapter = new MovieGridAdapter(getActivity(), list);
            mGrid.setAdapter(mAdapter);
            mGrid.setOnItemClickListener(this);
            mGrid.setOnItemLongClickListener(this);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mListener != null) {
            mListener.onDataChosen(list.get(position));
        }
    }


    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//        mDb.markAsFavorite(list.get(position).getId(), true);
        if (mListener != null) {
            mListener.onDataLongClick();
        }
        return false;
    }

}
