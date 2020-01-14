package com.application.kreditimpian.Akun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.application.kreditimpian.R;

public class DetailAkun extends AppCompatActivity {

    ImageButton btnback;

    CardView btndatapribadi, btnalamatpengiriman, btnalamatkantor, btndatasaudara, btnuploaddokumen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_akun);

        setActionBarTitle("Data Member");
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);// set drawable icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        btndatapribadi = findViewById(R.id.btndatapribadi);
        btndatapribadi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DataDiri.class);
                startActivity(intent);
            }
        });

        btnalamatpengiriman = findViewById(R.id.btnalamatpengiriman);
        btnalamatpengiriman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AlamatPengiriman.class);
                startActivity(intent);
            }
        });

        btnalamatkantor = findViewById(R.id.btnalamatkantor);
        btnalamatkantor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AlamatKantor.class);
                startActivity(intent);
            }
        });

        btndatasaudara = findViewById(R.id.btndatasaudara);
        btndatasaudara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DataSaudara.class);
                startActivity(intent);
            }
        });

        btnuploaddokumen = findViewById(R.id.btnuploaddokumen);
        btnuploaddokumen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UploadDokumenPendukung.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();

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
