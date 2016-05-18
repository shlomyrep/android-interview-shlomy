package my.interview.example.Process;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by Nackson on 5/17/2016.
 */
public class AppHelper {


    public static final String SAVE_INSTANCE_LIST = "currentList";
    public static final String TAG = "MovieDbLoggerExample";


    public static boolean isNetworkAvailable(Context mContext) {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static void displayImageGlide(Context mContext, String url, ImageView imageView) {
        Glide.with(mContext).load(url)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .dontTransform()
                .into(imageView);
    }

    public static void toggleOrientation(Context context, boolean enable) {
        if (!enable) {
            ((Activity) context).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            ((Activity) context).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        }
    }

    public static void Logger(String exception) {
        Log.i(TAG, exception);
    }
}
