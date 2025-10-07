package com.example.appcentralclim.api;

import com.example.appcentralclim.model.Funcionario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface FuncionarioService {

    @POST("/funcionarios") // ou o caminho correto da sua API
    Call<Void> cadastrarFuncionario(@Body Funcionario funcionario);
}
