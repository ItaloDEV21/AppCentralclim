package com.example.appcentralclim.dao;

import com.example.appcentralclim.model.Cliente;
import com.example.appcentralclim.model.Funcionario;
import com.example.appcentralclim.model.Servico;
import com.example.appcentralclim.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class MockDAO {

    // Simula a tabela de serviços no banco de dados (Lista estática para teste)
    private static List<Servico> servicosDatabase = new ArrayList<>();
    private static long nextServicoId = 1;

    // --- Métodos de Clientes e Funcionários (Mantidos) ---
    public List<Cliente> buscarClientes() {
        // ... (sua lógica existente de clientes)
        // Para fins de teste, vou adicionar um cliente aqui para garantir que a lista não esteja vazia
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente(1, "Empresa A"));
        return clientes;
    }

   // public List<Funcionario> buscarFuncionarios() {
        // ... (sua lógica existente de funcionários)
        // Para fins de teste, vou adicionar o funcionário logado
   //     List<Funcionario> funcionarios = new ArrayList<>();
   //     funcionarios.add(new Funcionario(101, "João da Silva"));
   //     return funcionarios;
   // }

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

    // MÉTODO ATUALIZADO PARA FILTRO
    public List<Servico> buscarServicos(String nomeFuncionario) {
        // Se o nomeFuncionario for null ou vazio, retorna a lista completa (Relatório Geral)
        if (nomeFuncionario == null || nomeFuncionario.isEmpty()) {
            return servicosDatabase; // Retorna todos os serviços
        }

        // Se houver filtro, retorna apenas os serviços daquele funcionário
        List<Servico> servicosFiltrados = new ArrayList<>();
        for (Servico servico : servicosDatabase) {
            // A comparação precisa ser exata
            if (servico.getNomeFuncionario().equals(nomeFuncionario)) {
                servicosFiltrados.add(servico);
            }
        }
        return servicosFiltrados;
    }

    // O antigo 'buscarTodosServicos()' foi substituído pelo 'buscarServicos(null)'

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

    // NOVO MÉTODO PARA SIMULAR O LOGIN
    public Usuario autenticarUsuario(String email, String senha) {
        // 1. Simulação do Admin
        if (email.equals("admin@clima.com") && senha.equals("admin123")) {
            // **NOTA: Usei as credenciais do seu código (ADMIN_EMAIL, ADMIN_PASSWORD)**
            return new Usuario(1, "Administrador Geral", "ADMIN");
        }

        // 2. Simulação do Funcionário
        if (email.equals("joao@clima.com") && senha.equals("123456")) {
            // Se for funcionário, retorna o objeto com o tipo "FUNCIONARIO"
            // Usei "joao@clima.com" e "123456" como credenciais de exemplo para o funcionário
            return new Usuario(101, "João da Silva", "FUNCIONARIO");
        }

        // Se a autenticação falhar, retorna nulo
        return null;
    }


}