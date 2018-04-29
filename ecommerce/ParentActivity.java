package com.company.ecommerce;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***************************************************************************************
 * class name :: ParentActivity
 * This is parent class of this project every activity of this project extends this class
 * It contain common  methods
 ***************************************************************************************/
public class ParentActivity extends AppCompatActivity implements View.OnClickListener {


    public DrawerLayout drawerLayout;
    public View fragment;
    private String TAG = "ParentActivity";
    private Context ctx;
    private boolean on_back_press;
    protected ImageView imgTopLeft;
    protected TextView txtTopCenter;


    /***************************************************************************************
     * getCurrentTime() is used to get if device is phone or tablet
     * @return current startJourneyTime
     ****************************************************************************************/
    public static String getCurrentDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return simpleDateFormat.format(date);

    }//getCurrentTime

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


       /* ExceptionHandler.register(this, StringConstant.CRASH_UL);
        ExceptionHandler.submitStackTraces();*/
        ctx = this;
        on_back_press = false;

       /* if (!on_back_press) {
            overridePendingTransition(R.anim.left, R.anim.right);
            on_back_press = false;
        }*/


    }//end of onCreate()

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseMemory();
    }//end of onDestroy

    // will set font awsome back mobileno

    //it will finish activity
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //onPreviousView();

    }//end of onBackPressed()

    //initialize objects
    void initObjects() {


    }

    //initialization of ui controls
    void initUIControls() {


    }

    public void setBackIcon() {

    }

    //register components for click listener
    void registerForListener() {

    }

    //set top bar components
    void setTopBarComponents() {
    }

    /***
     * used when whit bordered button is to be se in top bor
     *
     * @param buttonText --> text which is to be set
     */
    public void setTopBarWithRoundButton(String buttonText) {

    } // end of setTopBarWithRoundButton()

    //set data to ui
    void setUIData() {
    }

    //get data from intent
    void getIntentData() {

    }

    //set list adapter
    void setAdapter() {
    }

    public void noTransition() {
        overridePendingTransition(0, 0);
    }

    //it will take input as a string and check wether it is not null and  not empty and return true or false
    public boolean isStrNotNull(String string) {
        return string != null && !string.isEmpty();
    }//end of isStrNotNull()

    //this will call on backPress button and finish current activity
   /* void onPreviousView() {
        on_back_press = true;
        finish();
        overridePendingTransition(R.anim.back_anim_left, R.anim.back_anim_right);
    }//end of onPreviousView()*/

    //this function will display input message in a toast
    void showToast(String msg) {


        Toast.makeText(ParentActivity.this, msg, Toast.LENGTH_SHORT).show();
    }//end of showToast()


    //this method will release all memory of current activity
    private void releaseMemory() {
        System.runFinalization();
        Runtime.getRuntime().gc();
        System.gc();
    }//end of releaseMemory()

    //method will hide the soft input keyboard
    void hideSoftKeyBoard(Context context) {
        // Check if no view has focus:
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }//end of hideSoftKeyBoard()

    //method hide the soft input keyboard
    public void hideSoftKeyBoard(EditText editText, Context context) {
        // Check if no view has focus:
        if (editText != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
    }//end of hideSoftKeyBoard()

    public void showKeyBoard(EditText editText) {
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
    }


    //set the white_cursor position of editText to last(end of last line)
    public void setEditTextCursorPosition(EditText editText) {
        editText.setSelection(editText.getText().length());
    }//end of setEditTextCursorPosition()

    //hide title bar (status bar)
    public void hideTitle() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    //return current app package name
    public String getPackageName(Context context) {
        return context.getPackageName();
    }//end of getPackageName()

    //release progress dialog
    protected void releaseDialog(ProgressDialog pd) {
        if (pd != null) {
            pd.dismiss();
            pd = null;
        }
    }//end of releaseDialog()

    @Override
    public void onClick(View view) {

    }

    protected void initTopBarComponents() {
        imgTopLeft = findViewById(R.id.imgTopLeft);
        //txtTopCenter = findViewById(R.id.txtTopCenter);
    }


    /***
     * will decrease quantity on plus button click
     */
    public String getCurrentDataSql() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        //return simpleDateFormat.format(date);
        return simpleDateFormat.format(date);


    }   // end of decreaseQuantity


    /**
     * will capitalize first latter after space
     *
     * @param capString
     * @return
     */
    protected String getCapsWord(String capString) {
        StringBuffer capBuffer = new StringBuffer();
        Matcher capMatcher = Pattern.compile("([a-z])([a-z]*)", Pattern.CASE_INSENSITIVE).matcher(capString);
        while (capMatcher.find()) {
            capMatcher.appendReplacement(capBuffer, capMatcher.group(1).toUpperCase() + capMatcher.group(2).toLowerCase());
        }
        return capMatcher.appendTail(capBuffer).toString();
    }


    /**
     * will append 3 dots in textview android
     *
     * @param limit
     * @param text
     * @return
     */
    public String cutText(int limit, String text) {
        if (text.length() > limit) {
            return text.substring(0, limit - 3) + "...";
        } else {
            return text;
        }

    }   // end of cutText

    /**
     * will remove character from fix position
     *
     * @param strValue
     * @param index
     * @return
     */
    public String deleteCharAt(String strValue, int index) {
        return strValue.substring(0, index) + strValue.substring(index + 1);

    }

    /**
     * will set app screen as a full screen mode
     */
    void setFullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }   //end of setFullScreen


    /**
     * use for navigation
     *
     * @param activity ==> Name of activity
     * @param isFinish ==> Set value if want to finish activity or not
     */
    void navigateToActivity(Class activity, boolean isFinish) {
        Intent i = new Intent(this, activity);
        if (isFinish) {
            // i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();
        } else {
            startActivity(i);
        }

    }   //end of navigateToActivity


    /**
     * closeDrawer
     *
     * @param drawerLayout
     * @param view
     **/
    void closeDrawer(DrawerLayout drawerLayout, View view) {
        if (drawerLayout.isDrawerOpen(view)) {
            drawerLayout.closeDrawer(view);
        }
    }//end of closeDrawer()


    /**
     * closeDrawer
     *
     * @param drawerLayout
     * @param view
     **/
    void openDrawer(DrawerLayout drawerLayout, View view) {
        drawerLayout.openDrawer(view);

    }//end of closeDrawer()


    void setStatusBarColor(String color) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window w = getWindow();
            w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            w.setStatusBarColor(Color.parseColor(color));
        }
    }


    /**
     * will get device info
     *
     * @return
     */
    String getDeviceInfo() {
        String deviceInfo = "OS VERSION :" + Build.VERSION.RELEASE +
                ",DEVICE_MANUFACTURER : " + Build.MANUFACTURER
                + ",MODEL : " + Build.MODEL
                + ",SDK :" + Build.VERSION.SDK + ",Brand :" + Build.BRAND + ", current app version :: " + getVersionCode() +
                " Login time :: ";
        return deviceInfo.replace(" ", "");

    }   //end of getDeviceInfo


    /**
     * will get app version code
     *
     * @return
     */
    protected String getVersionCode() {
        PackageManager manager = getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(getPackageName(), 0);
            return info.versionName + "";
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return "1.0";
    }   //end of getVersionCode

    //this will call on backPress button and finish current activity
    void onPreviousView() {
        on_back_press = true;
        finish();
        overridePendingTransition(R.anim.back_anim_left, R.anim.back_anim_right);
    }//end of onPreviousView()
    @Override
    protected void onPause() {
        super.onPause();
        System.runFinalization();

        Runtime.getRuntime().gc();
        System.gc();
    }
}//end of ParentActivity class