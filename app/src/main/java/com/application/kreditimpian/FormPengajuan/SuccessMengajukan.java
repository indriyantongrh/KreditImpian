package com.application.kreditimpian.FormPengajuan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.application.kreditimpian.MenuUtama.MenuUtama;
import com.application.kreditimpian.R;

public class SuccessMengajukan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_mengajukan);
        Button btnSelesai = findViewById(R.id.btnSelesai);
        btnSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SuccessMengajukan.this, MenuUtama.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
