package com.example.appcentralclim.Telas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appcentralclim.MainActivity;
import com.example.appcentralclim.R;
import com.example.appcentralclim.Telas.TelaServico;

public class AdminActivity extends AppCompatActivity {

    private TextView welcomeText;
    private Button btnLogout, btnAddService;

    private static final String PREFS_NAME = "CentralClimSession";
    private static final String KEY_EMAIL = "user_email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        welcomeText = findViewById(R.id.welcome_text);
        btnLogout = findViewById(R.id.btn_logout);
        btnAddService = findViewById(R.id.btn_add_service);

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String email = prefs.getString(KEY_EMAIL, "Usuário");

        welcomeText.setText("Bem-vindo, " + email);

        btnLogout.setOnClickListener(v -> {
            prefs.edit().remove(KEY_EMAIL).apply();
            Toast.makeText(this, "Sessão encerrada", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });

        btnAddService.setOnClickListener(v -> {
            startActivity(new Intent(this, TelaServico.class));
        });
    }
}
