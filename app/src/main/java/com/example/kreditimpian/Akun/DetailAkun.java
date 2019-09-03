package com.example.kreditimpian.Akun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.kreditimpian.R;

public class DetailAkun extends AppCompatActivity {

    CardView btndatapribadi;

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

    }
}
