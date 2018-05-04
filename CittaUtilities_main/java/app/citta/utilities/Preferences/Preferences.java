package app.citta.utilities.Preferences;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class Preferences {
    private static Preferences preferences;

    public static Preferences getInstance() {
        if (preferences == null)
            preferences = new Preferences();
        return preferences;
    }

    public static final String PREFERENCE_FILE_NAME = "Project's_Preference_File_Name";

    public void clearPreferences(Activity activity){
        SharedPreferences sharedPreferences = activity.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

    public void addSharedPreference(Activity activity, String key, String value) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String getSharedPreferenceString(Activity activity, String key) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, null);
    }


    public void addSharedPreferenceboolean(Activity activity, boolean key, boolean value) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(String.valueOf(key), value);
        editor.commit();
    }

    public boolean getSharedPreferenceStringboolean(Activity activity, boolean key) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(String.valueOf(key), false);
    }


    public void addSharedPreferenceInt(Activity activity, String key, int value) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public int getSharedPreferenceInt(Activity activity, String key) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key, 0);
    }


    private void setupUI(View view, final Context context) {
        // Set up touch listener for non-text box views to hide keyboard.
        if (view == null)
            return;
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard((Activity) context);
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView, context);
            }
        }
    }

    private void hideSoftKeyboard(Activity activity) {

        if (activity == null)
            return;

        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);

        if (inputMethodManager.isAcceptingText()) {
            if (activity.getCurrentFocus() != null)
                inputMethodManager.hideSoftInputFromWindow(
                        activity.getCurrentFocus().getWindowToken(), 0);
        }
    }
}
