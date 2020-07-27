package com.application.kreditimpian.SaranKomplain;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.application.kreditimpian.Api.SharedPrefManager;
import com.application.kreditimpian.GantidanRisetPassword.GantiPassword;
import com.application.kreditimpian.R;

import java.util.ArrayList;
import java.util.List;

public class SarandanKomplain extends AppCompatActivity {
    SharedPrefManager sharedPrefManager;
    TextView txtEmail, txtBody;
    Spinner spinnerPilih;
    LinearLayout linearnomorinvoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sarandan_komplain);

        sharedPrefManager = new SharedPrefManager(SarandanKomplain.this);
        setActionBarTitle("Saran dan Komplain");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);// set drawable icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtEmail = findViewById(R.id.txtEmail);
        txtBody = findViewById(R.id.txtBody);
        spinnerPilih = findViewById(R.id.spinnerPilih);
        linearnomorinvoice = findViewById(R.id.linearnomorinvoice);
        String email = sharedPrefManager.getSPEmail();
        txtEmail.setText(email);

        List<String> Pilihan = new ArrayList<String>();  /*List Status Pilihan*/
        Pilihan.add(0, "= Pilih =");
        Pilihan.add("Saran");
        Pilihan.add("Komplain");

        ArrayAdapter<String> adapterKomplain = new ArrayAdapter<String>(SarandanKomplain.this, android.R.layout.simple_spinner_item, Pilihan);
        adapterKomplain.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerPilih.setAdapter(adapterKomplain);
        spinnerPilih.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String Komplain = adapterKomplain.getItem(position);
                if (Komplain.equals("Komplain")) {
                    linearnomorinvoice.setVisibility(View.VISIBLE);
                }else{
                    linearnomorinvoice.setVisibility(View.GONE);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


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
    @Override
    public void onBackPressed() {
        finish();
    }
}
