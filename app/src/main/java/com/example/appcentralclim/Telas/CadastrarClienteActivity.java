package com.example.appcentralclim.Telas;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.example.appcentralclim.R;
import com.example.appcentralclim.api.ApiClient;
import com.example.appcentralclim.api.ApiService;
import com.example.appcentralclim.model.Cliente;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastrarClienteActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextInputEditText editTextNome, editTextEmail, editTextEndereco, editTextTelefone, editTextCpfCnpj;
    Button buttonCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_cliente);

        // Configuração da Toolbar
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
        buttonCadastrar = findViewById(R.id.buttonCadastrar);

        // Ação do botão cadastrar
        buttonCadastrar.setOnClickListener(v -> salvarCliente());
    }

    private void salvarCliente() {
        String nome = editTextNome.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String endereco = editTextEndereco.getText().toString().trim();
        String telefone = editTextTelefone.getText().toString().trim();

        if (nome.isEmpty() || email.isEmpty() || telefone.isEmpty()) {
            Toast.makeText(this, "Preencha os campos obrigatórios", Toast.LENGTH_SHORT).show();
            return;
        }

        // Criar objeto Cliente
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setEmail(email);
        cliente.setEndereco(endereco);
        cliente.setTelefone(telefone);

        // Chamar API
        ApiService api = ApiClient.getClient().create(ApiService.class);
        api.criarCliente(cliente).enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(CadastrarClienteActivity.this,
                            "Cliente cadastrado com sucesso!", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(CadastrarClienteActivity.this,
                            "Erro ao cadastrar cliente (código " + response.code() + ")", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Cliente> call, Throwable t) {
                Toast.makeText(CadastrarClienteActivity.this,
                        "Falha: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    // Botão de voltar na toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
