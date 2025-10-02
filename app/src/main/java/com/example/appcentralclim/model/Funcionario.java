package com.example.appcentralclim.model;

public class Funcionario {
    private long id;
    private String nome;

    public Funcionario(long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public long getId() {
        return id;
    }

    // O AutoCompleteTextView usará este método para exibir o nome.
    @Override
    public String toString() {
        return nome;
    }
}
