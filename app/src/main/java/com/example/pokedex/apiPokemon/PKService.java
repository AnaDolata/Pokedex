package com.example.pokedex.apiPokemon;

import com.example.pokedex.model.Pokemon;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PKService {

  @GET("pokemons/tipo")
  Call<List<Pokemon>> getPokemonTipo();
}
