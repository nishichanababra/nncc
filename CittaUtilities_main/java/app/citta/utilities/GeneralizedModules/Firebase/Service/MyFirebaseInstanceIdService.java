package app.citta.utilities.GeneralizedModules.Firebase.Service;

import android.content.SharedPreferences;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by ws-16 on 7/19/2017.
 */

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {

    public static final String FIREBASE_TOKEN = "FIREBASE_TOKEN";
    public String recentToken = "";
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    public void onCreate() {
        super.onCreate();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        recentToken = FirebaseInstanceId.getInstance().getToken();
        if(recentToken != null && !recentToken.equals("") && !recentToken.isEmpty()){
            Log.d(FIREBASE_TOKEN,recentToken);
            saveTokenToPrefs(recentToken);
        }else{
            recentToken = "";
            saveTokenToPrefs(recentToken);
        }
    }
    public void saveTokenToPrefs(String recentToken) {
        // Access Shared Preferences
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

        // Save to SharedPreferences
        editor.putString("FIREBASE_TOKEN", recentToken);
        editor.apply();
    }
}
