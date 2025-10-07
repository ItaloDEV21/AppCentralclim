package com.example.appcentralclim.request;

import java.math.BigDecimal;

public class CriarServicoRequest {
    private String descricao;
    private String dataAgendamento; // formato: yyyy-MM-dd
    private BigDecimal valor;
    private Long usuarioId;
    private Long clienteId;

    public CriarServicoRequest(String descricao, String dataAgendamento, BigDecimal valor, Long usuarioId, Long clienteId) {
        this.descricao = descricao;
        this.dataAgendamento = dataAgendamento;
        this.valor = valor;
        this.usuarioId = usuarioId;
        this.clienteId = clienteId;
    }

    public String getDescricao() { return descricao; }
    public String getDataAgendamento() { return dataAgendamento; }
    public BigDecimal getValor() { return valor; }
    public Long getUsuarioId() { return usuarioId; }
    public Long getClienteId() { return clienteId; }

    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setDataAgendamento(String dataAgendamento) { this.dataAgendamento = dataAgendamento; }
    public void setValor(BigDecimal valor) { this.valor = valor; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
}
