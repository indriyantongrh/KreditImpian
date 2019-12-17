package com.application.kreditimpian.DecodeUtils;

import android.util.Base64;
import android.util.Log;

import java.io.UnsupportedEncodingException;

/**
 * Created by indriyanto Nugroho on 16 Des 2019.
 */
public class JWTUtils {

//    public static void decodeJWT(String EncodeString) throws Exception
//    {
//
//        String[]  splitstr = EncodeString.split("\\.");
//        Log.d("","Header "+ getJSon(  splitstr[0]));
//        Log.d("","Payload " +getJSon( splitstr[1]));
//
//    }
//
//    public static  String getJSon(String EncodeString) throws UnsupportedEncodingException
//    {
//        byte[] decodebyte = Base64.decode(EncodeString, Base64.URL_SAFE);
//        return new String(decodebyte, "UTF-8");
//
//    }


    public static void decoded(String JWTEncoded) throws Exception {
        try {
            String[] split = JWTEncoded.split("\\.");
            Log.d("JWT_DECODED", "Header: " + getJson(split[0]));
            Log.d("JWT_DECODED", "Body: " + getJson(split[1]));
        } catch (UnsupportedEncodingException e) {
            //Error
        }
    }

    private static String getJson(String strEncoded) throws UnsupportedEncodingException{
        byte[] decodedBytes = Base64.decode(strEncoded, Base64.URL_SAFE);
        return new String(decodedBytes, "UTF-8");
    }

}
