package com.example.appcentralclim.request;

public class AtualizarRequest {
    private String status; // ex: AGENDADO, EM_ANDAMENTO, CONCLUIDO

    public AtualizarRequest(String status) {
        this.status = status;
    }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
