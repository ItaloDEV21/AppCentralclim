package com.example.appcentralclim.Telas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.Toast;

import com.example.appcentralclim.LoginActivity;
import com.example.appcentralclim.R;
import com.example.appcentralclim.api.ApiClient;
import com.example.appcentralclim.api.UserService;
import com.example.appcentralclim.model.User;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroActivity extends AppCompatActivity {

    private TextInputEditText inputNome, inputCpf, inputEmail, inputPassword, inputConfirmPassword;
    private Button btnCadastrarConta;

    private UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        // 1. Mapear componentes
        inputNome = findViewById(R.id.input_nome);
        inputCpf = findViewById(R.id.input_cpf);
        inputEmail = findViewById(R.id.input_email);
        inputPassword = findViewById(R.id.input_password);
        inputConfirmPassword = findViewById(R.id.input_confirm_password);
        btnCadastrarConta = findViewById(R.id.btn_cadastrar_conta);

        // 2. Inicializar Retrofit
        userService = ApiClient.getRetrofitInstance().create(UserService.class);

        // 3. Botão de cadastro
        btnCadastrarConta.setOnClickListener(v -> handleCadastro());
    }

    private void handleCadastro() {
        String nome = inputNome.getText().toString().trim();
        String cpf = inputCpf.getText().toString().trim();
        String email = inputEmail.getText().toString().trim();
        String senha = inputPassword.getText().toString().trim();
        String confirmarSenha = inputConfirmPassword.getText().toString().trim();

        // Limpar erros
        inputNome.setError(null);
        inputCpf.setError(null);
        inputEmail.setError(null);
        inputPassword.setError(null);
        inputConfirmPassword.setError(null);

        // Validações básicas
        if (TextUtils.isEmpty(nome)) {
            inputNome.setError("Nome é obrigatório.");
            return;
        }
        if (TextUtils.isEmpty(cpf)) {
            inputCpf.setError("CPF é obrigatório.");
            return;
        }
        if (TextUtils.isEmpty(email)) {
            inputEmail.setError("E-mail é obrigatório.");
            return;
        }
        if (TextUtils.isEmpty(senha)) {
            inputPassword.setError("Senha é obrigatória.");
            return;
        }
        if (senha.length() < 6) {
            inputPassword.setError("A senha precisa ter no mínimo 6 caracteres.");
            return;
        }
        if (!senha.equals(confirmarSenha)) {
            inputConfirmPassword.setError("As senhas não coincidem.");
            return;
        }

        // Criar objeto User
        User user = new User(nome, cpf, email, senha);

        // Fazer requisição para API
        Call<User> call = userService.cadastrarUsuario(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(CadastroActivity.this,
                            "Usuário cadastrado com sucesso!",
                            Toast.LENGTH_LONG).show();

                    // Ir para MainActivity
                    Intent intent = new Intent(CadastroActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(CadastroActivity.this,
                            "Erro ao cadastrar. Código: " + response.code(),
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(CadastroActivity.this,
                        "Falha na conexão: " + t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}
