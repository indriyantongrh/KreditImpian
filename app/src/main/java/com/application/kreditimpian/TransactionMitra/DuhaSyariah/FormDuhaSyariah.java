package com.application.kreditimpian.TransactionMitra.DuhaSyariah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.application.kreditimpian.R;

public class FormDuhaSyariah extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_duha_syariah);

        Intent intent=getIntent();
        String id_member = intent.getStringExtra("id_member");

        Toast.makeText(this, "id member mu "+id_member, Toast.LENGTH_LONG).show();
    }
}