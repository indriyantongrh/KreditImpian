package com.application.kreditimpian.Api.ApiHeader;

import android.content.Context;
import android.util.Base64;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by indriyanto Nugroho on 7 Jan 2020.
 */
public class Utilss {

    public static String base64Encode(String username, String password) {
        String auth = username + ":" + password;
        return Base64.encodeToString(auth.getBytes(), Base64.NO_WRAP);
    }

    public static void hideSoftKey(View view) {
        if (view.getContext() != null) {
            InputMethodManager iml = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (iml != null) {
                iml.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }
}
