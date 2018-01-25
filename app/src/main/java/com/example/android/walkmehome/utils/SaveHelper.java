package com.example.android.walkmehome.utils;
/*
 * Created by Gleb
 * TulaCo 
 * 1/25/2018
 */

import android.content.Context;
import android.content.SharedPreferences;

public class SaveHelper {
    private static final String PREF_NAME = "Prefs";
    private static final String PHONE_KEY = "phone";

    public static void savePhone(Context context, String phone) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(PHONE_KEY, phone);
        editor.apply();
    }

    public static String getPhone(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return sharedPref.getString(PHONE_KEY, "");
    }

    public static void clearPhone(Context context) {
        savePhone(context, "");
    }
}
