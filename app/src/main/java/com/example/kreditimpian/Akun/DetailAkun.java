package com.example.kreditimpian.Akun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.kreditimpian.R;

public class DetailAkun extends AppCompatActivity {

    CardView btndatapribadi, btnalamatpengiriman, btnalamatkantor, btndatasaudara,btnuploaddokumen ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_akun);

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
}
