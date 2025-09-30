package com.example.appcentralclim;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appcentralclim.Telas.AdminActivity;

public class MainActivity extends AppCompatActivity {

    // Views
    private EditText inputEmail, inputPassword;
    private Button btnAuthAction;
    private TextView toggleModeText;
    private ProgressBar progressBar;

    // --- NOVAS CREDENCIAIS DE TESTE (Admin) ---
    private static final String ADMIN_EMAIL = "admin@clima.com";
    private static final String ADMIN_PASSWORD = "admin123";

    // Sessão
    private static final String PREFS_NAME = "CentralClimSession";
    private static final String KEY_EMAIL = "user_email";

    // Estado atual da tela (login ou cadastro)
    private boolean isLoginMode = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Sua tela de login

        // Verifica se já está logado
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        if (prefs.getString(KEY_EMAIL, null) != null) {
            // Usuário já logado, vai direto para AdminActivity
            startActivity(new Intent(this, AdminActivity.class));
            finish();
        }

        // Mapeia componentes
        inputEmail = findViewById(R.id.input_email);
        inputPassword = findViewById(R.id.input_password);
        btnAuthAction = findViewById(R.id.btn_auth_action);
        toggleModeText = findViewById(R.id.text_toggle_mode);
        progressBar = findViewById(R.id.progress_bar);

        // Clique no botão de login/cadastro
        btnAuthAction.setOnClickListener(v -> handleAuth());

        // Alternar entre login/cadastro
        toggleModeText.setOnClickListener(v -> toggleAuthMode());
    }

    /**
     * Alterna o modo login/cadastro
     */
    private void toggleAuthMode() {
        isLoginMode = !isLoginMode;

        if (isLoginMode) {
            btnAuthAction.setText("ENTRAR");
            toggleModeText.setText("Não tem uma conta? Cadastre-se aqui.");
        } else {
            btnAuthAction.setText("CADASTRAR");
            toggleModeText.setText("Já tem uma conta? Faça login aqui.");
        }
    }

    /**
     * Valida os campos e simula login/cadastro
     */
    private void handleAuth() {
        final String email = inputEmail.getText().toString().trim();
        final String password = inputPassword.getText().toString().trim();

        // Validação básica
        if (TextUtils.isEmpty(email)) {
            inputEmail.setError("Digite um e-mail");
            return;
        }

        if (TextUtils.isEmpty(password) || password.length() < 6) {
            inputPassword.setError("A senha precisa ter no mínimo 6 caracteres");
            return;
        }

        // Mostrar progresso
        progressBar.setVisibility(View.VISIBLE);
        btnAuthAction.setEnabled(false);

        new Handler().postDelayed(() -> {
            progressBar.setVisibility(View.GONE);
            btnAuthAction.setEnabled(true);

            if (isLoginMode) {
                // --- LÓGICA DE TESTE DE LOGIN ---
                if (email.equals(ADMIN_EMAIL) && password.equals(ADMIN_PASSWORD)) {

                    // LOGIN BEM-SUCEDIDO (ADMIN)
                    Toast.makeText(MainActivity.this, "Login Admin realizado!", Toast.LENGTH_SHORT).show();

                    // Salva sessão
                    SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                    prefs.edit().putString(KEY_EMAIL, email).apply();

                    // Vai para a tela de Administração
                    Intent intent = new Intent(MainActivity.this, AdminActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);

                } else {
                    // LOGIN FALHOU
                    Toast.makeText(MainActivity.this, "Falha no Login: E-mail ou Senha incorretos.", Toast.LENGTH_SHORT).show();
                }

            } else {
                // --- LÓGICA DE SIMULAÇÃO DE CADASTRO (Mantida como estava) ---
                Toast.makeText(MainActivity.this, "Cadastro realizado!", Toast.LENGTH_SHORT).show();

                // Salva sessão (assumindo que o cadastro faz login automático)
                SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                prefs.edit().putString(KEY_EMAIL, email).apply();

                // Vai para tela de Administração após o cadastro
                Intent intent = new Intent(MainActivity.this, AdminActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }

        }, 2000); // Simula carregamento por 2s
    }
}