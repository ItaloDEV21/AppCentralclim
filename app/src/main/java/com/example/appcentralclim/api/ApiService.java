package com.example.appcentralclim.api;

import com.example.appcentralclim.model.Cliente;
import com.example.appcentralclim.model.Funcionario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("clientes")
    Call<List<Cliente>> getClientes();

    @GET("funcionarios")
    Call<List<Funcionario>> getFuncionarios();
}
