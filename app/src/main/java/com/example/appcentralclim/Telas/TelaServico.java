package com.example.appcentralclim.Telas; // Verifique se o nome do pacote está correto

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.example.appcentralclim.R; // <-- ESTA É A LINHA CORRETA

public class TelaServico extends AppCompatActivity {

    // 1. Declare os componentes da interface
    TextInputEditText editTextClientName;
    TextInputEditText editTextDescription;
    AutoCompleteTextView autoCompleteEmployee;
    Button buttonAddService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_servico);

        // 2. Conecte os componentes declarados com os IDs do XML
        editTextClientName = findViewById(R.id.editTextClientName);
        editTextDescription = findViewById(R.id.editTextDescription);
        autoCompleteEmployee = findViewById(R.id.autoCompleteEmployee);
        buttonAddService = findViewById(R.id.buttonAddService);

        // --- Configuração do Seletor de Funcionários ---

        // 3. Simulação da sua lista de funcionários (no futuro, isso virá do seu sistema)
        String[] funcionarios = new String[] {
                "João da Silva",
                "Maria Oliveira",
                "Carlos Pereira",
                "Ana Souza"
        };

        // 4. Crie um "Adaptador" para carregar a lista no componente de dropdown
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, // Contexto da Activity
                android.R.layout.simple_dropdown_item_1line, // Layout padrão para um item do dropdown
                funcionarios // Sua lista de dados
        );

        // 5. Defina o adaptador no componente AutoCompleteTextView
        autoCompleteEmployee.setAdapter(adapter);


        // --- Ação do Botão ---

        // 6. Crie um "ouvinte de cliques" para o botão
        buttonAddService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 7. Obtenha os valores digitados/selecionados nos campos
                String clientName = editTextClientName.getText().toString();
                String description = editTextDescription.getText().toString();
                String selectedEmployee = autoCompleteEmployee.getText().toString();

                // 8. Valide se os campos não estão vazios
                if (clientName.trim().isEmpty() || description.trim().isEmpty() || selectedEmployee.isEmpty()) {
                    Toast.makeText(TelaServico.this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show();
                } else {
                    // Lógica para salvar o serviço no sistema
                    String successMessage = "Serviço para '" + clientName + "' adicionado e atribuído a '" + selectedEmployee + "'.";
                    Toast.makeText(TelaServico.this, successMessage, Toast.LENGTH_LONG).show();

                    // Aqui você chamaria o método para salvar no banco de dados, por exemplo.
                }
            }
        });
    }
}