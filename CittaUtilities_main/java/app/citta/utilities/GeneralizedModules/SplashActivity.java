package app.citta.utilities.GeneralizedModules;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import app.citta.utilities.GeneralizedModules.MainActivity.Activity.MainActivity;
import app.citta.utilities.Listeners.GlobalInternetDialogListner;
import app.citta.utilities.Preferences.Preferences;
import app.citta.utilities.R;
import app.citta.utilities.utilities.Config;


public class SplashActivity extends AppCompatActivity implements GlobalInternetDialogListner{

    private static final int PICK_REQUESTCALL = 11;
    private static final int PICK_REQUESTSTORAGE = 12;
    private static final int PICK_REQUESTCAMERA = 13;
    private static final int PICK_REQUESTSMS = 14;
    private static final int PICK_REQUESTLOCATION = 15;

    private Context context = this;
    String userSelectedLanguage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userSelectedLanguage = Preferences.getInstance().getSharedPreferenceString((Activity)context,getResources().getString(R.string.UserSelectedLanguage));
        Config.getInstance().registerGlobalInternetDialogOnClick(SplashActivity.this);

        if(userSelectedLanguage != null) {
            Config.getInstance().setLanguages(userSelectedLanguage, SplashActivity.this);
        }
        setContentView(R.layout.activity_splash);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        if(Config.getInstance().isInternetAvailable(SplashActivity.this)){
            Thread timerThread = new Thread() {
                public void run() {
                    try {
                        sleep(2000);
                        checkpermissioncall();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            timerThread.start();
        }else{
            Config.getInstance().GlobalInternetDialog(SplashActivity.this);
        }
    }

    /* **************************************************************************************       1.     Checking User Permissions for CALL */
    private void checkpermissioncall() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
            }
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, PICK_REQUESTCALL);
        } else {
            checkpermissionCamera();
        }
    }

    /* **************************************************************************************       2.   Checkiong User Permission for CAMERA  */
    private void checkpermissionCamera() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
            }
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PICK_REQUESTCAMERA);
        } else {
            checkpermissionSMS();
        }
    }

    /* **************************************************************************************       3.     Checkiong User Permission for SMS   */
    private void checkpermissionSMS() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)) {
            }
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, PICK_REQUESTSMS);
        } else {
            checkpermissionStorage();
        }
    }


    /* **************************************************************************************       4.     Checkiong User Permission for READ_EXTERNAL_STORAGE */
    private void checkpermissionStorage() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            }
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PICK_REQUESTSTORAGE);
        } else {
            checkpermissionLOCATION();
        }
    }

    /* **************************************************************************************       5.     Checkiong User Permission for ACCESS_FINE_LOCATION  */
    private void checkpermissionLOCATION() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            }
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PICK_REQUESTLOCATION);
        } else {

            /* *       SET INTENT HERE TO NEXT ACTIVITY     * */

            String userid = Preferences.getInstance().getSharedPreferenceString(SplashActivity.this, getResources().getString(R.string.userid));
            if (userid != null && !userid.equalsIgnoreCase("")) {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PICK_REQUESTCALL:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    checkpermissionCamera();
                } else {
                    checkpermissionCamera();
                }
                break;

            case PICK_REQUESTCAMERA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    checkpermissionSMS();
                } else {
                    checkpermissionSMS();
                }
                break;

            case PICK_REQUESTSMS:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    checkpermissionStorage();
                } else {
                    checkpermissionStorage();
                }
                break;

            case PICK_REQUESTSTORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    checkpermissionLOCATION();
                } else {
                    checkpermissionLOCATION();
                }
                break;

            case PICK_REQUESTLOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                   /* *       SET INTENT HERE TO NEXT ACTIVITY     * */
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                     /* *       SET INTENT HERE TO NEXT ACTIVITY     * */
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void globalInternetDialogOnClick(Context context) {
        ((Activity) this.context).finish();
    }
}
