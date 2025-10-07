package com.example.appcentralclim.api;

import com.example.appcentralclim.model.AtualizarRequest;
import com.example.appcentralclim.model.CriarServicoRequest;
import com.example.appcentralclim.model.Servico;
import com.example.appcentralclim.model.Cliente;
import com.example.appcentralclim.model.Funcionario;
import com.example.appcentralclim.model.LoginRequest;
import com.example.appcentralclim.model.LoginResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    // CLIENTES
    @GET("clientes")
    Call<List<Cliente>> getClientes();

    @POST("clientes")
    Call<Cliente> criarCliente(@Body Cliente cliente);

    // FUNCIONARIOS (no back ainda não está pronto, pode deixar por enquanto)
    @GET("funcionarios")
    Call<List<Funcionario>> getFuncionarios();

    // LOGIN
    @POST("auth/login")
    Call<LoginResponse> login(@Body LoginRequest request);

    // SERVICOS
    @POST("servicos")
    Call<Servico> criarServico(@Body CriarServicoRequest request);

    @GET("servicos")
    Call<List<Servico>> listarServicos();

    @GET("servicos/{id}")
    Call<Servico> buscarServico(@Path("id") Long id);

    @PATCH("servicos/{id}/status")
    Call<Servico> atualizarStatus(@Path("id") Long id, @Body AtualizarRequest request);
}
