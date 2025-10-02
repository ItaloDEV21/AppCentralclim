package com.example.appcentralclim.dao;

import com.example.appcentralclim.model.Cliente;
import com.example.appcentralclim.model.Funcionario;
import com.example.appcentralclim.model.Servico;
import java.util.ArrayList;
import java.util.List;

public class MockDAO {

    // Simula a tabela de serviços no banco de dados (Lista estática para teste)
    private static List<Servico> servicosDatabase = new ArrayList<>();
    private static long nextServicoId = 1;

    // --- Métodos de Clientes e Funcionários (Mantidos) ---
    public List<Cliente> buscarClientes() {
        // ... (sua lógica existente de clientes)
        return new ArrayList<>(); // Retornar a lógica real aqui
    }

    public List<Funcionario> buscarFuncionarios() {
        // ... (sua lógica existente de funcionários)
        return new ArrayList<>(); // Retornar a lógica real aqui
    }

    // --- NOVOS MÉTODOS PARA SERVIÇOS ---

    public void salvarServico(String nomeCliente, String descricao, String valor, String nomeFuncionario) {
        // Simula o salvamento no BD
        Servico novoServico = new Servico(
                nextServicoId++,
                nomeCliente,
                nomeFuncionario,
                descricao,
                valor,
                "Pendente", // Status inicial
                "01/10/2025" // Data de hoje (simplificado)
        );
        servicosDatabase.add(novoServico);
    }

    public List<Servico> buscarTodosServicos() {
        // Simula a busca de todos os serviços
        return servicosDatabase;
    }

    // NOVO MÉTODO: Atualiza o status de um serviço pelo ID
    public boolean atualizarStatusServico(long idServico, String novoStatus) {
        for (int i = 0; i < servicosDatabase.size(); i++) {
            Servico servico = servicosDatabase.get(i);

            if (servico.getId() == idServico) {
                // Remove o serviço antigo e adiciona um novo com o status atualizado
                servicosDatabase.remove(i);

                Servico servicoAtualizado = new Servico(
                        servico.getId(),
                        servico.getNomeCliente(),
                        servico.getNomeFuncionario(),
                        servico.getDescricao(),
                        servico.getValor(),
                        novoStatus, // Status atualizado
                        servico.getDataCriacao()
                );
                servicosDatabase.add(i, servicoAtualizado); // Insere na mesma posição
                return true;
            }
        }
        return false;
    }

}