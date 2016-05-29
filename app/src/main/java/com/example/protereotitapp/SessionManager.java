package com.example.protereotitapp;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

public class SessionManager {
    // LogCat tag
    private static String TAG = SessionManager.class.getSimpleName();

    // Shared Preferences
    SharedPreferences pref;

    Editor editor;
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "protereotitapp";
    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";

    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String UNIQUE_ID = "unique_id";
    private static final String ID = "id";
    private static final String CREATED_AT = "created_at";
//    private static final String GCM_REGISTRATION_ID="GoogleCloudMessagingResgistrationId";

    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setLogin(boolean isLoggedIn) {

        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);
        // commit changes
        editor.commit();
        Log.d(TAG, "User login session modified!");
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }


    public void setNAME(String name) {
        editor.putString(NAME, name);
        // commit changes
        editor.commit();
    }

    public void setEMAIL(String email) {
        editor.putString(EMAIL, email);
        // commit changes
        editor.commit();
    }

    public void setUNIQUE_ID(String unique_id) {
        editor.putString(UNIQUE_ID, unique_id);
        // commit changes
        editor.commit();
    }


//    public void setID(String id) {
//        editor.putString(ID, id);
//        // commit changes
//        editor.commit();
//    }

    public void setCREATED_AT(String created_at) {
        editor.putString(CREATED_AT, created_at);
        // commit changes
        editor.commit();
    }

    public String getNAME() {
        return pref.getString(NAME, "User");
    }

    public String getEMAIL() {
        return pref.getString(EMAIL, "user@example.com");
    }

    public String getUNIQUE_ID() {
        return pref.getString(UNIQUE_ID, "00000.0000");
    }

//    public String getID() {
//        return pref.getString(ID, "0");
//    }

    public String getCREATED_AT() {
        return pref.getString(CREATED_AT, "1970-00-00 00:00");
    }

}