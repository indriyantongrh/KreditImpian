package com.application.kreditimpian.FormPengajuan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.application.kreditimpian.R;

public class SuccessMengajukan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_mengajukan);
        Button btnSelesai = findViewById(R.id.btnSelesai);
        btnSelesai.setOnClickListener(v -> finish());
    }
}
