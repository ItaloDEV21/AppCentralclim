package com.example.appcentralclim.Telas;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar; // <-- IMPORTANTE: Importe a Toolbar correta

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

    // 1. Declaração dos componentes da interface (continua a mesma)
    AutoCompleteTextView autoCompleteClient;
    TextInputEditText editTextDescription;
    TextInputEditText editTextValue;
    AutoCompleteTextView autoCompleteEmployee;
    Button buttonAddService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_servico);

        // --- MODIFICADO: Configuração da Toolbar Customizada ---
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); // Define nossa toolbar como a action bar da tela

        // Pega a ActionBar que agora é a nossa Toolbar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true); // Habilita o botão de voltar
            actionBar.setTitle(""); // Define o título
            // Usamos o nome exato do seu ícone
            actionBar.setHomeAsUpIndicator(R.drawable.botao_voltar);
        }

        // 2. Conecte os componentes declarados com os IDs do XML
        autoCompleteClient = findViewById(R.id.autoCompleteClient);
        editTextDescription = findViewById(R.id.editTextDescription);
        editTextValue = findViewById(R.id.editTextValue);
        autoCompleteEmployee = findViewById(R.id.autoCompleteEmployee);
        buttonAddService = findViewById(R.id.buttonAddService);

        // --- Configuração dos Seletores ---
        setupClientSelector();
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

    // Método para configurar o seletor de clientes
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

    // Método para configurar o seletor de funcionários
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

    // Método para lidar com o clique no botão de voltar (continua o mesmo e funciona com a Toolbar)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed(); // Fecha a atividade atual e volta para a anterior
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}