package com.example.appcentralclim.model;

public class Cliente {
    private long id;
    private String nome;

    public Cliente(long id, String nome) {
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
