package com.example.appcentralclim.Telas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.Toast;

import com.example.appcentralclim.MainActivity;
import com.example.appcentralclim.R;
import com.google.android.material.textfield.TextInputEditText;

public class CadastroActivity extends AppCompatActivity {

    private TextInputEditText inputNome, inputCpf, inputEmail, inputPassword, inputConfirmPassword;
    private Button btnCadastrarConta;

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

        // 2. Ação do Botão Cadastrar
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

        // --- 3. VALIDAÇÃO DOS CAMPOS ---

        // Validação básica de campos vazios
        if (TextUtils.isEmpty(nome)) {
            inputNome.setError("Nome é obrigatório.");
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

        // Validação de Tamanho da Senha
        if (senha.length() < 6) {
            inputPassword.setError("A senha precisa ter no mínimo 6 caracteres.");
            return;
        }

        // Validação da Confirmação de Senha
        if (!senha.equals(confirmarSenha)) {
            inputConfirmPassword.setError("As senhas não coincidem.");
            Toast.makeText(this, "As senhas devem ser iguais!", Toast.LENGTH_SHORT).show();
            return;
        }

        // --- 4. LÓGICA DE CADASTRO (SIMULAÇÃO) ---

        // Aqui você faria a chamada ao seu banco de dados ou Firebase para salvar o novo usuário

        Toast.makeText(this, "Usuário " + nome + " cadastrado com sucesso!", Toast.LENGTH_LONG).show();

        // --- 5. REDIRECIONAMENTO PARA A TELA PRINCIPAL ---

        // Redireciona para a MainActivity após o cadastro
        Intent intent = new Intent(this, MainActivity.class);
        // Opcional: Garante que o usuário não volte para a tela de cadastro
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}