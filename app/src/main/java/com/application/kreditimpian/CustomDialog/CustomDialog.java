package com.application.kreditimpian.CustomDialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import com.application.kreditimpian.R;

/**
 * Created by indriyanto Nugroho on 5 Agu 2020.
 */
public class CustomDialog {

    private Activity activity;
    private AlertDialog dialog;

     CustomDialog(Activity mActivity){
        activity = mActivity;
    }

    void startLoadingDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.customdialog, null));
        builder.setCancelable(false);

        dialog= builder.create();
        dialog.show();
    }

    void dismissLoadingDialog(){
        dialog.dismiss();
    }


}
