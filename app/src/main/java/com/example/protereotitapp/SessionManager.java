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

    private static final String estimated_time = "estimated_time";
    private static final String average_time = "average_time";
    private static final String number = "number";
    private static final String unique_code = "unique_code";
    private static final String expiration_date = "expiration_date";
    private static final String service = "service";
    private static final String address = "address";
    private static final String place_id = "place_id";


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


    public void setID(String id) {
        editor.putString(ID, id);
        // commit changes
        editor.commit();
    }

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

    public String getID() {
        return pref.getString(ID, "0");
    }

    public String getCREATED_AT() {
        return pref.getString(CREATED_AT, "1970-00-00 00:00");
    }

    public void setTicket(String estimated_time, String average_time, String number, String unique_code, String expiration_date, String service, String address) {
        editor.putString(this.estimated_time, estimated_time);
//        editor.putString(this.average_time, average_time);
        editor.putString(this.number, number);
        editor.putString(this.unique_code, unique_code);
//        editor.putString(this.expiration_date, expiration_date);
        editor.putString(this.service, service);
        editor.putString(this.address, address);
        // commit changes
        editor.commit();
    }

    public String[] getTicket() {
        String[] array = new String[5];
        array[0] = pref.getString(this.estimated_time, "estimated_time");
//        array[1] = pref.getString(this.average_time, "average_time");
        array[1] = pref.getString(this.number, "number");
        array[2] = pref.getString(this.unique_code, "unique_code");
//        array[4] = pref.getString(this.expiration_date, "expiration_date");
        array[3] = pref.getString(this.service, "service");
        array[4] = pref.getString(this.address, "address");
        return array;
    }


    public void setPlaceId(String place_id) {
        editor.putString(this.place_id, place_id);
        editor.commit();
    }


    public String getPlaceId() {
        return pref.getString(this.place_id, "place_id");
    }

}