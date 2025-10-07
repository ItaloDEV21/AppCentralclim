package com.example.appcentralclim.model;

public class Cliente {

    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String endereco;

    // Construtor vazio (necessário para Retrofit / JPA / JSON)
    public Cliente() {
    }

    // Construtor simples para mocks ou testes
    public Cliente(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    // Construtor completo (opcional, se precisar criar cliente com todos os dados)
    public Cliente(Long id, String nome, String email, String telefone, String endereco) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
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

    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
