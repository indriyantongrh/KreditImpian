package com.application.kreditimpian.TransactionProcess;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.application.kreditimpian.R;

public class TransactionSelectTenor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_select_tenor);
        setActionBarTitle("Pilih tenor yang cocok");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);// set drawable icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void setActionBarTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
