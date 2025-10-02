package com.example.appcentralclim.adapter;
// ServicoAdapter.java

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button; // NOVO IMPORT
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appcentralclim.R;
import com.example.appcentralclim.model.Servico;

import java.util.List;

public class ServicoAdapter extends RecyclerView.Adapter<ServicoAdapter.ServicoViewHolder> {

    // --- NOVA INTERFACE DE CLIQUE ---
    public interface OnServicoActionListener {
        void onConcluirClicked(long servicoId);
    }
    // ---------------------------------

    private final List<Servico> listaServicos;
    private final Context context;
    private final OnServicoActionListener listener; // NOVO CAMPO

    // Construtor atualizado para receber o Listener
    public ServicoAdapter(Context context, List<Servico> listaServicos, OnServicoActionListener listener) {
        this.context = context;
        this.listaServicos = listaServicos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ServicoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_servico, parent, false);
        return new ServicoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServicoViewHolder holder, int position) {
        Servico servico = listaServicos.get(position);

        // 1. Preenche os dados
        holder.textClienteNome.setText("Cliente: " + servico.getNomeCliente());
        holder.textFuncionario.setText("Técnico: " + servico.getNomeFuncionario());
        holder.textDescricao.setText("Descrição: " + servico.getDescricao());
        holder.textValor.setText("R$ " + servico.getValor());
        holder.textStatus.setText(servico.getStatus());

        // 2. Lógica de cores para o Status
        // ... (Seu código de cores existente)
        switch (servico.getStatus()) {
            case "Concluído":
                holder.textStatus.setTextColor(Color.parseColor("#4CAF50")); // Verde
                break;
            case "Em Andamento":
                holder.textStatus.setTextColor(Color.parseColor("#2196F3")); // Azul
                break;
            case "Pendente":
            default:
                holder.textStatus.setTextColor(Color.parseColor("#FF9800")); // Laranja
                break;
        }

        // 3. Lógica do Botão de Conclusão e Visibilidade
        if (servico.getStatus().equals("Concluído")) {
            // Se o serviço já está concluído, esconde o botão
            holder.btnConcluirServico.setVisibility(View.GONE);
        } else {
            // Se o serviço está Pendente/Em Andamento, mostra o botão e configura o clique
            holder.btnConcluirServico.setVisibility(View.VISIBLE);
            holder.btnConcluirServico.setOnClickListener(v -> {
                if (listener != null) {
                    // Chama o método na Activity, passando o ID do serviço
                    listener.onConcluirClicked(servico.getId());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listaServicos.size();
    }

    public void atualizarLista(List<Servico> novaLista) {
        listaServicos.clear();
        listaServicos.addAll(novaLista);
        notifyDataSetChanged();
    }

    // ViewHolder: Mapeia as Views do item_servico.xml
    public static class ServicoViewHolder extends RecyclerView.ViewHolder {
        TextView textClienteNome, textFuncionario, textDescricao, textValor, textStatus;
        Button btnConcluirServico; // NOVO COMPONENTE

        public ServicoViewHolder(@NonNull View itemView) {
            super(itemView);
            textClienteNome = itemView.findViewById(R.id.text_cliente_nome);
            textFuncionario = itemView.findViewById(R.id.text_funcionario);
            textDescricao = itemView.findViewById(R.id.text_descricao);
            textValor = itemView.findViewById(R.id.text_valor);
            textStatus = itemView.findViewById(R.id.text_status);
            btnConcluirServico = itemView.findViewById(R.id.btn_concluir_servico); // MAPEAMENTO
        }
    }
}