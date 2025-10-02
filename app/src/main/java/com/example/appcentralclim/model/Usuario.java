package com.example.appcentralclim.model;

// Usuario.java

// Usuario.java

public class Usuario {
    private long id;
    private String nome;
    private String tipoUsuario; // "ADMIN" ou "FUNCIONARIO"

    public Usuario(long id, String nome, String tipoUsuario) {
        this.id = id;
        this.nome = nome;
        this.tipoUsuario = tipoUsuario;
    }

    public long getId() { return id; }
    public String getNome() { return nome; }
    public String getTipoUsuario() { return tipoUsuario; }
}