package com.example.sunil.mymovie;

import android.content.Context;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PrefUtils {
	
	public static final String PREFS_LOGIN_PRIVACY_KEY = "PRIVACY";
	public static final String PREFS_COMPETITION_KEY = "COMPATITION";

	public static final String PREFS_LOGIN_UserIdServer_KEY = "__USERIDServer__";
	public static final String PREFS_LOGIN_EMAILServer_KEY = "__EMAILServer__";

	public static final String PREFS_LOGIN_CITYServer_KEY = "__CITYServer__";

	public static final String PREFS_LOGIN_CountryServer_KEY = "__CountryServer__";

	public static final String PREFS_ID_EVENT_KEY = "_Id_event_";

	public static final String PREFS_DEsc = "__DESC__";

	public static final String PREFS_LOGIN_SocialServer_KEY = "FacebookLogin";

	public static final String PREFS_LOGIN_USER_TYPE_KEY = "__USER_TYPE__";

	public static final String PREFS_DOCTER_TYPE_KEY = "__DOCTER_TYPE__";
	public static final String PREFS_PATIENT_TYPE_KEY = "__PATIENT_TYPE__";
	//
	public static final String Video_Id = "Schduled";


	public static final String PREFS_LOGIN_PATIENT_ID_KEY = "patient_id";
	
	
	// friend profile  
	
	
	

		public static final String PREFS_FRIEND_FB_TOKEN_KEY = "__FRIEND_FB_TOKEN_KEY__";
	

	// //
	// {"result":{"id":"12","username":"tt","email":"te@gmail.com","password":"289dff07669d7a23de0ef88d2f7129e7","age":"24 years","gender":"Male","country":"india","profile_image":"","cover_image":"","facebook_id":"","sport":"","longitude":"77.3022982","lattitude":"28.5442434","device_token":"e1c64b8d0b7f4c2","is_delete":"0","created":"2014-09-03 08:26:20","updated":"2014-09-03 08:26:20","left_foot":"0","right_foot":"0","both_foot":"0"}}

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
