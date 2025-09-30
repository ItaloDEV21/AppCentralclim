package com.example.appcentralclim.Telas;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.example.appcentralclim.R;
import com.google.android.material.textfield.TextInputEditText;

public class CadastrarClienteActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextInputEditText editTextNome, editTextEmail, editTextEndereco, editTextTelefone, editTextCpfCnpj;
    Button buttonCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_cliente);

        // Configuração da Toolbar com botão de voltar
        toolbar = findViewById(R.id.toolbar_cadastrar_cliente);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Cadastrar Novo Cliente");
            actionBar.setHomeAsUpIndicator(R.drawable.botao_voltar);
        }

        // Conectar componentes do layout
        editTextNome = findViewById(R.id.editTextNome);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextEndereco = findViewById(R.id.editTextEndereco);
        editTextTelefone = findViewById(R.id.editTextTelefone);
        editTextCpfCnpj = findViewById(R.id.editTextCpfCnpj);
        buttonCadastrar = findViewById(R.id.buttonCadastrar);

        // Ação do botão cadastrar
        buttonCadastrar.setOnClickListener(v -> {
            // Aqui você adicionaria a lógica para validar os campos e salvar no banco de dados
            String nome = editTextNome.getText().toString();
            if (nome.trim().isEmpty()) {
                Toast.makeText(this, "O campo Nome é obrigatório.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Cliente " + nome + " cadastrado com sucesso!", Toast.LENGTH_LONG).show();
                // Opcional: fechar a tela após o cadastro
                // finish();
            }
        });
    }

    // Método para lidar com o clique no botão de voltar da toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed(); // Fecha a tela atual e volta para a anterior (TelaServico)
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}