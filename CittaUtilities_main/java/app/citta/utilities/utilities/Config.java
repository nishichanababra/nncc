package app.citta.utilities.utilities;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import app.citta.utilities.Listeners.GlobalAlertDialogListner;
import app.citta.utilities.SoapClass.SoapServiceCall;
import app.citta.utilities.Listeners.GlobalInternetDialogListner;
import app.citta.utilities.Preferences.Preferences;
import app.citta.utilities.R;

import static android.content.Context.WIFI_SERVICE;

public class Config {

    private static Config Config;

    public Context context;
    public static Config getInstance() {
        if (Config == null)
            Config = new Config();
        return Config;
    }

    /* ******************************************   Application service call URL ******************************************************** */
    //  public static final String URL = "http://192.168.0.110:9004/Services/";      ////// local
    public static final String URL = "http://mygps.demoforyou.co.in/wcf/Services/";  ////// live

    /* ***********************************************       LOGGER IDS    ********************** */
    public final String logger_LOGIN = "1";
    public final String logger_FORGOT_PASSWORD = "2";
    public final String logger_CHANGE_PASSWORD = "3";
    public final String logger_MAIN_ACTIVITY = "4";
    public final String logger_PROFILE = "5";
    public final String logger_PROFILE_IMAGE_UPLOAD = "6";
    public final String logger_PROFILE_DELETE_IMAGE = "7";
    public final String logger_PROFILE_SAVE_PROFILE = "8";
    public final String logger_TIMETABLE_BYDATE = "9";
    public final String logger_TIMETABLE_BYFROMTO = "10";
    public final String logger_EXAM_SCHEDULE = "11";
    public final String logger_RESULT = "12";
    public final String logger_RESULT_SHARE = "13";
    public final String logger_RESULT_SCREENSHOTS = "14";
    public final String logger_ATTENDANCE = "15";
    public final String logger_ATTENDANCE_FILL_ATTENDANCE = "16";
    public final String logger_ATTENDANCE_FILL_ATTENDANCE_MANUALLY = "17";
    public final String logger_PAYMENT_HISTORY = "18";
    public final String logger_LOGOUT = "19";
    public final String logger_STUDENT_TRACKING_ACCESS = "20";
    public final String logger_STUDENT_TRACKING_ACCESS_TRUE = "21";
    public final String logger_STUDENT_TRACKING_ACCESS_FALSE = "22";
    public final String logger_STUDENT_TRACKING_BY_EMPLOYEE = "23";
    public final String logger_STUDENT_TRACKING_MAP_SHOWING = "24";


    /* ***************************** final variables for fetch address service class  *****************************************  */
    public static final int SUCCESS_RESULT = 0;
    public static final int FAILURE_RESULT = 1;
    public static final String PACKAGE_NAME = "com.example.i5.gps_masterproject";
    public static final String RECEIVER = PACKAGE_NAME + ".RECEIVER";
    public static final String RESULT_DATA_KEY = PACKAGE_NAME + ".RESULT_DATA_KEY";
    public static final String LOCATION_DATA_EXTRA = PACKAGE_NAME + ".LOCATION_DATA_EXTRA";

    /* *****************************   Constant values for finding LATLONG FROM ADDRESS ***************************************  */
    public static final int USE_ADDRESS_NAME = 1;
    public static final int USE_ADDRESS_LOCATION = 2;
    public static final String RESULT_ADDRESS = PACKAGE_NAME + ".RESULT_ADDRESS";
    public static final String LOCATION_LATITUDE_DATA_EXTRA = PACKAGE_NAME + ".LOCATION_LATITUDE_DATA_EXTRA";
    public static final String LOCATION_LONGITUDE_DATA_EXTRA = PACKAGE_NAME + ".LOCATION_LONGITUDE_DATA_EXTRA";
    public static final String LOCATION_NAME_DATA_EXTRA = PACKAGE_NAME + ".LOCATION_NAME_DATA_EXTRA";
    public static final String FETCH_TYPE_EXTRA = PACKAGE_NAME + ".FETCH_TYPE_EXTRA";


    /* *************************************************** OTP CREDENTIALS ****************************************************** */
    public static final String OTP_authKey = "123871Af8MceWnde57c5190a";
    public static final String OTP_senderID = "REFHSE";
    public static final String OTP_route = "4";

    /* ******************************* IMAGE UPLOAD CREDENTIALS **************************************************************** */
    public static final String FTP_HOST = "182.50.151.42";
    public static final String FTP_USER = "gpsdemo";
    public static final String FTP_PASS = "mylife@123";
    public static final String FTP_DIRECTORY_CUSTOMER = "/ImageFolder/CustomerImage/";
    public static final String FTP_DOMAIN = "http://mygps.demoforyou.co.in";


    /* *****************************   Regular expressions strings for preference and validator class *********************************** */
    public final static String REGEX_EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static final String REGEX_NAME_PATTERN = "[a-zA-Z]+";


    /* **********************************************  TOAST FOR INTERNET  ************************************************************** */
    public void InternetToast(Context context) {
        Toast.makeText(context, "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();
    }

    /* ********************************************** TOAST WITH MSG FROM CLASS ********************************************************* */
    public void ToastWithMsg(Context context, String Msg) {
        Toast.makeText(context, Msg, Toast.LENGTH_SHORT).show();
    }


    /* ******************************************  DIALOG FOR INTERNET  ***************************************************************** */
    private GlobalInternetDialogListner globalInternetDialogListner;

    public void GlobalInternetDialog(final Context context) {
        new AlertDialog.Builder(context).setCancelable(false).setIcon(R.mipmap.interneticon)
                .setTitle("No internet connection !!")
                .setMessage("It looks like your internet connection is off. Please turn it on and try again.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        globalInternetDialogListner.globalInternetDialogOnClick(context);
                    }
                }).show();
    }

    public void registerGlobalInternetDialogOnClick(GlobalInternetDialogListner globalInternetDialogListner) {
        this.globalInternetDialogListner = globalInternetDialogListner;
    }


    /* ***************************************** DIALOG WITH TITLE AND MSG FROM ALL CLASSES ********************************************* */

    private GlobalAlertDialogListner globalAlertDialogListner;

    public void GlobalAlertDialog(final Context context, String Title, String msg, final Boolean clickFromActivity) {
        new AlertDialog.Builder(context).setCancelable(false).setIcon(R.mipmap.ic_launcher)
                .setTitle(Title)
                .setMessage(msg)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!clickFromActivity) {
                            dialog.dismiss();
                        } else {
                            dialog.dismiss();
                            if (globalAlertDialogListner != null) {
                                globalAlertDialogListner.globalAlertDialogOnClick(context);
                            }
                        }
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
    }

    public void registerGlobalAlertDialogClickListener(GlobalAlertDialogListner globalAlertDialogListner) {
        this.globalAlertDialogListner = globalAlertDialogListner;
    }


    /*  ***************************   Transeperent ProgressDialog   ****************************************************************     */
    private TransparentProgressDialog pd;
    public Runnable r;
    public static String PLEASE_WAIT = "Please Wait...";

    /* *************************************      Creating custom Progress Dialog with this class    ***********************************  */
    private class TransparentProgressDialog extends Dialog {
        private ImageView iv;

        TransparentProgressDialog(Activity activity, int resourceIdOfImage, String title) {
            super(activity, R.style.TransparentProgressDialog);
            WindowManager.LayoutParams wlmp = getWindow().getAttributes();
            wlmp.gravity = Gravity.CENTER_HORIZONTAL;
            getWindow().setAttributes(wlmp);
            setCanceledOnTouchOutside(false);
            setCancelable(false);
            setOnCancelListener(null);
            LinearLayout layout = new LinearLayout(activity);
            layout.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            iv = new ImageView(activity);
            iv.setImageResource(resourceIdOfImage);
            iv.setPadding(20, 20, 20, 20);
            layout.addView(iv, params);
            addContentView(layout, params);
            setTitle(title);
        }

        @Override
        public void show() {
            if (!isShowing()) {
                super.show();
                RotateAnimation anim = new RotateAnimation(0.0f, 360.0f, Animation.RELATIVE_TO_SELF, .5f, Animation.RELATIVE_TO_SELF, .5f);
                anim.setInterpolator(new LinearInterpolator());
                anim.setRepeatCount(Animation.INFINITE);
                anim.setDuration(3000);
                iv.setAnimation(anim);
                iv.startAnimation(anim);
            }
        }

        @Override
        public void dismiss() {
            super.dismiss();
        }

        @Override
        protected void onStop() {
            super.onStop();
            if (pd != null) {
                if (pd.isShowing()) {
                    dismiss();
                }
            }
        }
    }

    /* ****************   PROGRESSBAR START FROM WHOLE APPLICATION    ( Config.getInstance().startProgressdialog(context,Congfig.PLEASE_WAIT);  ************* */
    public void startProgressdialog(Activity activity, String title) {
        if (pd == null) {
            pd = new TransparentProgressDialog(activity, R.mipmap.progressbar, title);
            pd.show();
        }
    }

    /* *********************     PROGRESSBAR STOP FROM WHOLE APPLICATION  ( Config.getInstance().stopProgrssDialog(); )    *********************************** */
    public void stopProgressdialog() {
        if (pd != null) {
            pd.dismiss();
            pd = null;
        }
    }


    /* *********************************       Boolean class for check internet connection ******************************************************** */
    private final String TAG = Config.class.getSimpleName();

    public boolean isInternetAvailable(Context context) {
        NetworkInfo info = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();

        if (info == null) {
            Log.d(TAG, "no internet connection");
            return false;
        } else {
            if (info.isConnected()) {
                Log.d(TAG, " internet connection available...");
                return true;
            } else {
                Log.d(TAG, " internet connection");
                return true;
            }
        }
    }

    /* ****************************************** marshmallow permission asking to user ************************************************ */
    public static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }
    /*  // The request code used in ActivityCompat.requestPermissions()
                        // and returned in the Activity's onRequestPermissionsResult()
                        int PERMISSION_ALL = 1;
                        String[] PERMISSIONS = {Manifest.permission.CALL_PHONE,
                                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                                Manifest.permission.CAMERA,
                                                Manifest.permission.SEND_SMS};

                        if (!hasPermissions(SplashActivity.this, PERMISSIONS)) {
                            ActivityCompat.requestPermissions(SplashActivity.this, PERMISSIONS, PERMISSION_ALL);
                        }*/


    /* ***************************************************************************************
     *   Method Call of Image Upload Permissions
     *   This Method is called in profile picture upload
     * *************************************************************************************** */
    private final int MY_PERMISSIONS_REQUEST = 123;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public boolean checkPermissionForImageUpload(final Context context, final String permission) {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, permission)) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                    alertBuilder.setCancelable(true);
                    alertBuilder.setTitle("Please allow permissions to R3");
                    alertBuilder.setMessage("External permissions are required for this app !!");
                    alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions((Activity) context,
                                    new String[]{permission}, MY_PERMISSIONS_REQUEST);
                        }
                    });
                    AlertDialog alert = alertBuilder.create();
                    alert.show();
                } else {
                    ActivityCompat.requestPermissions((Activity) context,
                            new String[]{permission}, MY_PERMISSIONS_REQUEST);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    /* *****************************************   Random number generator function for generate OTP ****************************************** */
    public long createRandomInteger(int aStart,long aEnd) {

        Random aRandom = new Random();
        if (aStart > aEnd) {
            throw new IllegalArgumentException("Start cannot exceed End.");
        }
        //get the range, casting to long to avoid overflow problems
        long range = aEnd - (long) aStart + 1;
        // compute a fraction of the range, 0 <= frac < range
        long fraction = (long) (range * aRandom.nextDouble());
        long randomNumber = fraction + (long) aStart;
        return randomNumber;
    }

    /* *****************************************  LOGGER METHOD FOR USER INTERACTION UPDATE AT WHOLE APPLICATION *******************************      */
    public void logger(Context context, String actionid) {

        String userid = Preferences.getInstance().getSharedPreferenceString((Activity) context, context.getResources().getString(R.string.userid));
        String cid = Preferences.getInstance().getSharedPreferenceString((Activity) context, context.getResources().getString(R.string.Cid));
        String bid = Preferences.getInstance().getSharedPreferenceString((Activity) context, context.getResources().getString(R.string.Bid));
        String Usertypeid = Preferences.getInstance().getSharedPreferenceString((Activity) context, context.getResources().getString(R.string.Usertypeid));


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormatter.setLenient(false);
        Date today = new Date();
        String s = dateFormatter.format(today);

        String todaysdate = sdf.format(today);

        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String imei = telephonyManager.getDeviceId();
        String loginOs = "Android " + Build.VERSION.RELEASE;
        String loginDevice = Build.MANUFACTURER + " " + Build.DEVICE + " " + Build.BRAND + " " + Build.MODEL;

        @SuppressLint("WifiManagerLeak")
        WifiManager wm = (WifiManager) context.getSystemService(WIFI_SERVICE);
        String deviceIpAddress = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());

        final String URLL = URL + "UserActionHistoryService.svc";
        final String SOAPAction = "http://tempuri.org/IBLUserActionHistorySvc/SaveNewUserActionHistory";
        final String SOAPMETHOD = "SaveNewUserActionHistory";
        final String Object = "blUserActionHistory";

        SoapServiceCall fd1 = new SoapServiceCall(URLL);
        try {
            fd1.CallServiceObject(SOAPAction, SOAPMETHOD, Object,
                    "bus:Actionid", actionid,
                    "bus:Bid", bid,
                    "bus:Cid", cid,
                    "bus:Entrydate", todaysdate,
                    "bus:Imeino", imei,
                    "bus:Ipaddress", deviceIpAddress,
                    "bus:Isactive", 1,
                    "bus:Isdelete", 0,
                    "bus:Issync", 1,
                    "bus:Lastlogindevice", loginOs + " " + loginDevice,
                    "bus:Latitude", 0.000,
                    "bus:Longitude", 0.000,
                    "bus:Sid", Usertypeid,
                    "bus:Userid", userid);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* ****************************       Method and Variables for Localization of Languages *********************************** */

    public static String HINDI = "hi";
    public static String ENGLISH = "en";
    public static String GUJARATI = "gu";

    public void setLanguages(String lang,Context context){

        Resources res = context.getResources();
        // Change locale settings in the app.
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            conf.setLocale(new Locale(lang)); // API 17+ only.
        }
        // Use conf.locale = new Locale(...) if targeting lower versions
        res.updateConfiguration(conf, dm);
    }
}