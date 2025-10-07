package com.example.appcentralclim.model;

public class Funcionario {

    private Long id;
    private String nome;
    private String email;
    private String cargo;
    private String senha; // 🔹 Novo campo adicionado

    // Construtor vazio (necessário para Retrofit / JSON / JPA)
    public Funcionario() {}

    // Construtor simples para mocks (id + nome)
    public Funcionario(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    // Construtor usado em alguns cadastros (nome, email, senha)
    public Funcionario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cargo = "FUNCIONARIO"; // valor padrão para cargo
    }

    // Construtor completo
    public Funcionario(Long id, String nome, String email, String cargo, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cargo = cargo;
        this.senha = senha;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getCargo() {
        return cargo;
    }
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
}
