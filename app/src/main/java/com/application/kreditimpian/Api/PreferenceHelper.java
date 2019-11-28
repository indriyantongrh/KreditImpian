package com.application.kreditimpian.Api;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceHelper {
    private final String ID = "id";
    private final String email = "email";
    private SharedPreferences app_prefs;
    private Context context;

    public PreferenceHelper(Context context) {
        app_prefs = context.getSharedPreferences("shared",
                Context.MODE_PRIVATE);
        this.context = context;
    }

    public void putIsLogin(boolean loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putBoolean(ID, loginorout);
        edit.commit();
    }
    public boolean getIsLogin() {
        return app_prefs.getBoolean(ID, false);
    }

    public void putEmail    (String loginorout) {
        SharedPreferences.Editor edit = app_prefs.edit();
        edit.putString(email, loginorout);
        edit.commit();
    }
    public String getEmail() {
        return app_prefs.getString(email, "");
    }


}
