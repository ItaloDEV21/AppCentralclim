package com.example.appcentralclim.Telas;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.Toast;

import com.example.appcentralclim.R;
import com.example.appcentralclim.api.FuncionarioService;
import com.example.appcentralclim.model.Funcionario;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CadastrarFuncionarioActivity extends AppCompatActivity {

    private TextInputEditText inputNome, inputEmail, inputPassword;
    private Button btnCadastrarFuncionario;

    private FuncionarioService funcionarioService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_funcionario);

        // --- Toolbar com botão de voltar ---
        Toolbar toolbar = findViewById(R.id.toolbar_cadastrar_funcionario);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Cadastrar Funcionário");
        }

        // Conecta os campos
        inputNome = findViewById(R.id.input_nome);
        inputEmail = findViewById(R.id.input_email);
        inputPassword = findViewById(R.id.input_password);
        btnCadastrarFuncionario = findViewById(R.id.btn_cadastrar_funcionario);

        // Configura Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        funcionarioService = retrofit.create(FuncionarioService.class);

        // Ação do botão cadastrar
        btnCadastrarFuncionario.setOnClickListener(v -> cadastrarFuncionario());
    }

    // --- Método do botão de voltar ---
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private void cadastrarFuncionario() {
        String nome = inputNome.getText().toString().trim();
        String email = inputEmail.getText().toString().trim();
        String senha = inputPassword.getText().toString().trim();

        if (TextUtils.isEmpty(nome)) {
            inputNome.setError("Nome obrigatório");
            return;
        }

        if (TextUtils.isEmpty(email)) {
            inputEmail.setError("E-mail obrigatório");
            return;
        }

        if (TextUtils.isEmpty(senha) || senha.length() < 6) {
            inputPassword.setError("Senha deve ter ao menos 6 caracteres");
            return;
        }

        Funcionario funcionario = new Funcionario(nome, email, senha);

        funcionarioService.cadastrarFuncionario(funcionario).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(CadastrarFuncionarioActivity.this, "Funcionário cadastrado!", Toast.LENGTH_SHORT).show();
                    limparCampos();
                } else {
                    Toast.makeText(CadastrarFuncionarioActivity.this, "Erro ao cadastrar: " + response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(CadastrarFuncionarioActivity.this, "Erro de conexão: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void limparCampos() {
        inputNome.setText("");
        inputEmail.setText("");
        inputPassword.setText("");
    }
}
