package com.example.appcentralclim.Telas;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.example.appcentralclim.R;

public class TelaServico extends AppCompatActivity {

    // 1. Declare os componentes da interface (MODIFICADOS E NOVOS)
    AutoCompleteTextView autoCompleteClient; // MODIFICADO: de TextInputEditText para AutoCompleteTextView
    TextInputEditText editTextDescription;
    TextInputEditText editTextValue; // NOVO: Campo para o valor
    AutoCompleteTextView autoCompleteEmployee;
    Button buttonAddService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_servico);

        // --- NOVO: Configuração do Botão Voltar (ActionBar) ---
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Adicionar Novo Serviço"); // Opcional: define um título para a tela
        }

        // 2. Conecte os componentes declarados com os IDs do XML
        autoCompleteClient = findViewById(R.id.autoCompleteClient); // MODIFICADO: Novo ID
        editTextDescription = findViewById(R.id.editTextDescription);
        editTextValue = findViewById(R.id.editTextValue); // NOVO: Conexão do campo de valor
        autoCompleteEmployee = findViewById(R.id.autoCompleteEmployee);
        buttonAddService = findViewById(R.id.buttonAddService);

        // --- NOVO: Configuração do Seletor de Clientes ---
        setupClientSelector();

        // --- Configuração do Seletor de Funcionários ---
        setupEmployeeSelector();

        // --- Ação do Botão ---
        buttonAddService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 7. Obtenha os valores dos campos
                String selectedClient = autoCompleteClient.getText().toString();
                String description = editTextDescription.getText().toString();
                String value = editTextValue.getText().toString();
                String selectedEmployee = autoCompleteEmployee.getText().toString();

                // 8. Valide se os campos não estão vazios
                if (selectedClient.isEmpty() || description.trim().isEmpty() || value.trim().isEmpty() || selectedEmployee.isEmpty()) {
                    Toast.makeText(TelaServico.this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show();
                } else {
                    // Lógica para salvar o serviço
                    String successMessage = "Serviço de R$" + value + " para '" + selectedClient + "' atribuído a '" + selectedEmployee + "'.";
                    Toast.makeText(TelaServico.this, successMessage, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    // NOVO: Método para configurar o seletor de clientes
    private void setupClientSelector() {
        String[] clientes = new String[] {
                "Empresa A",
                "Comércio B",
                "Residência C",
                "Consultório D"
        };
        ArrayAdapter<String> clientAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, clientes);
        autoCompleteClient.setAdapter(clientAdapter);
    }

    // MODIFICADO: Lógica do seletor de funcionários movida para um método próprio para organização
    private void setupEmployeeSelector() {
        String[] funcionarios = new String[] {
                "João da Silva",
                "Maria Oliveira",
                "Carlos Pereira",
                "Ana Souza"
        };
        ArrayAdapter<String> employeeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, funcionarios);
        autoCompleteEmployee.setAdapter(employeeAdapter);
    }

    // --- NOVO: Método para lidar com o clique no botão de voltar ---
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed(); // Fecha a atividade atual e volta para a anterior
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}