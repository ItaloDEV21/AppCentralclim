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

// IMPORTAÇÕES NECESSÁRIAS PARA O REDIRECIONAMENTO E DAO
import com.example.appcentralclim.Telas.AdminActivity;
import com.example.appcentralclim.Telas.FuncionarioActivity; // Assumindo que você criou esta classe
import com.example.appcentralclim.dao.MockDAO;
import com.example.appcentralclim.model.Usuario; // O modelo que criamos

public class MainActivity extends AppCompatActivity {

    // Views
    private EditText inputEmail, inputPassword;
    private Button btnAuthAction;
    private TextView toggleModeText;
    private ProgressBar progressBar;

    // Removemos ADMIN_EMAIL e ADMIN_PASSWORD pois a autenticação é feita pelo DAO

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
            // Usuário já logado, tenta pegar o perfil salvo e redirecionar (melhoria futura)
            // Por enquanto, apenas redireciona para AdminActivity (comportamento atual)
            startActivity(new Intent(this, AdminActivity.class));
            finish();
            return; // Termina o onCreate
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
     * Valida os campos e simula login/cadastro com base no perfil.
     */
    private void handleAuth() {
        final String email = inputEmail.getText().toString().trim();
        final String password = inputPassword.getText().toString().trim();

        // Validação básica
        if (TextUtils.isEmpty(email)) {
            inputEmail.setError("Digite um e-mail");
            return;
        }

        if (TextUtils.isEmpty(password) || password.length() < 3) { // Reduzi para 3 para o admin123
            inputPassword.setError("A senha precisa ter no mínimo 3 caracteres");
            return;
        }

        // Mostrar progresso
        progressBar.setVisibility(View.VISIBLE);
        btnAuthAction.setEnabled(false);

        new Handler().postDelayed(() -> {
            progressBar.setVisibility(View.GONE);
            btnAuthAction.setEnabled(true);

            if (isLoginMode) {

                // --- NOVA LÓGICA DE AUTENTICAÇÃO E REDIRECIONAMENTO ---
                MockDAO dao = new MockDAO();
                Usuario usuarioLogado = dao.autenticarUsuario(email, password);

                if (usuarioLogado != null) {

                    Toast.makeText(MainActivity.this, "Login efetuado: " + usuarioLogado.getTipoUsuario(), Toast.LENGTH_SHORT).show();

                    // Salva sessão (usando o email como identificador)
                    SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                    prefs.edit().putString(KEY_EMAIL, email).apply();

                    Intent intent;

                    if (usuarioLogado.getTipoUsuario().equals("ADMIN")) {
                        // Redireciona para o Painel Administrativo
                        intent = new Intent(MainActivity.this, AdminActivity.class);

                    } else if (usuarioLogado.getTipoUsuario().equals("FUNCIONARIO")) {
                        // Redireciona para o Painel do Funcionário
                        intent = new Intent(MainActivity.this, FuncionarioActivity.class);
                        // PASSAR O NOME É CRUCIAL PARA O FILTRO DE SERVIÇOS
                        intent.putExtra(FuncionarioActivity.EXTRA_FILTRO_FUNCIONARIO, usuarioLogado.getNome());

                    } else {
                        // Perfil inválido (teoricamente nunca acontece, mas é bom ter)
                        Toast.makeText(MainActivity.this, "Erro: Perfil desconhecido.", Toast.LENGTH_LONG).show();
                        return;
                    }

                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);

                } else {
                    // LOGIN FALHOU
                    Toast.makeText(MainActivity.this, "Falha no Login: E-mail ou Senha incorretos.", Toast.LENGTH_SHORT).show();
                }

            } else {
                // --- LÓGICA DE SIMULAÇÃO DE CADASTRO ---
                Toast.makeText(MainActivity.this, "Cadastro realizado! Faça login.", Toast.LENGTH_SHORT).show();
                // Após o cadastro, o ideal é ir para o login ou direto para uma tela padrão.
                toggleAuthMode(); // Volta para o modo Login
            }

        }, 2000); // Simula carregamento por 2s
    }
}