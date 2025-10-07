package com.example.appcentralclim.Telas;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.appcentralclim.R;
import com.example.appcentralclim.api.ApiClient;
import com.example.appcentralclim.api.ApiService;
import com.example.appcentralclim.model.Cliente;
import com.example.appcentralclim.model.Funcionario;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TelaServico extends AppCompatActivity {

    AutoCompleteTextView autoCompleteClient;
    TextInputEditText editTextDescription, editTextValue, editTextDate;
    AutoCompleteTextView autoCompleteEmployee;
    Button buttonAddService, linkNovoCliente;

    private List<Cliente> listaClientes = new ArrayList<>();
    private List<Funcionario> listaFuncionarios = new ArrayList<>();

    private ApiService apiService; // Retrofit

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_servico);

        apiService = ApiClient.getClient().create(ApiService.class);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Adicionar Serviço");
            actionBar.setHomeAsUpIndicator(R.drawable.botao_voltar);
        }

        autoCompleteClient = findViewById(R.id.autoCompleteClient);
        editTextDescription = findViewById(R.id.editTextDescription);
        editTextValue = findViewById(R.id.editTextValue);
        autoCompleteEmployee = findViewById(R.id.autoCompleteEmployee);
        editTextDate = findViewById(R.id.editTextDate);
        buttonAddService = findViewById(R.id.buttonAddService);
        linkNovoCliente = findViewById(R.id.linkNovoCliente);

        // Abrir o calendário ao clicar no campo de data
        editTextDate.setOnClickListener(v -> showDatePicker());

        // Buscar dados do servidor
        carregarClientes();
        carregarFuncionarios();

        linkNovoCliente.setOnClickListener(v -> {
            Intent intent = new Intent(TelaServico.this, CadastrarClienteActivity.class);
            startActivity(intent);
        });

        buttonAddService.setOnClickListener(v -> adicionarServico());
    }

    private void showDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        int ano = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        int dia = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePicker = new DatePickerDialog(
                this,
                (DatePicker view, int year, int month, int dayOfMonth) ->
                        editTextDate.setText(String.format("%02d/%02d/%d", dayOfMonth, month + 1, year)),
                ano, mes, dia
        );
        datePicker.show();
    }

    private void carregarClientes() {
        apiService.getClientes().enqueue(new Callback<List<Cliente>>() {
            @Override
            public void onResponse(Call<List<Cliente>> call, Response<List<Cliente>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listaClientes = response.body();
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(
                            TelaServico.this,
                            android.R.layout.simple_dropdown_item_1line,
                            converterNomesClientes(listaClientes)
                    );
                    autoCompleteClient.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Cliente>> call, Throwable t) {
                Toast.makeText(TelaServico.this, "Erro ao carregar clientes", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void carregarFuncionarios() {
        apiService.getFuncionarios().enqueue(new Callback<List<Funcionario>>() {
            @Override
            public void onResponse(Call<List<Funcionario>> call, Response<List<Funcionario>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listaFuncionarios = response.body();
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(
                            TelaServico.this,
                            android.R.layout.simple_dropdown_item_1line,
                            converterNomesFuncionarios(listaFuncionarios)
                    );
                    autoCompleteEmployee.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Funcionario>> call, Throwable t) {
                Toast.makeText(TelaServico.this, "Erro ao carregar funcionários", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private List<String> converterNomesClientes(List<Cliente> lista) {
        List<String> nomes = new ArrayList<>();
        for (Cliente c : lista) nomes.add(c.getNome());
        return nomes;
    }

    private List<String> converterNomesFuncionarios(List<Funcionario> lista) {
        List<String> nomes = new ArrayList<>();
        for (Funcionario f : lista) nomes.add(f.getNome());
        return nomes;
    }

    private void adicionarServico() {
        String cliente = autoCompleteClient.getText().toString();
        String descricao = editTextDescription.getText().toString();
        String valor = editTextValue.getText().toString();
        String funcionario = autoCompleteEmployee.getText().toString();
        String data = editTextDate.getText().toString();

        if (cliente.isEmpty() || descricao.isEmpty() || valor.isEmpty() || funcionario.isEmpty() || data.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, "Serviço adicionado com sucesso!", Toast.LENGTH_LONG).show();

        // Redirecionar
        Intent intent = new Intent(this, RelatoriosActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
