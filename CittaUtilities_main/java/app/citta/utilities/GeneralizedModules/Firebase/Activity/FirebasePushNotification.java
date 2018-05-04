package app.citta.utilities.GeneralizedModules.Firebase.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.citta.utilities.GeneralizedModules.Firebase.Adapter.FirebaseNotificationAdapter;
import app.citta.utilities.GeneralizedModules.Firebase.Listener.NotificationItemClickListener;
import app.citta.utilities.GeneralizedModules.Firebase.LocalDB.DBAdapter;
import app.citta.utilities.GeneralizedModules.Firebase.POJO.FirebaseNotificationsPOJO;
import app.citta.utilities.Listeners.GlobalAlertDialogListner;
import app.citta.utilities.R;
import app.citta.utilities.utilities.Config;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FirebasePushNotification extends AppCompatActivity implements NotificationItemClickListener, GlobalAlertDialogListner {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.notificationsRecyclerview)
    RecyclerView recyclerNotifications;
    @BindView(R.id.tv_refreshedtoken)
    TextView tv_refreshedToken;

    List<FirebaseNotificationsPOJO> firebaseNotificationsPOJOs;
    FirebaseNotificationAdapter firebaseNotificationAdapter;
    DBAdapter dbAdapter;
    String deleteRowId = "", refreshedToken = "";
    int deletePosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_push_notification_demo);
        Config.getInstance().registerGlobalAlertDialogClickListener(FirebasePushNotification.this);
        ButterKnife.bind(this);
        dbAdapter = new DBAdapter(FirebasePushNotification.this);
        inittoolbar();

        refreshedToken = getTokenFromPrefs();

        LinearLayoutManager layoutManager = new LinearLayoutManager(FirebasePushNotification.this, LinearLayoutManager.VERTICAL, false);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerNotifications.setLayoutManager(layoutManager);
        recyclerNotifications.setItemAnimator(new DefaultItemAnimator());
        inittoolbar();
        LocalBroadcastManager.getInstance(FirebasePushNotification.this).registerReceiver(mMessageReceiver, new IntentFilter("speedExceeded"));

        tv_refreshedToken.setText(" Your Current Firebase Token is :  " + " \n \n " + refreshedToken);
        Log.d("FIREBASE_TOKEN", refreshedToken);
        getAllNotifications();
    }


    public void inittoolbar() {
        // Find the toolbar view inside the activity layout
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        ImageView back = (ImageView) toolbar.findViewById(R.id.back);
        mTitle.setText(getResources().getString(R.string.firebasepushnotification));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    /*
     *   Get Refreshed token from Shared Preference
     * */
    public String getTokenFromPrefs() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(FirebasePushNotification.this);
        return preferences.getString("FIREBASE_TOKEN", null);
    }

    /*      Local BROADCAST reciever for get latlong from background service
     *       This Broadcast will always run if background service is running
     * */
    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String data = intent.getStringExtra("fbData");
            String token = intent.getStringExtra("fbToken");
            String time = intent.getStringExtra("fbTime");
            String status = intent.getStringExtra("fbStatus");

            saveNewNotification(data, token, time, status);
        }
    };

    public void saveNewNotification(String data, String token, String time, String status) {

        dbAdapter.saveNewFirebaseNotification(token, data, time, status);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getAllNotifications();
            }
        }, 500);
    }

    /*  Get All Previous Notification from local DB and update RecyclerView here
    *
    * */
    public void getAllNotifications() {
        Cursor cursor = dbAdapter.getAllFirebaseNotifications();

        if (cursor != null) {
            if (cursor.getCount() > 0) {
                if (cursor.moveToFirst()) {
                    firebaseNotificationsPOJOs = new ArrayList<>();
                    do {
                        String id = cursor.getString(cursor.getColumnIndex("ID"));
                        String token = cursor.getString(cursor.getColumnIndex("TOKEN"));
                        String data = cursor.getString(cursor.getColumnIndex("DATA"));
                        String time = cursor.getString(cursor.getColumnIndex("TIME"));
                        String status = cursor.getString(cursor.getColumnIndex("STATUS"));

                        FirebaseNotificationsPOJO firebaseNotificationsPOJO = new FirebaseNotificationsPOJO(id, token, data, time, status);
                        firebaseNotificationsPOJOs.add(firebaseNotificationsPOJO);

                    } while (cursor.moveToNext());

                    if (firebaseNotificationsPOJOs.size() != 0) {
                        firebaseNotificationAdapter = new FirebaseNotificationAdapter(FirebasePushNotification.this, firebaseNotificationsPOJOs);
                        recyclerNotifications.setAdapter(firebaseNotificationAdapter);
                        firebaseNotificationAdapter.notifyDataSetChanged();

                        /*  Register This Class with Method of listener for get an Event of item click*/
                        firebaseNotificationAdapter.registerNotificationListener(FirebasePushNotification.this);
                    }
                }
            }
        }
    }

    /*
    *   This is notification Item click listener method which will invoke global dialog for delete an item via "globalAlertDialogOnClick" method defined at below
    *   of this method.
    * */
    @Override
    public void notificationItemClicked(String id, String fbToken, String fbData, String fbTime, String fbStatus,int position) {
        deleteRowId = id;
        deletePosition = position;
        Config.getInstance().GlobalAlertDialog(FirebasePushNotification.this, "Delete item ?", "Are you sure you want to delete this Notification?", true);
    }

    /*
    *   On click of ok button of dialog , item will be deleted from local DB here and recyclerView will be updated again.
    * */
    @Override
    public void globalAlertDialogOnClick(Context context) {
        dbAdapter.deletePlanVisitData(deleteRowId);
        firebaseNotificationsPOJOs.remove(deletePosition);
        firebaseNotificationAdapter.notifyItemRemoved(deletePosition);
        firebaseNotificationAdapter.notifyItemRangeChanged(deletePosition,firebaseNotificationsPOJOs.size());
    }
}