package com.example.appcentralclim.Telas;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import com.example.appcentralclim.MainActivity; // Assumindo que o MainActivity está no pacote raiz
import com.example.appcentralclim.R;

public class AdminActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        // --- Configuração da Toolbar com botão de voltar ---
        toolbar = findViewById(R.id.toolbar_admin);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Painel de Administração");
            // Se você quiser usar o mesmo ícone que na CadastrarClienteActivity:
            // actionBar.setHomeAsUpIndicator(R.drawable.botao_voltar);
        }

        // Conectar componentes do layout (os botões)
        Button btnCriarCliente = findViewById(R.id.btn_criar_cliente);
        Button btnCriarServico = findViewById(R.id.btn_criar_servico);
        Button btnCriarFuncionario = findViewById(R.id.btn_criar_funcionario);
        Button btnRelatorios = findViewById(R.id.btn_relatorios);

        // Ações de navegação

        // 1. Botão Criar Cliente -> Navega para a CadastrarClienteActivity
        btnCriarCliente.setOnClickListener(v -> {
            Intent intent = new Intent(this, CadastrarClienteActivity.class);
            startActivity(intent);
        });

        // 2. Botão Criar Serviço -> Navega para a CadastrarServicoActivity
        // NOTA: Você deve ter criado a classe CadastrarServicoActivity para isso funcionar.
        btnCriarServico.setOnClickListener(v -> {
            Intent intent = new Intent(this, TelaServico.class);
            startActivity(intent);
        });

        // 3. Botão Criar Funcionário (Ainda precisa criar a activity)
        btnCriarFuncionario.setOnClickListener(v -> {
            // Intenção para a próxima tela de cadastro de funcionário
            // Intent intent = new Intent(this, CadastrarFuncionarioActivity.class);
            // startActivity(intent);
        });

        // 4. Botão Relatórios (Ainda precisa criar a activity)
        btnRelatorios.setOnClickListener(v -> {
            // Intenção para a próxima tela de relatórios
            // Intent intent = new Intent(this, RelatoriosActivity.class);
            // startActivity(intent);
        });
    }

    // Método para lidar com o clique no botão de voltar da toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // android.R.id.home é o ID padrão do botão de navegação na action bar
        if (item.getItemId() == android.R.id.home) {
            // Ação de voltar para a MainActivity
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}