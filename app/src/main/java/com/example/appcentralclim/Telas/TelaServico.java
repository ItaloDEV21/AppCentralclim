package com.example.appcentralclim.Telas;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.appcentralclim.R;
import com.google.android.material.textfield.TextInputEditText;

// NOVOS IMPORTS
import com.example.appcentralclim.dao.MockDAO;
import com.example.appcentralclim.model.Cliente;
import com.example.appcentralclim.model.Funcionario;

import java.util.List;

public class TelaServico extends AppCompatActivity {

    // Declaração dos componentes da interface
    AutoCompleteTextView autoCompleteClient;
    TextInputEditText editTextDescription;
    TextInputEditText editTextValue;
    AutoCompleteTextView autoCompleteEmployee;
    Button buttonAddService;
    Button linkNovoCliente;

    // Listas para armazenar os objetos do banco de dados
    private List<Cliente> listaClientes;
    private List<Funcionario> listaFuncionarios;

    // Instância do DAO para simular o acesso ao banco
    private MockDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_servico);

        dao = new MockDAO();

        // Configuração da Toolbar Customizada
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Adicionar Serviço");
            actionBar.setHomeAsUpIndicator(R.drawable.botao_voltar);
        }

        // Conecte os componentes declarados com os IDs do XML
        autoCompleteClient = findViewById(R.id.autoCompleteClient);
        editTextDescription = findViewById(R.id.editTextDescription);
        editTextValue = findViewById(R.id.editTextValue);
        autoCompleteEmployee = findViewById(R.id.autoCompleteEmployee);
        buttonAddService = findViewById(R.id.buttonAddService);
        linkNovoCliente = findViewById(R.id.linkNovoCliente);

        // Configuração dos Seletores (AGORA BUSCA DO MOCK DAO)
        setupClientSelector();
        setupEmployeeSelector();

        // Ação de clique para o link de cadastro de cliente
        linkNovoCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaServico.this, CadastrarClienteActivity.class);
                startActivity(intent);
            }
        });

        // Ação do Botão "Adicionar Serviço"
        buttonAddService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adicionarServico();
            }
        });
    }

    // Método para configurar o seletor de clientes (AGORA BUSCA DO DAO)
    private void setupClientSelector() {
        // 1. Busca a lista de objetos Cliente no DAO
        listaClientes = dao.buscarClientes();

        // 2. Cria o Adapter usando a lista de objetos Cliente
        // O ArrayAdapter usa o método toString() de Cliente para exibir o nome.
        ArrayAdapter<Cliente> clientAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                listaClientes
        );
        autoCompleteClient.setAdapter(clientAdapter);
    }

    // Método para configurar o seletor de funcionários (AGORA BUSCA DO DAO)
    private void setupEmployeeSelector() {
        // 1. Busca a lista de objetos Funcionario no DAO
        listaFuncionarios = dao.buscarFuncionarios();

        // 2. Cria o Adapter usando a lista de objetos Funcionario
        // O ArrayAdapter usa o método toString() de Funcionario para exibir o nome.
        ArrayAdapter<Funcionario> employeeAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                listaFuncionarios
        );
        autoCompleteEmployee.setAdapter(employeeAdapter);
    }

    // LÓGICA DE ADICIONAR SERVIÇO COM REDIRECIONAMENTO
    private void adicionarServico() {
        String selectedClientName = autoCompleteClient.getText().toString();
        String description = editTextDescription.getText().toString();
        String value = editTextValue.getText().toString();
        String selectedEmployeeName = autoCompleteEmployee.getText().toString();

        if (selectedClientName.isEmpty() || description.trim().isEmpty() || value.trim().isEmpty() || selectedEmployeeName.isEmpty()) {
            Toast.makeText(TelaServico.this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show();
            return;
        }

        // --- Lógica para encontrar o ID do Cliente e Funcionário ---
        long clienteId = -1;
        for (Cliente cliente : listaClientes) {
            if (cliente.toString().equals(selectedClientName)) {
                clienteId = cliente.getId();
                break;
            }
        }

        long funcionarioId = -1;
        for (Funcionario funcionario : listaFuncionarios) {
            if (funcionario.toString().equals(selectedEmployeeName)) {
                funcionarioId = funcionario.getId();
                break;
            }
        }

        if (clienteId == -1 || funcionarioId == -1) {
            Toast.makeText(TelaServico.this, "Selecione um Cliente e Funcionário válidos da lista.", Toast.LENGTH_LONG).show();
            return;
        }

        // 1. Salva o serviço no banco de dados usando os IDs
        dao.salvarServico(selectedClientName, description, value, selectedEmployeeName);

        String successMessage = "Serviço de R$" + value + " (ID Cliente: " + clienteId + ") atribuído a " + selectedEmployeeName + " (ID Funcionário: " + funcionarioId + ").";
        Toast.makeText(TelaServico.this, successMessage, Toast.LENGTH_LONG).show();

        // Limpar campos após o sucesso
        autoCompleteClient.setText("");
        editTextDescription.setText("");
        editTextValue.setText("");
        autoCompleteEmployee.setText("");

        // --- REDIRECIONAMENTO ADICIONADO AQUI ---
        Intent intent = new Intent(this, RelatoriosActivity.class);
        startActivity(intent);
        // --- FIM DO REDIRECIONAMENTO ---
    }

    // Método para lidar com o clique no botão de voltar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}