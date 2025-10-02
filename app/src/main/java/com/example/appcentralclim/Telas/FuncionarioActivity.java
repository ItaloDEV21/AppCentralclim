package com.example.appcentralclim.Telas;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.example.appcentralclim.R;

public class FuncionarioActivity extends AppCompatActivity {

    private Button btnMeusServicos;
    private Button btnRelatorioServicos;
    private Button btnLogout;

    // VARIÁVEL CHAVE: Simula o funcionário que está logado
    private final String FUNCIONARIO_LOGADO = "João da Silva";
    // OBS: Em um sistema real, você obteria esta informação (Nome/ID) da sessão de login.

    // Chave para passar o filtro para a RelatoriosActivity
    public static final String EXTRA_FILTRO_FUNCIONARIO = "filtro_funcionario";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_funcionario);

        // 1. Configuração da Toolbar (Botão de Voltar)
        Toolbar toolbar = findViewById(R.id.toolbar_funcionario);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Habilita o botão de voltar
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(""); // O título já está no TextView do layout
        }

        // 2. Mapeamento dos Componentes
        btnMeusServicos = findViewById(R.id.btn_meus_servicos);
        btnRelatorioServicos = findViewById(R.id.btn_relatorio_servicos);
        btnLogout = findViewById(R.id.btn_logout_func);

        // 3. Ações dos Botões

        // A. Meus Serviços (Filtro Personalizado)
        btnMeusServicos.setOnClickListener(v -> {
            Intent intent = new Intent(FuncionarioActivity.this, RelatoriosActivity.class);
            // IMPORTANTE: Envia o nome/ID do funcionário logado como filtro
            intent.putExtra(EXTRA_FILTRO_FUNCIONARIO, FUNCIONARIO_LOGADO);
            startActivity(intent);
        });

        // B. Relatório Geral (Sem Filtro)
        btnRelatorioServicos.setOnClickListener(v -> {
            // Abre a tela de relatórios sem filtro
            Intent intent = new Intent(FuncionarioActivity.this, RelatoriosActivity.class);
            startActivity(intent);
        });

        // C. Logout (Apenas simulação)
        btnLogout.setOnClickListener(v -> {
            Toast.makeText(this, FUNCIONARIO_LOGADO + " deslogado.", Toast.LENGTH_SHORT).show();
            // Retorna para a tela de Login (ou a Main/Inicial)
            finish();
        });
    }

    // Método para lidar com o clique no botão de voltar da toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed(); // Volta para a tela anterior
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}