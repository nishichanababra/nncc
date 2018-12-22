package app.citta.retail365cloud.abstractclasses;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import app.citta.retail365cloud.R;
import app.citta.retail365cloud.activities.DashboardActivity;
import app.citta.retail365cloud.apimodels.UserLoginResponse;
import app.citta.retail365cloud.apimodels.UserLoginResponseObject;
import app.citta.retail365cloud.retrofit.RetrofitAPIClient;
import app.citta.retail365cloud.retrofit.WebServicesAPI;
import app.citta.retail365cloud.utils.AppPreference;
import app.citta.retail365cloud.utils.MyProgressDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class BaseActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();

    private Context mContext = BaseActivity.this;

    public static final String CurrentFont = "Simple-Line-Icons.ttf";

    public static final String RETROFIT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String RETROFIT_API_CALLING_DATE_FORMAT = "yyyy-MM-dd";
    public static final String dd__MMM__yyyy = "dd MMM yyyy";
    public static final String dd__MMM__yyyy_hh_mm_aa = "dd MMM yyyy Thh:mm aa";
    public static final String CURRENT_DATE_FORMAT = "dd-MM-yyyy";
    public static final String RETROFIT_DATE_FORMAT_LIST = "yyyy-MM-dd'T'HH:mm:ss aa";
    public final int ACTIVATION_KEY_LENGTH = 9;
    public final int MY_PERMISSIONS_REQUEST = 123;

    private boolean isNewUser;
    public MyProgressDialog myProgressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // For NetworkOnMainThreadException Handling
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    /*
     * Method to Check Device is Connected to the Internet,
     * Return true if connected otherwise false
     *
     *
     * */
    public boolean isOnline(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return (netInfo != null && netInfo.isConnected());
    }

    /*Connection Checker*/
    public static class MyConnectivityChecker {
        public static boolean isConnected(Context context) {
            boolean connected = false;
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            connected = (wifi.isAvailable() && wifi.isConnectedOrConnecting() || (mobile.isAvailable() && mobile.isConnectedOrConnecting()));
            return connected;
        }
    }

    public boolean isInternetAvailable(Context context) {

        NetworkInfo info = ((ConnectivityManager) Objects.requireNonNull(context.getSystemService(Context.CONNECTIVITY_SERVICE))).getActiveNetworkInfo();
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

    /*Permission of Marshmallow*/
    public static boolean hasPermissions(@Nullable Context context, @Nullable String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public boolean checkRunTimePermission(final Context context, final String permission) {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, permission)) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                    alertBuilder.setCancelable(true);
                    alertBuilder.setTitle("Please allow permissions to Retail35cloud");
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

    /*Show Custom Toast*/
    public static void showCustomToast(@Nullable Context context, @Nullable String toastmsg) {
        if (toastmsg != null && context != null) {
            Toast.makeText(context, toastmsg, Toast.LENGTH_LONG).show();
        }
    }

    /* ****************   PROGRESSBAR START FROM WHOLE APPLICATION    ( BaseActivity.getInstance().startProgressDialog(context,Congfig."");  ************* */
    public void startProgressDialog(Activity activity, String title, boolean cancelable) {
        try {
            if (myProgressDialog == null) {
                myProgressDialog = MyProgressDialog.show(activity, "", "", true, cancelable);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* *********************     PROGRESSBAR STOP FROM WHOLE APPLICATION  ( BaseActivity.getInstance().stopProgrssDialog(); )    *********************************** */
    public void stopProgressDialog() {
        try {
            if (myProgressDialog != null) {
                myProgressDialog.dismiss();
                myProgressDialog = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getProperdate(int date) {
        if (date < 10) {
            return "0" + String.valueOf(date);
        }
        return String.valueOf(date);
    }

    /*
     * Method to convert date format from one format to another format
     *
     * */
    @SuppressLint("SimpleDateFormat")
    public static String dateConversion(String inputDateFormat, String outputDateFormat, String inputDate) {

        String outputDate = "";

        try {

            if (!TextUtils.isEmpty(inputDate)) {

                DateFormat originalFormat = new SimpleDateFormat(inputDateFormat, Locale.ENGLISH);
                DateFormat targetFormat = new SimpleDateFormat(outputDateFormat, Locale.ENGLISH);
                Date date = originalFormat.parse(inputDate);
                outputDate = targetFormat.format(date);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return outputDate;
    }

    /*
     * Method to display no internet toast messages
     *
     * */
    public void displayInternetToastMessage(Context context) {

        Toast.makeText(context, getResources().getString(R.string.internetConnection), Toast.LENGTH_SHORT).show();
    }


    /* ******************************************  DIALOG FOR INTERNET  ***************************************************************** */

    public GlobalAlertDialogListner globalAlertDialogListner;

    /* ***************************************** DIALOG WITH TITLE AND MSG FROM ALL CLASSES ********************************************* */

    public void GlobalAlertDialog(final Context context, String Title, String msg, final Boolean clickFromActivity) {

        try {

            Drawable drawable = getResources().getDrawable(R.mipmap.alert);
            TypedValue typedValue = new TypedValue();
            Resources.Theme theme = getTheme();
            theme.resolveAttribute(R.attr.colorPrimary, typedValue, true);
            @ColorInt int color = typedValue.data;
            // drawable.setTint(color);

            new AlertDialog.Builder(context).setCancelable(false).setIcon(drawable)
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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface GlobalAlertDialogListner {
        void globalAlertDialogOnClick(Context context);
    }


    public void registerGlobalAlertDialogClickListener(GlobalAlertDialogListner globalAlertDialogListner) {
        this.globalAlertDialogListner = globalAlertDialogListner;
    }

    public String getCurrentDate() {
        String entryDate = "";
        try {
            entryDate = new SimpleDateFormat(CURRENT_DATE_FORMAT, Locale.US).format(new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entryDate;
    }

    /*
     * Method to get current date as per user defined format
     *
     * */
    public static String getCurrentDate(String outputDateFormat) {

        Calendar calendar = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat(outputDateFormat);
        return simpleDateFormat.format(calendar.getTime());
    }

    /*** Method to subtract days in current date ***/
    public static String subtractDaysInDate(int days) {

        String newDate = "";

        try {

            Calendar calender = Calendar.getInstance();
            calender.add(Calendar.DAY_OF_MONTH, -days);
            Date date = new Date();
            date.setTime(calender.getTimeInMillis());
            @SuppressLint("SimpleDateFormat") SimpleDateFormat postFormatter = new SimpleDateFormat("dd-MM-yyyy");
            newDate = postFormatter.format(date);
            return newDate;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return newDate;
    }

    /*** Method to subtract days in current date ***/
    public String subtractMonth(int month) {

        String newDate = "";

        try {

            Calendar calender = Calendar.getInstance();
            calender.add(Calendar.MONTH, -month);
            Date date = new Date();
            date.setTime(calender.getTimeInMillis());
            @SuppressLint("SimpleDateFormat") SimpleDateFormat postFormatter = new SimpleDateFormat("dd-MM-yyyy");
            newDate = postFormatter.format(date);
            return newDate;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return newDate;
    }

    /*** Method to subtract days in current date ***/
    public String subtractYear(int year) {

        String newDate = "";

        try {

            Calendar calender = Calendar.getInstance();
            calender.add(Calendar.YEAR, -year);
            Date date = new Date();
            date.setTime(calender.getTimeInMillis());
            @SuppressLint("SimpleDateFormat") SimpleDateFormat postFormatter = new SimpleDateFormat("dd-MM-yyyy");
            newDate = postFormatter.format(date);
            return newDate;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return newDate;
    }

    /* **************************  Select date for birthdate from Datepicker Dialog *************************************** */
    public void selectDateFromDatePickerDialog(final EditText editText, final String myFormat) {
        try {
            if (editText != null && mContext != null) {
                final Calendar myCalendar = Calendar.getInstance();

                DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        // TODO Auto-generated method stub
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, monthOfYear);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                        editText.setText(sdf.format(myCalendar.getTime()));
                    }
                };

                if (!TextUtils.isEmpty(editText.getText().toString().trim())) {

                    Calendar cal = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);
                    cal.setTime(sdf.parse(editText.getText().toString().trim()));// all done

                    new DatePickerDialog(mContext,
                            date,
                            cal.get(Calendar.YEAR),
                            cal.get(Calendar.MONTH),
                            cal.get(Calendar.DAY_OF_MONTH)).
                            show();

                } else {
                    new DatePickerDialog(mContext, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /*
     * Method to get authentication
     *
     * */
    public void getAuthentication(String userName, String password) {

        try {

            startProgressDialog((Activity) mContext, "", false);

            WebServicesAPI webServicesAPI = RetrofitAPIClient.getClient(BaseActivity.this).create(WebServicesAPI.class);

            Call<UserLoginResponse> call = webServicesAPI.getUserDetails(userName, password);

            call.enqueue(new Callback<UserLoginResponse>() {

                @Override
                public void onResponse(@NonNull Call<UserLoginResponse> call, @NonNull Response<UserLoginResponse> response) {

                    stopProgressDialog();

                    if (response.isSuccessful()) {

                        UserLoginResponse userLoginResponse = response.body();

                        assert userLoginResponse != null;
                        if (userLoginResponse.getStatus().equalsIgnoreCase("1")) {

                            String companyId = String.valueOf(userLoginResponse.getObj().getCompanyid());
                            String branchId = String.valueOf(userLoginResponse.getObj().getBranchid());
                            String userId = String.valueOf(userLoginResponse.getObj().getId());
                            String firstName = String.valueOf(userLoginResponse.getObj().getFirstname());
                            String lastName = String.valueOf(userLoginResponse.getObj().getLastname());
                            String appRights = String.valueOf(userLoginResponse.getObj().getNotes());

                            AppPreference.getInstance().setStringPreference(mContext, getResources().getString(R.string.pref_user_name), userName);
                            AppPreference.getInstance().setStringPreference(mContext, getResources().getString(R.string.pref_password), password);
                            AppPreference.getInstance().setStringPreference(mContext, getResources().getString(R.string.pref_company_id), companyId);
                            AppPreference.getInstance().setStringPreference(mContext, getResources().getString(R.string.pref_branch_id), branchId);
                            AppPreference.getInstance().setStringPreference(mContext, getResources().getString(R.string.pref_user_id), userId);
                            AppPreference.getInstance().setStringPreference(mContext, getResources().getString(R.string.pref_firstname), firstName);
                            AppPreference.getInstance().setStringPreference(mContext, getResources().getString(R.string.pref_lastname), lastName);
                            AppPreference.getInstance().setStringPreference(mContext, getResources().getString(R.string.pref_app_rights), appRights);

                            navigateToDashboard(userLoginResponse.getObj());

                        } else {
                            showCustomToast(mContext, "Authentication Failed. Check your username & password.");
                        }

                    } else {
                        Log.v(TAG, response.errorBody().toString());
                        showCustomToast(mContext, "Something went wrong!");
                    }
                }

                @Override
                public void onFailure(Call<UserLoginResponse> call, Throwable t) {

                    stopProgressDialog();

                    GlobalAlertDialog(mContext, "Whoops!", "Something went wrong. Please check your username and  password", false);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            Log.v(TAG, e.getLocalizedMessage());

            stopProgressDialog();
        }
    }

    /*
     * Method to navigate on Dashboard activity
     *
     * */
    private void navigateToDashboard(UserLoginResponseObject userLoginResponseObject) {

        if (BaseActivity.MyConnectivityChecker.isConnected(mContext)) {

            Intent mIntent = new Intent(mContext, DashboardActivity.class);
            mIntent.putExtra(getResources().getString(R.string.const_login_object), userLoginResponseObject);
            startActivity(mIntent);
            finish();
        } else {

            displayInternetToastMessage(mContext);
        }
    }
}
