package app.citta.retail365cloud.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class AppPreference {

    private static final String PREFERENCE_FILE_NAME = "Retail365cloudPreferences";

    private static AppPreference appPreference;

    private SharedPreferences mSharedPreferences;

    public static AppPreference getInstance() {
        appPreference = new AppPreference();
        return appPreference;
    }

    public String clearPreferences(Activity activity) {
        mSharedPreferences = activity.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.clear();
        editor.apply();
        return null;
    }

    public void setStringPreference(Activity activity, String key, String value) {
        mSharedPreferences = activity.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getStringPreference(Activity activity, String key) {
        mSharedPreferences = activity.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(key, null);
    }

    public void setBooleanPreference(Activity activity, String key, boolean value) {
        mSharedPreferences = activity.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(String.valueOf(key), value);
        editor.apply();
    }

    public boolean getBooleanPreference(Activity activity, String key) {
        mSharedPreferences = activity.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        return mSharedPreferences.getBoolean(String.valueOf(key), false);
    }

    public void setIntegerPreference(Activity activity, String key, int value) {
        mSharedPreferences = activity.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public int getIntegerPreference(Activity activity, String key) {
        mSharedPreferences = activity.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        return mSharedPreferences.getInt(key, 0);
    }

    public void setStringPreference(Context context, String key, String value) {
        mSharedPreferences = context.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getStringPreference(Context context, String key) {
        mSharedPreferences = context.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        return mSharedPreferences.getString(key, null);
    }

    public void setBooleanPreference(Context context, String key, boolean value) {
        mSharedPreferences = context.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(String.valueOf(key), value);
        editor.apply();
    }

    public boolean getBooleanPreference(Context context, String key) {
        mSharedPreferences = context.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        return mSharedPreferences.getBoolean(String.valueOf(key), false);
    }
}
