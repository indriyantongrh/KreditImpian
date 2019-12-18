package com.application.kreditimpian.Api;

import android.content.Context;
import android.content.SharedPreferences;



public class SharedPrefManager {

    public static final String SP_Kreditimpian = "kreditimpian_v2_demo";

    public static final String SP_ID = "id";
    public static final String SP_EMAIL = "email";
    public static final String SP_USERNAME = "username";
    public static final String SP_MSISDN ="msisdn";
    public static final String SP_TOKEN = "result";

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
        return sp.getString(SP_ID, "");
    }

    public String getSPEmail(){
        return sp.getString(SP_EMAIL, "");
    }

    public String getSpUsername(){
        return sp.getString(SP_USERNAME, "");
    }

    public String getSpMsisdn(){
        return sp.getString(SP_MSISDN, "");
    }

    public String getSPToken(){
        return sp.getString(SP_TOKEN, "");
    }

    public Boolean getSPSudahLogin(){
        return sp.getBoolean(SP_SUDAH_LOGIN, false);
    }
}
