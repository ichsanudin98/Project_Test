package id.co.ichsanudinstore.student.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import id.co.ichsanudinstore.student.config.Constant;

public class SharedPrefs {
    private static final transient String TAG = SharedPrefs.class.getSimpleName();

    private static SharedPreferences mPreferences;

    public static SharedPreferences getmPreferences(Context context) {
        if (mPreferences == null)
            mPreferences = context.getSharedPreferences("BelajarRealm", Context.MODE_PRIVATE);

        return mPreferences;
    }

    public static void saveBaseURL(String url, Activity activity) {
        if (mPreferences == null)
            mPreferences = getmPreferences(activity.getApplicationContext());

        SharedPreferences.Editor mEditor = mPreferences.edit();
        mEditor.putString(Constant.URL.BASE, url);
        mEditor.apply();
    }
}
