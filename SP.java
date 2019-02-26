package com.mandaliyamedicals.medical.generalHelper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by nectarbits on 15/02/16.
 */
public class SP {

    public static final String SP_TRUE = "SP_TRUE";
    public static final String SP_FALSE = "SP_FALSE";
    public static final String LOGIN_STATUS = "LOGIN_STATUS";
    public static final String INTRO_SCREEN = "INTRO_SCREEN";
    public static final String USER_FIRST_NAME = "USER_FIRST_NAME";
    public static final String PROFILE_FIRST_NAME = "PROFILE_FIRST_NAME";
    public static final String USER_LAST_NAME = "USER_LAST_NAME";
    public static final String USER_MOBILE = "USER_MOBILE";
    public static final String USER_EMAIL = "USER_EMAIL";
    public static final String USER_PROFILE = "USER_PROFILE";
    public static final String USER_ID = "USER_ID";
    public static final String USER_DOB = "USER_DOB";
    public static final String USER_GENDER = "USER_GENDER";
    public static final String USER_AGE = "USER_AGE";
    public static final String CART_TOTAL = "CART_TOTAL";
    public static final String WISH_TOTAL = "WISH_TOTAL";
    public static final String USER_LAT = "USER_LAT";
    public static final String CALL_US = "CALL_US";
    public static final String COUNTRY_CODE = "COUNTRY_CODE";
    public static final String NOTIFICATION_STATUS = "NOTIFICATION_STATUS";
    public static final String NOTIFICATION_TYPE = "NOTIFICATION_TYPE";
    public static final String TOTAL_NOTIFICATION = "TOTAL_NOTIFICATION";

    public static final String USER_LONG = "USER_LONG";
    public static final String USER_PASSPORT = "USER_PASSPORT";
    public static final String USER_PROFESSIONAl = "USER_PROFESSIONAL";
    public static final String USER_DELIVERY_ADDRESS = "USER_DELIVERY_ADDRESS";
    public static final String USER_BILLING_ADDRESS = "USER_BILLING_ADDRESS";
    public static final String USER_DELIVERY_ADDRESS_ID = "USER_DELIVERY_ADDRESS_ID";
    public static final String USER_BILLING_ADDRESS_ID = "USER_BILLING_ADDRESS_ID";
    public static final String CITY_NAME = "CITY_NAME";
    public static final String CITY_ID = "CITY_ID";
    public static final String KEY_BUNDLE_PASS_LAB_TEST_AMT = "key_bundle_pass_lab_test_amt";
    public static final String SELECTED_LANGUAGE = "SELECTED_LANGUAGE";
    public static final String LANGUAGE_ENGLISH = "en";
    public static final String LANGUAGE_HINDI = "hi";
    /**
     * @param mContext
     * @param key
     * @param value
     */
    public static void savePreferences(Context mContext, String key, String value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value).apply();
    }

    /**
     * @param context
     * @param keyValue
     * @return
     */
    public static String getPreferences(Context context, String keyValue) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(keyValue, "");
    }

    /**
     * @param mContext
     */
    public static void removeAllSharedPreferences(Context mContext) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear().apply();
    }
}
