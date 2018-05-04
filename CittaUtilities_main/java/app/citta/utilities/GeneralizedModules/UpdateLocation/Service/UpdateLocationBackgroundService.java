package app.citta.utilities.GeneralizedModules.UpdateLocation.Service;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.StrictMode;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;

import app.citta.utilities.SoapClass.SoapServiceCall;
import app.citta.utilities.utilities.Config;

/**
 * Created by ws-16 on 7/4/2017.
 */

public class UpdateLocationBackgroundService extends Service {

    public static final int TEN_SECONDS = 10000; // 10 seconds
    public Boolean isRunning = false;
    public LocationManager mLocationManager;
    public LocationUpdateListener mLocationListener;
    public Location previousBestLocation = null;
    public Context context = this;
    public SharedPreferences sharedPreferences;
    public String userid;
    public double Latitude, Longitude;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mLocationListener = new LocationUpdateListener();
        super.onCreate();

       /* sharedPreferences = context.getSharedPreferences(Preferences.PREFERENCE_FILE_NAME, MODE_PRIVATE);
        userid = sharedPreferences.getString(getResources().getString(R.string.userid), null);*/
        Config.getInstance().ToastWithMsg(UpdateLocationBackgroundService.this,"Service Started");
    }

    Handler mHandlerr = new Handler();
    Runnable mHandlerTask = new Runnable() {
        @Override
        public void run() {
            if (!isRunning) {
                startListening();
            }
            mHandlerr.postDelayed(mHandlerTask, TEN_SECONDS);
        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        mHandlerTask.run();
        return START_NOT_STICKY;
    }

    /* start update latlong and check for customer call  */
    public void startListening() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (mLocationManager.getAllProviders().contains(LocationManager.NETWORK_PROVIDER))
                mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, mLocationListener);

            if (mLocationManager.getAllProviders().contains(LocationManager.GPS_PROVIDER))
                mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mLocationListener);
        }
        isRunning = true;
    }

    /* Stop updates and wait state of service process */
    public void stopListening() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mLocationManager.removeUpdates(mLocationListener);
        }
        isRunning = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Config.getInstance().ToastWithMsg(UpdateLocationBackgroundService.this,"Service Stopped");
    }

    private class LocationUpdateListener implements LocationListener {
        @Override
        public void onLocationChanged(Location location) {
            if (isBetterLocation(location, previousBestLocation)) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                previousBestLocation = location;
                try {
                    Latitude = location.getLatitude();
                    Longitude = location.getLongitude();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    speedExceedMessageToActivity();
                    // UPDATE_LATLONG_EVERYTIME();       // you can update your location to DB via service call from here
                    stopListening();
                }
            }
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onProviderDisabled(String provider) {
            stopSelf();
        }
    }

    public boolean isBetterLocation(Location location, Location currentBestLocation) {
        if (currentBestLocation == null) {
            // A new location is always better than no location
            return true;
        }

        // Check whether the new location fix is newer or older
        long timeDelta = location.getTime() - currentBestLocation.getTime();
        boolean isSignificantlyNewer = timeDelta > TEN_SECONDS;
        boolean isSignificantlyOlder = timeDelta < -TEN_SECONDS;
        boolean isNewer = timeDelta > 0;

        // If it's been more than two minutes since the current location, use the new location
        // because the user has likely moved
        if (isSignificantlyNewer) {
            return true;
            // If the new location is more than two minutes older, it must be worse
        } else if (isSignificantlyOlder) {
            return false;
        }

        // Check whether the new location fix is more or less accurate
        int accuracyDelta = (int) (location.getAccuracy() - currentBestLocation.getAccuracy());
        boolean isLessAccurate = accuracyDelta > 0;
        boolean isMoreAccurate = accuracyDelta < 0;
        boolean isSignificantlyLessAccurate = accuracyDelta > 200;

        // Check if the old and new location are from the same provider
        boolean isFromSameProvider = isSameProvider(location.getProvider(), currentBestLocation.getProvider());

        // Determine location quality using a combination of timeliness and accuracy
        if (isMoreAccurate) {
            return true;
        } else if (isNewer && !isLessAccurate) {
            return true;
        } else if (isNewer && !isSignificantlyLessAccurate && isFromSameProvider) {
            return true;
        }
        return false;
    }

    /*
     * Checks whether two providers are the same
     */
    public boolean isSameProvider(String provider1, String provider2) {
        if (provider1 == null) {
            return provider2 == null;
        }
        return provider1.equals(provider2);
    }

    /*
    *   Local BroadCast Intent used for sending current latlong to UI Or MAP
    * */
    private void speedExceedMessageToActivity() {
        Intent intent = new Intent("speedExceeded");
        sendLocationBroadcast(intent);
    }

    private void sendLocationBroadcast(Intent intent) {
        intent.putExtra("latitude", Latitude);
        intent.putExtra("longitude", Longitude);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }


    /*
     *       SERVICE CALL FOR UPDATE LOCATION TO DATABASE AT INTERVAL OF 10 MINUTES
     * */
    public void UPDATE_LATLONG_EVERYTIME() {

        final String SOAPURL = Config.URL + "StudentTrackingService.svc";
        final String SOAPACTION = "http://tempuri.org/IBLStudentTrackingSvc/UpdateStudentTrackingLatLongByUserid";
        final String SOAPMETHOD = "UpdateStudentTrackingLatLongByUserid";

        String Lat = String.valueOf(Latitude);
        String Longi = String.valueOf(Longitude);

        SoapServiceCall updateDataService = new SoapServiceCall(SOAPURL);
        try {
            updateDataService.CallServiceWithoutObject(SOAPACTION, SOAPMETHOD,
                    "tem:userid", userid,
                    "tem:latitude", Lat,
                    "tem:longitude", Longi);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
