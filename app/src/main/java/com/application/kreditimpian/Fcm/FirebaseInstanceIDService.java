/*
package com.application.kreditimpian.Fcm;

import android.util.Log;


import com.application.kreditimpian.PushNotifikasi.FirebaseInstanceIdService;
import com.google.firebase.iid.FirebaseInstanceId;

*/
/**
 * Created by indriyanto Nugroho on 14 Jul 2020.
 *//*

public class FirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = "FirebaseIIDServiceDemo";
    public String[] name;

    @Override
    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance().getToken();
        //String token = "AAAAMoJsXe0:APA91bFTmxxyUFVmvahXXdOeaH6i5cdgLj7dQaRjsfFEqdF_PtXvf9X0_7uGsQTM975S20LIvfAM4xbcWwTj6osQw8t8FHn4jGllXt08z9g4nCtt0MultHf85mTe1H41V5EA59sa77rT";
        System.out.println("my firebase token " + token );
        onNewToken(token);
    }

    public void onNewToken(String token) {
        Log.d(TAG, "Refreshed token: " + token);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(token);
    }
    private void sendRegistrationToServer(String token) {

    }

}
*/
