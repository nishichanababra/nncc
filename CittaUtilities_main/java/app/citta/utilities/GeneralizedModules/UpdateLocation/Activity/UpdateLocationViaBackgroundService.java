package app.citta.utilities.GeneralizedModules.UpdateLocation.Activity;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;

import java.text.DateFormat;
import java.util.Date;

import app.citta.utilities.GeneralizedModules.UpdateLocation.Service.UpdateLocationBackgroundService;
import app.citta.utilities.R;
import app.citta.utilities.utilities.Config;
import butterknife.BindView;
import butterknife.ButterKnife;

public class UpdateLocationViaBackgroundService extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        ResultCallback<LocationSettingsResult>, View.OnClickListener {

    @BindView(R.id.tv_latitude)
    TextView tv_latitude;
    @BindView(R.id.tv_longitude)
    TextView tv_longitude;
    @BindView(R.id.tv_heading)
    TextView tv_heading;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.btn_startService)
    Button btn_startservice;
    @BindView(R.id.btn_stopservice)
    Button btn_stopservice;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    GoogleApiClient mGoogleApiClient;
    protected static final String TAG = "UpdateLocationViaBackgroundService";
    LocationRequest mLocationRequest;
    protected LocationSettingsRequest mLocationSettingsRequest;
    protected static final int REQUEST_CHECK_SETTINGS = 0x1;
    public static final long UPDATE_INTERVAL_IN_MILLISECONDS = 10000;
    public static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = UPDATE_INTERVAL_IN_MILLISECONDS / 2;
    String currentTime;
    double Latitude, Longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_location_via_background_service);
        ButterKnife.bind(this);
        inittoolbar();
        btn_startservice.setOnClickListener(this);
        btn_stopservice.setOnClickListener(this);
        buildGoogleApiClient();
        createLocationRequest();
        buildLocationSettingsRequest();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
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
        mTitle.setText(getResources().getString(R.string.backGroundservice));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_startService:
                checkLocationSettings();
                break;

            case R.id.btn_stopservice:
                if (isMyServiceRunning()) {
                    stopService(new Intent(UpdateLocationViaBackgroundService.this, UpdateLocationBackgroundService.class));
                    LocalBroadcastManager.getInstance(UpdateLocationViaBackgroundService.this).unregisterReceiver(mMessageReceiver);
                    tv_heading.setText("Background service is stopped !!");
                }
                break;
        }
    }

    /**
     * Builds a GoogleApiClient. Uses the {@code #addApi} method to request the
     * LocationServices API.
     */
    public synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    /**
     * Sets up the location request. Android has two location request settings:
     * {@code ACCESS_COARSE_LOCATION} and {@code ACCESS_FINE_LOCATION}. These settings control
     * the accuracy of the current location. This sample uses ACCESS_FINE_LOCATION, as defined in
     * the AndroidManifest.xml.
     * <p/>
     * When the ACCESS_FINE_LOCATION setting is specified, combined with a fast update
     * interval (5 seconds), the Fused Location Provider API returns location updates that are
     * accurate to within a few feet.
     * <p/>
     * These settings are appropriate for mapping applications that show real-time location
     * updates.
     */
    public void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        // Sets the desired interval for active location updates. This interval is
        // inexact. You may not receive updates at all if no location sources are available, or
        // you may receive them slower than requested. You may also receive updates faster than
        // requested if other applications are requesting location at a faster interval.
        mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
        // Sets the fastest rate for active location updates. This interval is exact, and your
        // application will never receive updates faster than this value.
        mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
        //mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    /**
     * Uses a {@link LocationSettingsRequest.Builder} to build
     * a {@link LocationSettingsRequest} that is used for checking
     * if a device has the needed location settings.
     */
    public void buildLocationSettingsRequest() {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(mLocationRequest);
        mLocationSettingsRequest = builder.build();
        builder.setAlwaysShow(true);
    }

    /**
     * Check if the device's location settings are adequate for the app's needs using the
     * {@link com.google.android.gms.location.SettingsApi#checkLocationSettings(GoogleApiClient,
     * LocationSettingsRequest)} method, with the results provided through a {@code PendingResult}.
     */
    public void checkLocationSettings() {
        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi.checkLocationSettings(
                        mGoogleApiClient,
                        mLocationSettingsRequest
                );
        result.setResultCallback(this);
    }

    /**
     * The callback invoked when
     * {@link com.google.android.gms.location.SettingsApi#checkLocationSettings(GoogleApiClient,
     * LocationSettingsRequest)} is called. Examines the
     * {@link LocationSettingsResult} object and determines if
     * location settings are adequate. If they are not, begins the process of presenting a location
     * settings dialog to the user.
     */
    @Override
    public void onResult(@NonNull LocationSettingsResult locationSettingsResult) {
        final Status status = locationSettingsResult.getStatus();
        switch (status.getStatusCode()) {
            case LocationSettingsStatusCodes.SUCCESS:

                startService(new Intent(UpdateLocationViaBackgroundService.this, UpdateLocationBackgroundService.class));
                LocalBroadcastManager.getInstance(UpdateLocationViaBackgroundService.this).registerReceiver(mMessageReceiver, new IntentFilter("speedExceeded"));
                tv_heading.setText("Background Service is started");
                break;

            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                try {
                    // Show the dialog by calling startResolutionForResult(), and check the result
                    // in onActivityResult().
                    status.startResolutionForResult(UpdateLocationViaBackgroundService.this, REQUEST_CHECK_SETTINGS);
                } catch (IntentSender.SendIntentException e) {
                    e.printStackTrace();
                }
                break;
            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:

                Config.getInstance().ToastWithMsg(UpdateLocationViaBackgroundService.this, "Location settings are inadequate, and cannot be fixed here. Dialog not created.");
                finish();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            // Check for the integer request code originally supplied to startResolutionForResult().
            case REQUEST_CHECK_SETTINGS:
                switch (resultCode) {
                    case Activity.RESULT_OK:

                        startService(new Intent(UpdateLocationViaBackgroundService.this, UpdateLocationBackgroundService.class));
                        LocalBroadcastManager.getInstance(UpdateLocationViaBackgroundService.this).registerReceiver(mMessageReceiver, new IntentFilter("speedExceeded"));
                        tv_heading.setText("Background Service is started");
                        break;
                    case Activity.RESULT_CANCELED:
                        Config.getInstance().ToastWithMsg(UpdateLocationViaBackgroundService.this, "You need to turn on your location compulsory !!");
                        finish();
                        break;
                }
                break;
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    /*      Local BROADCAST reciever for get latlong from background service
     *       This Broadcast will always run if background service is running
     * */
    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Latitude = intent.getDoubleExtra("latitude", 0);
            Longitude = intent.getDoubleExtra("longitude", 0);
            currentTime = DateFormat.getDateTimeInstance().format(new Date());

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tv_latitude.setText("Latest Latitude is : " + "" + Latitude);
                    tv_longitude.setText("Latest Longitude is : " + "" + Longitude);
                    tv_time.setText("Current Time is : " + currentTime);
                }
            });
        }
    };


    /*              HERE I AM GETTING THAT MY SERVICE IS RUNIING OR NOT     */
    public boolean isMyServiceRunning() {
        ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if ("app.citta.utilities.GeneralizedModules.UpdateLocation.Service.UpdateLocationBackgroundService".equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (isMyServiceRunning()) {
            stopService(new Intent(UpdateLocationViaBackgroundService.this, UpdateLocationBackgroundService.class));
            LocalBroadcastManager.getInstance(UpdateLocationViaBackgroundService.this).unregisterReceiver(mMessageReceiver);
            finish();
        } else {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mGoogleApiClient.disconnect();
    }
}
