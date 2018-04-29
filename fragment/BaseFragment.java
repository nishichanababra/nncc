package com.company.fragment;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Created by Jay-Raj on 1/19/2018.
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    private String TAG = "BaseFragment";

    /***********************************************************************
     * getCurrentTime() it will return system time
     *
     * @return
     ***********************************************************************/
    public static String getCurrentTime() {
        // 2016-08-16 00:00:00
        // DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }// end of getCurrentTime()

    protected void initObjects() {
    }

    protected void initUIControls(View view) {
    }

    protected void registerForListener() {
    }

    protected void setUIData() {
    }

    protected void getIntentData() {
    }

    protected void setAdapter() {
    }

    protected void getBundle() {

    }

    @Override
    public void onClick(View view) {
    }//end of onClick

    @Override
    public void onDestroy() {
        super.onDestroy();
        //releaseMemory();
    }

    /**
     * this method will release all memory of current activity
     */
    public void releaseMemory() {
        System.runFinalization();
        Runtime.getRuntime().gc();
        System.gc();
    }//end of releaseMemory()

    /**
     * it will take input as a string and check weather it is not null and
     * not empty and return true oe false
     *
     * @param string
     * @return
     */
    protected boolean isStrNotNull(String string) {
        return string != null && !string.isEmpty();
    }//end of isStrNotNull()

    protected void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }


    protected void lifecycleMethodLog(String msg) {
        Log.d("fragment lifecycle ::", msg);
    }

    //on validation error
    protected void setError(EditText editText, String errorMsg) {
        editText.setError(errorMsg);
        editText.requestFocus();
    }

    /**
     * this method hide the soft input keyboard
     */
    protected void hideSoftKeyBoard(EditText editText, Context context) {
        // Check if no view has focus:
        if (editText != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
    }//end of hideSoftKeyBoard()

    /**
     * hide the soft input keyboard
     */
    protected void hideSoftKeyBoard(Context context) {
        // Check if no view has focus:
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }//end of hideSoftKeyBoard()

    //convert image to base64
    public String getBinaryData(String fileName) {
        String data = "";
        try {
            File file = new File(fileName);
            FileInputStream in = new FileInputStream(file);
            byte[] buffer = new byte[(int) file.length() + 100];
            int length = in.read(buffer);
            data = Base64.encodeToString(buffer, 0, length, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }//end of getBinaryData()

    //get proper string
    protected String getValidString(String name) {
        if (name.equalsIgnoreCase("null") || name.equalsIgnoreCase("empty") || name.contains("empty") || name.contains("null")) {
            return "";
        }
        return name;
    }//end of getValidString()

    //get proper string
    protected String getProperNumber(String number) {
        Double d = Double.valueOf(number);
        return d.intValue() + "";
    }//end of getValidString()

    /**********************************************************************************************
     * set the white_cursor position of editText to last(end of last line)
     *
     * @param editText
     ***********************************************************************************************/
    public void setEditTextCursorPosition(EditText editText) {
        editText.setSelection(editText.getText().length());
    }//end of setEditTextCursorPosition()

    protected void copyTextToClipBoard(String text) {
        ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("share code", text);
        clipboard.setPrimaryClip(clip);
        //showToast(text);
    }


    protected String properText(String str) {
        if (isStrNotNull(str)) {
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        }

        return str;
    }

    /**
     * will calculate days diffrence
     *
     * @param startDate
     * @param endDate
     */
    protected int getDaysDiffrence(String startDate, String endDate) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date date1 = null;
        Date date2 = null;
        try {
            date1 = df.parse(startDate);
            date2 = df.parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);

        int numberOfDays = 0;
        while (cal1.before(cal2)) {
            if ((Calendar.SATURDAY != cal1.get(Calendar.DAY_OF_WEEK))
                    && (Calendar.SUNDAY != cal1.get(Calendar.DAY_OF_WEEK))) {
                numberOfDays++;
            }
            cal1.add(Calendar.DATE, 1);
        }
        return numberOfDays;
    }   //end of getDaysDiffrence


    public void showKeyBoard(EditText editText) {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
    }


    public void showKeyBoard(Context context) {
        ((InputMethodManager) (context).getSystemService(Context.INPUT_METHOD_SERVICE)).toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }


    /**
     * will check if current device is pre lollipop device or not
     * Its used for ripple drawable
     *
     * @return
     */
    protected boolean isPreLollipop() {
        return android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.LOLLIPOP;
    }   //end of isPreLollipop


    protected String getUserName(String firstName, String lastName) {
        firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1, firstName.length()).toLowerCase();
        lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1, lastName.length()).toLowerCase();

        return firstName + " " + lastName;
    }


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
     * will get app version code
     *
     * @return
     */
    protected String getVersionCode() {
        PackageManager manager = getActivity().getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(getActivity().getPackageName(), 0);
            return info.versionName + "";
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return "1.0";
    }   //end of getVersionCode



}//end of class BaseFragment
