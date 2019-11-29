package com.application.kreditimpian.Api.api;

import android.content.Context;
import android.content.SharedPreferences;



public class SharedPrefManager {

    public static final String SP_Kreditimpian = "KreditimpianApp";

    public static final String SP_ID = "id";
    public static final String SP_EMAIL = "email";

    public static final String SP_SUDAH_LOGIN = "spSudahLogin";

    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    public SharedPrefManager(Context context){
        sp = context.getSharedPreferences(SP_Kreditimpian, Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }

    public void saveSPString(String keySP, String value){
        spEditor.putString(keySP, value);
        spEditor.commit();
    }

    public void saveSPInt(String keySP, int value){
        spEditor.putInt(keySP, value);
        spEditor.commit();
    }

    public void saveSPBoolean(String keySP, boolean value){
        spEditor.putBoolean(keySP, value);
        spEditor.commit();
    }

    public String getSPID(){
        return sp.getString(SP_ID, "ID member not found");
    }

    public String getSPEmail(){
        return sp.getString(SP_EMAIL, "Email member not found");
    }

    public Boolean getSPSudahLogin(){
        return sp.getBoolean(SP_SUDAH_LOGIN, false);
    }
}
