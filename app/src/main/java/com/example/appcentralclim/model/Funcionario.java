package com.example.appcentralclim.model;

public class Funcionario {
    private long id;
    private String nome;
    private String email;
    private String senha;

    public Funcionario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public long getId() { return id; }

    public String getNome() { return nome; }

    public String getEmail() { return email; }

    public String getSenha() { return senha; }

    @Override
    public String toString() {
        return nome;
    }
}
