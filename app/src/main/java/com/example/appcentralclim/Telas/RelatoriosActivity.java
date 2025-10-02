package com.example.appcentralclim.Telas;
// RelatoriosActivity.java

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast; // NOVO IMPORT

import com.example.appcentralclim.R;
import com.example.appcentralclim.adapter.ServicoAdapter;
import com.example.appcentralclim.dao.MockDAO;
import com.example.appcentralclim.model.Servico;

import java.util.ArrayList;
import java.util.List;

// IMPLEMENTA A INTERFACE DO ADAPTER
public class RelatoriosActivity extends AppCompatActivity
        implements ServicoAdapter.OnServicoActionListener {

    private RecyclerView recyclerView;
    private ServicoAdapter adapter;
    private MockDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorios);

        // Configuração da Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_relatorios);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Relatório de Serviços");
        }

        dao = new MockDAO();
        recyclerView = findViewById(R.id.recyclerViewServicos);

        List<Servico> listaInicial = new ArrayList<>();

        // Inicializa o Adapter passando 'this' como o Listener
        adapter = new ServicoAdapter(this, listaInicial, this);
        recyclerView.setAdapter(adapter);

        carregarDados();
    }

    // Método para buscar os dados do DAO e atualizar a lista
    private void carregarDados() {
        List<Servico> servicosAtuais = dao.buscarTodosServicos();
        adapter.atualizarLista(servicosAtuais);
    }

    // Para garantir que a lista seja recarregada quando voltar para esta tela
    @Override
    protected void onResume() {
        super.onResume();
        carregarDados();
    }

    // --- IMPLEMENTAÇÃO DA INTERFACE DE AÇÃO ---
    @Override
    public void onConcluirClicked(long servicoId) {
        // 1. Chama o DAO para atualizar o status do serviço específico
        boolean sucesso = dao.atualizarStatusServico(servicoId, "Concluído");

        if (sucesso) {
            Toast.makeText(this, "Serviço #" + servicoId + " marcado como CONCLUÍDO.", Toast.LENGTH_SHORT).show();
            // 2. Recarrega os dados imediatamente para que a tela se atualize
            carregarDados();
        } else {
            Toast.makeText(this, "Erro ao tentar concluir serviço.", Toast.LENGTH_SHORT).show();
        }
    }
    // ---------------------------------------------

    // Método para lidar com o clique no botão de voltar da toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}