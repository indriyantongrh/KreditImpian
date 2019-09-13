package com.example.kreditimpian;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.kreditimpian.LoginRegister.LoginUser;
import com.example.kreditimpian.LoginRegister.Register;

public class SplashScreenDepan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen_depan);

        Thread timerThread = new Thread(){
          public void run(){
              try{
                  sleep(3000);;
              }catch (InterruptedException e){
                  e.printStackTrace();
              }finally {
                  Intent intent = new Intent(SplashScreenDepan.this, LoginUser.class);
                  startActivity(intent);
              }

          }

        };
        timerThread.start();

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
