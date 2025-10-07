package com.example.appcentralclim.model;

import java.math.BigDecimal;

public class Servico {
    private Long id;
    private String descricao;
    private String dataAgendamento; // manter como String para exibir fácil
    private BigDecimal valor;
    private Usuario usuario; // você já tem Usuario.java no app
    private Cliente cliente; // você já tem Cliente.java no app
    private String status;   // enum do back como string

    public Long getId() { return id; }
    public String getDescricao() { return descricao; }
    public String getDataAgendamento() { return dataAgendamento; }
    public BigDecimal getValor() { return valor; }
    public Usuario getUsuario() { return usuario; }
    public Cliente getCliente() { return cliente; }
    public String getStatus() { return status; }

    public void setId(Long id) { this.id = id; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setDataAgendamento(String dataAgendamento) { this.dataAgendamento = dataAgendamento; }
    public void setValor(BigDecimal valor) { this.valor = valor; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public void setStatus(String status) { this.status = status; }
}
