package my.interview.example.Process;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;

import my.interview.example.R;

/**
 * Created by Nackson on 5/17/2016.
 */
public class GetMovieRequest extends AsyncTask<Void, Void, ArrayList<MovieModel>> {

    public interface onLoadedFinished {
        void onFinishLoadMovies(ArrayList<MovieModel> list);

        void noConnection();
    }

    private Context mContext;
    private onLoadedFinished mListener;
    private ProgressDialog mProgress;
    private ArrayList<MovieModel> mList;
    private JSONObject responseObject;
    private Downloader mDownloader;

    public GetMovieRequest(Context mContext) {
        this.mContext = mContext;
        this.mListener = (onLoadedFinished) mContext;
        mProgress = new ProgressDialog(mContext);
        mList = new ArrayList<>();
        mDownloader = new Downloader();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (!AppHelper.isNetworkAvailable(mContext) && mListener != null) {
            mListener.noConnection();
            cancel(true);
        } else {
            mProgress.setTitle(mContext.getResources().getString(R.string.loading));
            mProgress.setCancelable(false);
            mProgress.show();
        }
    }

    @Override
    protected ArrayList<MovieModel> doInBackground(Void... params) {
        if (isCancelled()) {
            return null;
        }
        String url = MovieDbApi.URL_TOP_RATED;
        String json = mDownloader.getJsonDataFromUrl(url);
        if (json == null) {
            return null;
        }
        try {
            responseObject = new JSONObject(json);
        } catch (JSONException e) {
            AppHelper.Logger(e.toString());

        }

        try {
            return findMovies(responseObject);
        } catch (JSONException e) {
            AppHelper.Logger(e.toString());
            return null;
        } //
    }


    @Override
    protected void onPostExecute(ArrayList<MovieModel> result) {
        super.onPostExecute(result);
        if (result != null && !result.isEmpty()) {
            if (mListener != null) {
                mListener.onFinishLoadMovies(result);
            }
        }
        if (mProgress != null) {
            mProgress.dismiss();
        }
        AppHelper.toggleOrientation(mContext, true);

    }


    public ArrayList<MovieModel> findMovies(JSONObject responseObject) throws JSONException {
        JSONArray movies = responseObject.getJSONArray(MovieDbApi.JSON_KEYS.RESULTS);
        if (movies.length() == 0) {
            return null;
        }

        for (int i = 0; i < movies.length(); i++) {
            JSONObject movieJson = movies.getJSONObject(i);
            long id = movieJson.getLong(MovieDbApi.JSON_KEYS.ID);
            String title = movieJson.getString(MovieDbApi.JSON_KEYS.TITLE);
            String synopsis = movieJson.getString(MovieDbApi.JSON_KEYS.OVERVIEW);
            String releaseDate = movieJson.getString(MovieDbApi.JSON_KEYS.RELEASE_DATE);
            double rate = movieJson.getDouble(MovieDbApi.JSON_KEYS.VOTE_AVERAGE);
            String imageUrl = movieJson.getString(MovieDbApi.JSON_KEYS.POSTER_PATH);

//            String videoUrl = String.format(MovieDbApi.VIDEO_URL, String.valueOf(id));
//            String json = mDownloader.getJsonDataFromUrl(videoUrl);
//
//            responseObject = new JSONObject(json);
//            JSONArray trailers = responseObject.getJSONArray(MovieDbApi.JSON_KEYS.RESULTS);
//            JSONObject  trailer = trailers.getJSONObject(0);
//            String trailerKey = trailer.getString(MovieDbApi.JSON_KEYS.TRAILER_KEY);

            mList.add(new MovieModel(id, title, synopsis, releaseDate, rate, MovieDbApi.IMAGE_URL + imageUrl, "", "", false));
        }

        return mList;
    }
}
