package com.example.appcentralclim.model;

public class Servico {
    private long id;
    private String nomeCliente;
    private String nomeFuncionario;
    private String descricao;
    private String valor;
    private String status; // Ex: "Pendente", "Em Andamento", "Concluído"
    private String dataCriacao; // Para saber quando foi adicionado

    public Servico(long id, String nomeCliente, String nomeFuncionario, String descricao, String valor, String status, String dataCriacao) {
        this.id = id;
        this.nomeCliente = nomeCliente;
        this.nomeFuncionario = nomeFuncionario;
        this.descricao = descricao;
        this.valor = valor;
        this.status = status;
        this.dataCriacao = dataCriacao;
    }

    // Adicione os getters para acessar as propriedades

    public long getId() { return id; }
    public String getNomeCliente() { return nomeCliente; }
    public String getNomeFuncionario() { return nomeFuncionario; }
    public String getDescricao() { return descricao; }
    public String getValor() { return valor; }
    public String getStatus() { return status; }
    public String getDataCriacao() { return dataCriacao; }
}
