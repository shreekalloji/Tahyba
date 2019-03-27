package com.iprismtech.tahyba.utilities;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SharedPrefsUtils {
    private Context context;

    private static SharedPreferences preferences;


    public static SharedPreferences.Editor editor;

    public static final String PREF_NAME = "myprefs";

    public static final String IS_USER_LOGIN = "IsUserLoggedIn";

    public static final String KEY_ID = "id";

    public static final String KEY_PHONE = "phone";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_NAME = "name";
    public static final String KEY_PASSCODE = "passcode";


    public static final String KEY_PASSWORD = "password";
    public static final String KEY_PROFILE_PIC = "profile_image";
    public static final String KEY_QRCODE = "qrcode";
    public static final String KEY_TOKEN = "token_id";
    public static final String KEY_LAT = "user_lat";
    public static final String KEY_LNG = "user_lng";

    //user type =1/2
    //residence =1      family member = 2


    private static SharedPrefsUtils prefsUtils;

    public static SharedPrefsUtils getInstance(Context context) {
        if (prefsUtils == null) {
            prefsUtils = new SharedPrefsUtils(context);
        }
        return prefsUtils;

    }

    public SharedPrefsUtils(Context context) {
        this.context = context;
        this.preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    //constructor

    public SharedPrefsUtils(Context context, SharedPreferences preferences) {
        this.context = context;
        this.preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }


    //Saving user details
    public void createUserSession(String id, String Phone, String email, String name) {
        editor.putBoolean(IS_USER_LOGIN, true);
        editor.putString(KEY_ID, id);
        editor.putString(KEY_PHONE, Phone);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_NAME, name);
        editor.commit();
    }


    public void storelatlangcode(String lat, String longi) {
        editor.putString(KEY_LAT, lat);
        editor.putString(KEY_LNG, longi);
        editor.commit();
    }

    public HashMap<String, String> getLangCode() {
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_LNG, preferences.getString(KEY_LNG, null));
        user.put(KEY_LAT, preferences.getString(KEY_LAT, null));
        return user;
    }


    public static void setString(String key, String value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static void updateString(String key, String value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }
    public static void changeUserLoginScreenStatus() {
        editor.putBoolean(IS_USER_LOGIN, false);

        editor.commit();
    }
    public static String getString(String key) {
        return preferences.getString(key, "");
    }

    public String getId() {
        return preferences.getString(KEY_ID, "");
    }

    public String getEmial() {
        return preferences.getString(KEY_EMAIL, "");
    }

    public String getLat() {
        return preferences.getString(KEY_LAT, "");
    }

    public String getLng() {
        return preferences.getString(KEY_LNG, "");
    }

    public String getName() {
        return preferences.getString(KEY_NAME, "");
    }

    public String getPhoneNumber() {
        return preferences.getString(KEY_PHONE, "");
    }

    public String getPasscode() {
        return preferences.getString(KEY_PASSWORD, "");
    }

    public boolean isUserLoggedIn() {
        return preferences.getBoolean(IS_USER_LOGIN, false);
    }

    public static void logoutUser() {
        changeUserLoginScreenStatus();
        editor.clear();
        editor.commit();
    }
}
