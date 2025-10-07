package com.example.appcentralclim.dao;

import com.example.appcentralclim.model.Cliente;
import com.example.appcentralclim.model.Funcionario;
import com.example.appcentralclim.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class MockDAO {

    private final List<Cliente> clientes = new ArrayList<>();
    private final List<Funcionario> funcionarios = new ArrayList<>();
    private final List<Usuario> usuarios = new ArrayList<>();

    public MockDAO() {
        // Mock de Clientes
        clientes.add(new Cliente(1L, "Empresa A"));
        clientes.add(new Cliente(2L, "Empresa B"));
        clientes.add(new Cliente(3L, "Empresa C"));

        // Mock de Funcionários
        funcionarios.add(new Funcionario(1L, "Carlos Silva"));
        funcionarios.add(new Funcionario(2L, "Maria Oliveira"));

        // Mock de Usuários (exemplo ADMIN e FUNCIONARIO)
        usuarios.add(new Usuario(1L, "Administrador", "ADMIN"));
        usuarios.add(new Usuario(2L, "Carlos Silva", "FUNCIONARIO"));
        usuarios.add(new Usuario(3L, "Maria Oliveira", "FUNCIONARIO"));
    }

    // Retorna lista de clientes
    public List<Cliente> getClientes() {
        return clientes;
    }

    // Retorna lista de funcionários
    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    // Retorna lista de usuários
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    // Autentica usuário pelo nome
    public Usuario autenticarUsuario(String nome, String senha) {
        // Obs: aqui a senha não está sendo validada, só nome
        for (Usuario u : usuarios) {
            if (u.getNome().equalsIgnoreCase(nome)) {
                return u;
            }
        }
        return null;
    }
}
