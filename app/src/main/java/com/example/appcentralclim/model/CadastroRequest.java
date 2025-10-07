package com.example.appcentralclim.model;

public class CadastroRequest {
    private String nome;
    private String cpf;
    private String email;
    private String senha;

    public CadastroRequest(String nome, String cpf, String email, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
    }

    // Getters e Setters se necessário
}

