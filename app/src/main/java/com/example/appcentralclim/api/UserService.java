package com.example.appcentralclim.api;

import com.example.appcentralclim.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

    @POST("usuarios") // Exemplo: POST /api/usuarios
    Call<User> cadastrarUsuario(@Body User user);
}
