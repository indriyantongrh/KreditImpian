package com.application.kreditimpian.Api;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.application.kreditimpian.LoginRegister.LoginUser;
import com.application.kreditimpian.MenuUtama.MenuUtama;

import java.util.HashMap;

public class SessionManager {

    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Context context;
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "LOGIN";
    private static final String LOGIN = "IS_LOGIN";
    private static final String ID = "id";
    private static final String USERNAME = "username";
    private static final String EMAIL = "email";


    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("LOGIN", PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void createSession(String id, String username, String email) {
        editor.putBoolean("LOGIN", true);
        editor.putString(ID, id);
        editor.putString(USERNAME, username);
        editor.putString(EMAIL, email);
        editor.apply();
    }

    public boolean isLoggin() {
        return sharedPreferences.getBoolean(LOGIN, false);

    }

    public void checkLogin() {
        if (!this.isLoggin()) {
            Intent intent = new Intent(context, LoginUser.class);
            context.startActivity(intent);
            ((MenuUtama) context).finish();

        }
    }

    public HashMap<String, String> getUserDetail() {
        HashMap<String, String> user = new HashMap<>();
        user.put(ID, sharedPreferences.getString(ID, "ID not found"));
        user.put(USERNAME, sharedPreferences.getString(USERNAME, "Username not found"));
        user.put(EMAIL, sharedPreferences.getString(EMAIL, "Email not found"));

        return user;
    }

    public void logout() {
        editor.clear();
        editor.commit();
        Intent intent = new Intent(context, LoginUser.class);
        context.startActivity(intent);
        ((MenuUtama) context).finish();


    }

}
