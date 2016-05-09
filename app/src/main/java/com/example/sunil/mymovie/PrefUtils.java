package com.example.sunil.mymovie;

import android.content.Context;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PrefUtils {
	
	public static final String PREFS_LOGIN_PRIVACY_KEY = "PRIVACY";
	public static final String PREFS_COMPETITION_KEY = "COMPATITION";

//
	public static void saveToPrefs(Context context, String key, String value) {
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(context);
		final SharedPreferences.Editor editor = prefs.edit();
		editor.putString(key, value);
		editor.commit();
	}

	public static String getFromPrefs(Context context, String key,
			String defaultValue) {
		SharedPreferences sharedPrefs = PreferenceManager
				.getDefaultSharedPreferences(context);
		try {
			return sharedPrefs.getString(key, defaultValue);
		} catch (Exception e) {
			e.printStackTrace();
			return defaultValue;
		}
	}
}
