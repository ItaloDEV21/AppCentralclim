package com.example.appcentralclim.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appcentralclim.R;
import com.example.appcentralclim.model.Servico;

import java.util.List;

public class ServicoAdapter extends RecyclerView.Adapter<ServicoAdapter.ServicoViewHolder> {

    // Interface para ação no botão "Concluir"
    public interface OnServicoActionListener {
        void onConcluirClicked(long servicoId);
    }

    private final List<Servico> listaServicos;
    private final Context context;
    private final OnServicoActionListener listener;

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

        // Cliente
        if (servico.getCliente() != null) {
            holder.textClienteNome.setText("Cliente: " + servico.getCliente().getNome());
        } else {
            holder.textClienteNome.setText("Cliente: -");
        }

        // Funcionário
        if (servico.getUsuario() != null) {
            holder.textFuncionario.setText("Técnico: " + servico.getUsuario().getNome());
        } else {
            holder.textFuncionario.setText("Técnico: -");
        }

        // Descrição, Valor e Status
        holder.textDescricao.setText("Descrição: " + servico.getDescricao());
        holder.textValor.setText("R$ " + (servico.getValor() != null ? servico.getValor() : "0.00"));
        holder.textStatus.setText(servico.getStatus());

        // Cor do status
        if (servico.getStatus() != null) {
            switch (servico.getStatus().toUpperCase()) {
                case "CONCLUIDO":
                    holder.textStatus.setTextColor(Color.parseColor("#4CAF50")); // Verde
                    break;
                case "EM_ANDAMENTO":
                    holder.textStatus.setTextColor(Color.parseColor("#2196F3")); // Azul
                    break;
                default:
                    holder.textStatus.setTextColor(Color.parseColor("#FF9800")); // Laranja
                    break;
            }
        }

        // Botão concluir
        if ("CONCLUIDO".equalsIgnoreCase(servico.getStatus())) {
            holder.btnConcluirServico.setVisibility(View.GONE);
        } else {
            holder.btnConcluirServico.setVisibility(View.VISIBLE);
            holder.btnConcluirServico.setOnClickListener(v -> {
                if (listener != null) {
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

    public static class ServicoViewHolder extends RecyclerView.ViewHolder {
        TextView textClienteNome, textFuncionario, textDescricao, textValor, textStatus;
        Button btnConcluirServico;

        public ServicoViewHolder(@NonNull View itemView) {
            super(itemView);
            textClienteNome = itemView.findViewById(R.id.text_cliente_nome);
            textFuncionario = itemView.findViewById(R.id.text_funcionario);
            textDescricao = itemView.findViewById(R.id.text_descricao);
            textValor = itemView.findViewById(R.id.text_valor);
            textStatus = itemView.findViewById(R.id.text_status);
            btnConcluirServico = itemView.findViewById(R.id.btn_concluir_servico);
        }
    }
}
