package com.application.kreditimpian.LoadingDialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import com.application.kreditimpian.R;

/**
 * Created by indriyanto Nugroho on 11 Jun 2020.
 */
public class LoadingDialog {

    Activity activity;
    AlertDialog alertDialog;


    public LoadingDialog(Activity myActivity){
        activity = myActivity;

    }

    void startLoadingDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.loading_dialog, null));
        builder.setCancelable(true);

        alertDialog = builder.create();
        alertDialog.show();
    }

    void dismissLoadingDialog(){
        alertDialog.dismiss();
    }
}
