package com.application.kreditimpian.Api;

import android.content.Context;
import android.content.SharedPreferences;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;


public class SharedPrefManager {

    public static final String SP_Kreditimpian = "kreditimpian_v2_demo";


    public static final String SP_MSISDNREGIS = "msisdn";
    public static final String SP_DECODE = "decode";
    public static final String SP_IDPROFILE = "id";
    public static final String SP_ID = "id";
    public static final String SP_ID_USER = "id_user";
    public static final String SP_ID_MEMBER = "id_member";
    public static final String SP_USER_USERNAME = "user_username";
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
        return sp.getString(SP_ID, "Not Found");
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

    public String  getSpDecode() {
        return sp.getString(SP_DECODE,"");
    }

    public String  getSpMsisdnregis() {
        return sp.getString(SP_MSISDNREGIS,"Kosong");
    }

    public String  getSpIdprofile() {
        return sp.getString(SP_IDPROFILE,"");
    }

    public String  getSpIdUser() {
        return sp.getString(SP_ID_USER,"");
    }

    public String  getSpIdMember() {
        return sp.getString(SP_ID_MEMBER,"");
    }

    public String  getSpUserUsername() {
        return sp.getString(SP_USER_USERNAME,"");
    }

    public Boolean getSPSudahLogin(){
        return sp.getBoolean(SP_SUDAH_LOGIN, false);
    }



}
