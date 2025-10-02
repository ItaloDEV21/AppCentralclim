package com.example.appcentralclim.Telas;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences; // Import necessário
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.example.appcentralclim.MainActivity;
import com.example.appcentralclim.R;

public class AdminActivity extends AppCompatActivity {

    Toolbar toolbar;
    private Button btnLogout; // Declaração do novo botão

    // Constantes de sessão (As mesmas usadas na MainActivity)
    private static final String PREFS_NAME = "CentralClimSession";
    private static final String KEY_EMAIL = "user_email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        // --- Configuração da Toolbar com botão de voltar ---
        toolbar = findViewById(R.id.toolbar_admin);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Removemos o botão de voltar da Toolbar, pois o Logout fará essa função.
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setTitle("Painel de Administração");
        }

        // Conectar componentes do layout
        Button btnCriarCliente = findViewById(R.id.btn_criar_cliente);
        Button btnCriarServico = findViewById(R.id.btn_criar_servico);
        Button btnCriarFuncionario = findViewById(R.id.btn_criar_funcionario);
        Button btnRelatorios = findViewById(R.id.btn_relatorios);

        // Mapeia o novo botão de Sair
        btnLogout = findViewById(R.id.btn_logout_admin);

        // Ações de navegação (existentes)

        btnCriarCliente.setOnClickListener(v -> {
            Intent intent = new Intent(this, CadastrarClienteActivity.class);
            startActivity(intent);
        });

        btnCriarServico.setOnClickListener(v -> {
            Intent intent = new Intent(this, TelaServico.class);
            startActivity(intent);
        });

        btnCriarFuncionario.setOnClickListener(v -> {
            // Lógica para cadastrar funcionário
        });

        btnRelatorios.setOnClickListener(v -> {
            Intent intent = new Intent(this, RelatoriosActivity.class);
            startActivity(intent);
        });

        // --- NOVA AÇÃO: LOGOUT ---
        btnLogout.setOnClickListener(v -> performLogout());
    }

    // Método que realiza o logout
    private void performLogout() {
        // 1. Limpa a sessão salva no SharedPreferences
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        prefs.edit().remove(KEY_EMAIL).apply();

        Toast.makeText(this, "Sessão encerrada.", Toast.LENGTH_SHORT).show();

        // 2. Navega para a MainActivity e limpa a pilha de atividades
        Intent intent = new Intent(AdminActivity.this, MainActivity.class);

        // Estas flags garantem que o usuário não volte para esta tela usando o botão Voltar
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        startActivity(intent);
        finish(); // Finaliza a AdminActivity
    }

    // O método onOptionsItemSelected foi removido, pois o botão de logout
    // substitui a função de voltar para a tela de login.
}