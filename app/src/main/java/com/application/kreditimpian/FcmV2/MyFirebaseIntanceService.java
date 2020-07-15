package com.application.kreditimpian.FcmV2;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.application.kreditimpian.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;
import java.util.Random;

/**
 * Created by indriyanto Nugroho on 15 Jul 2020.
 */
public class MyFirebaseIntanceService extends FirebaseMessagingService {


    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        if(remoteMessage.getData().isEmpty()){
            showNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
        }else{
            showNotification(remoteMessage.getData());
        }

    }

    private void showNotification(Map<String, String> data){
        String title = data.get("title").toString();
        String body = data.get("body").toString();

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "application.kreditimpian.FcmV2.test";

        if(Build.VERSION.SDK_INT >=  Build.VERSION_CODES.O){

            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "Notification",
                    NotificationManager.IMPORTANCE_DEFAULT);

            notificationChannel.setDescription("Kreditimpian.id");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.BLUE);
            notificationChannel.enableLights(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
        notificationBuilder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.iconapps)
                .setContentTitle(title)
                .setContentText(body)
                .setContentInfo("Info");

        notificationManager.notify(new Random().nextInt(), notificationBuilder.build());

    }

    public void showNotification(String title, String body){


        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "application.kreditimpian.FcmV2.test";

        if(Build.VERSION.SDK_INT >=  Build.VERSION_CODES.O){

            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "Notification",
                    NotificationManager.IMPORTANCE_DEFAULT);

            notificationChannel.setDescription("Kreditimpian.id");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.BLUE);
            notificationChannel.enableLights(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
        notificationBuilder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.iconapps)
                .setContentTitle(title)
                .setContentText(body)
                .setContentInfo("Info");

        notificationManager.notify(new Random().nextInt(), notificationBuilder.build());
    }

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);

        Log.d("TOKENFIREBASE", s);
    }
}
