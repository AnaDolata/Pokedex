package com.example.pokedex.apiPokemon;

import com.example.pokedex.model.Usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {

  @POST("login")
  Call<Usuario> auth(@Body Usuario usuario);
}
