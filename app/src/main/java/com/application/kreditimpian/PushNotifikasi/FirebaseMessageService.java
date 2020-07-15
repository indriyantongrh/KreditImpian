package com.application.kreditimpian.PushNotifikasi;

import android.content.Intent;
import android.util.Log;

import com.application.kreditimpian.MenuUtama.MenuUtama;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by indriyanto Nugroho on 14 Jul 2020.
 */
public class FirebaseMessageService extends FirebaseMessagingService {
    private static final String TAG = "FirebaseMsgService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage.getData().size() > 0) {
            Log.e(TAG, "Data Payload: " + remoteMessage.getData().toString());
            try {
                JSONObject json = new JSONObject(remoteMessage.getData().toString());
                sendPushNotification(json);
            } catch (Exception e) {
                Log.e(TAG, "Exception: " + e.getMessage());
            }
        }
        /// super.onMessageReceived(remoteMessage);
    }
    //firebase cloud messaging
    private void sendPushNotification(JSONObject json) {
        //optionally we can display the json into log


        try {
            //getting the json data
            JSONObject data = json.getJSONObject("data");

            //parsing json data
            String title = data.getString("title");
            String message = data.getString("message");
            String imageUrl = data.getString("image");

            //creating MyNotificationManager object
            MyNotificationManager mNotificationManager = new MyNotificationManager(getApplicationContext());

            //creating an intent for the notification
//            Intent intent = new Intent(getApplicationContext(), DetailAkun.class);
//            intent.putExtra("id_user", id_user);

            Intent intent = new Intent(getApplicationContext(), MenuUtama.class);


            //if there is no image
            if (imageUrl.equals("null")) {
                //displaying small notification
                mNotificationManager.showSmallNotification(title, message, intent);
            } else {
                //if there is an image
                //displaying a big notification
                mNotificationManager.showBigNotification(title, message, imageUrl, intent);
            }
        } catch (JSONException e) {
            Log.e(TAG, "Json Exception: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
    }

}

