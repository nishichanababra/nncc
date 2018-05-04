package app.citta.utilities.GeneralizedModules.Firebase.Service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.text.DateFormat;
import java.util.Date;

import app.citta.utilities.GeneralizedModules.SplashActivity;
import app.citta.utilities.R;


public class MyFirebaseMessangingService extends FirebaseMessagingService {

    private static final String FIREBASE_NOTIFICATION = "FIREBASE_NOTIFICATION";
    public String data, fbToken, notificationArrivalTime, status;


    @Override
    public void onCreate() {
        super.onCreate();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        data = remoteMessage.getNotification().getBody();
        if (data != null && !data.equals("")) {
            status = "Notification With Data";
        } else {
            status = "Notification Without Data";
        }

        fbToken = getTokenFromPrefs();
        notificationArrivalTime = DateFormat.getDateTimeInstance().format(new Date());

        Log.d(FIREBASE_NOTIFICATION, "FIREBASE_NOTIFICATION_ARRIVED");

        Intent intent = new Intent(MyFirebaseMessangingService.this, SplashActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(MyFirebaseMessangingService.this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(MyFirebaseMessangingService.this);
        builder.setContentTitle("welcome to Citta Utilities !!" + data);
        builder.setContentText("This is firebase notification demo");
        builder.setAutoCancel(true);
        builder.setSmallIcon(R.mipmap.firebase);
        builder.setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, builder.build());

        speedExceedMessageToActivity();

    }

    public String getTokenFromPrefs() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MyFirebaseMessangingService.this);
        return preferences.getString("FIREBASE_TOKEN", null);
    }

    /*
     *   Local BroadCast Intent used for sending current latlong to UI Or MAP
     * */
    private void speedExceedMessageToActivity() {
        Intent intent = new Intent("speedExceeded");
        sendLocationBroadcast(intent);
    }

    private void sendLocationBroadcast(Intent intent) {
        intent.putExtra("fbData", data);
        intent.putExtra("fbToken", fbToken);
        intent.putExtra("fbTime", notificationArrivalTime);
        intent.putExtra("fbStatus", status);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}
