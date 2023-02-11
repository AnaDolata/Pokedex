package com.example.pokedex.apiPokemon;

import com.example.pokedex.model.Pokemon;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Path;

public interface PKService {

  //@GET("pokemons/tipo")
  @HTTP(method = "GET", path = "pokemons/tipo", hasBody = true)
  Call<List<Pokemon>> getPokemonTipo(@Body String tipo);

  @HTTP(method = "GET", path = "pokemons/habilidade", hasBody = true)
  Call<List<Pokemon>> getPokemonHabilidade(@Body Pokemon pokemon);

  @GET("pokemons")
  Call<List<Pokemon>> getPokemonList();

  @GET("pokemons/{id}")
  Call<Pokemon> getPokemonById(@Path("id") long id);
}
