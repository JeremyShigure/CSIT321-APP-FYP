package com.example.aquafinaapp.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.prefs.Preferences;

public class preferences {

    static final String LOG_IN_STATUS = "LOG_IN_STATUS";
    static final String LOG_IN_USER = "LOG_IN_USER";


    public static void putLogInStatus(Context context, boolean status) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putBoolean(LOG_IN_STATUS, status).apply();
    }

    public static boolean getLogInStatus(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getBoolean(LOG_IN_STATUS, false);
    }

    public static void putLoggedInUser(Context context, String user) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putString(LOG_IN_USER, user).apply();
    }

    public static String getLoggedInUser(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(LOG_IN_USER, "");
    }

}
