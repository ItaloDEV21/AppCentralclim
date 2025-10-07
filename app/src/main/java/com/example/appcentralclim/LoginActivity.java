package com.example.appcentralclim;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appcentralclim.Telas.AdminActivity;
import com.example.appcentralclim.Telas.FuncionarioActivity;
import com.example.appcentralclim.api.ApiClient;
import com.example.appcentralclim.api.ApiService;
import com.example.appcentralclim.model.LoginRequest;
import com.example.appcentralclim.model.LoginResponse;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "CentralClimSession";
    private static final String KEY_EMAIL = "user_email";
    private static final String KEY_PERFIL = "user_perfil";
    private static final String KEY_NOME = "user_nome";
    private static final String KEY_TOKEN = "user_token";

    private TextInputEditText inputEmail, inputPassword;
    private Button btnLogin;
    private ProgressBar progressBar;

    private ApiService api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Se já estiver logado
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        if (prefs.getString(KEY_EMAIL, null) != null) {
            redirecionarUsuario(prefs.getString(KEY_PERFIL, ""), prefs.getString(KEY_NOME, ""));
            return;
        }

        inputEmail = findViewById(R.id.input_email);
        inputPassword = findViewById(R.id.input_password);
        btnLogin = findViewById(R.id.btn_auth_action);
        progressBar = findViewById(R.id.progress_bar);

        api = ApiClient.getClient().create(ApiService.class);

        btnLogin.setOnClickListener(v -> fazerLogin());
    }

    private void fazerLogin() {
        String email = inputEmail.getText().toString().trim();
        String senha = inputPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            inputEmail.setError("Digite o e-mail");
            return;
        }
        if (TextUtils.isEmpty(senha)) {
            inputPassword.setError("Digite a senha");
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        btnLogin.setEnabled(false);

        LoginRequest request = new LoginRequest(email, senha);

        api.login(request).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                progressBar.setVisibility(View.GONE);
                btnLogin.setEnabled(true);

                if (response.isSuccessful() && response.body() != null) {
                    LoginResponse user = response.body();

                    // Salvar sessão
                    SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                    prefs.edit()
                            .putString(KEY_EMAIL, email)
                            .putString(KEY_NOME, user.getNome())
                            .putString(KEY_PERFIL, user.getPerfil())
                            .putString(KEY_TOKEN, user.getToken())
                            .apply();

                    Toast.makeText(LoginActivity.this, "Bem-vindo " + user.getNome(), Toast.LENGTH_SHORT).show();

                    redirecionarUsuario(user.getPerfil(), user.getNome());

                } else {
                    Toast.makeText(LoginActivity.this, "E-mail ou senha inválidos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                btnLogin.setEnabled(true);
                Toast.makeText(LoginActivity.this, "Erro: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void redirecionarUsuario(String perfil, String nome) {
        Intent intent;
        if ("ADMIN".equalsIgnoreCase(perfil)) {
            intent = new Intent(this, AdminActivity.class);
        } else if ("FUNCIONARIO".equalsIgnoreCase(perfil)) {
            intent = new Intent(this, FuncionarioActivity.class);
            intent.putExtra(FuncionarioActivity.EXTRA_FILTRO_FUNCIONARIO, nome);
        } else {
            Toast.makeText(this, "Perfil inválido", Toast.LENGTH_SHORT).show();
            return;
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
