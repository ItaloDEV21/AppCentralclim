package com.example.appcentralclim.Telas;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appcentralclim.R;
import com.example.appcentralclim.adapter.ServicoAdapter;
import com.example.appcentralclim.api.ApiClient;
import com.example.appcentralclim.api.ApiService;
import com.example.appcentralclim.model.Servico;
import com.example.appcentralclim.request.AtualizarRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TelaServico extends AppCompatActivity {

    private RecyclerView recyclerViewServicos;
    private ProgressBar progressBar;
    private ServicoAdapter servicoAdapter;
    private ApiService api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_servico);

        recyclerViewServicos = findViewById(R.id.recyclerViewServicos);
        progressBar = findViewById(R.id.progressBarServicos);

        recyclerViewServicos.setLayoutManager(new LinearLayoutManager(this));

        api = ApiClient.getClient().create(ApiService.class);

        carregarServicos();
    }

    private void carregarServicos() {
        progressBar.setVisibility(View.VISIBLE);

        api.listarServicos().enqueue(new Callback<List<Servico>>() {
            @Override
            public void onResponse(Call<List<Servico>> call, Response<List<Servico>> response) {
                progressBar.setVisibility(View.GONE);

                if (response.isSuccessful() && response.body() != null) {
                    List<Servico> lista = response.body();

                    servicoAdapter = new ServicoAdapter(
                            TelaServico.this, // contexto
                            lista,            // lista
                            servicoId -> concluirServico(servicoId) // listener
                    );

                    recyclerViewServicos.setAdapter(servicoAdapter);
                } else {
                    Toast.makeText(TelaServico.this, "Erro ao carregar serviços", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Servico>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(TelaServico.this, "Falha: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    // Método chamado quando clicar no botão "Concluir" de um serviço
    private void concluirServico(long servicoId) {
        AtualizarRequest request = new AtualizarRequest("CONCLUIDO");

        api.atualizarStatus(servicoId, request).enqueue(new Callback<Servico>() {
            @Override
            public void onResponse(Call<Servico> call, Response<Servico> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(TelaServico.this, "Serviço concluído!", Toast.LENGTH_SHORT).show();
                    carregarServicos(); // Atualiza lista após concluir
                } else {
                    Toast.makeText(TelaServico.this, "Erro ao concluir serviço", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Servico> call, Throwable t) {
                Toast.makeText(TelaServico.this, "Falha: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
