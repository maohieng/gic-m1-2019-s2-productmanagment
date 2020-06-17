package com.example.sharedpreference_sample;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.Nullable;

/**
 * Utility class to use with SharedPreference.
 */
public final class SharedPrefs {

    private SharedPrefs() {
        //no instance
    }

    public static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(context.getString(R.string.sharedpref_sample_filename), Context.MODE_PRIVATE);
    }

    @Nullable
    public static String getString(Context context, String key) {
        return getSharedPreferences(context).getString(key, null);
    }

    public static void putString(Context context, String key, String value) {
        getSharedPreferences(context).edit().putString(key, value).apply();
    }

    /**
     * @return value associated to the key, or {@code false} if key doesn't exist.
     */
    public static boolean getBoolean(Context context, String key) {
        return getSharedPreferences(context).getBoolean(key, false);
    }

    public static void putBoolean(Context context, String key, boolean value) {
        getSharedPreferences(context).edit().putBoolean(key, value).apply();
    }

}
