package com.example.pokedex.apiPokemon;

import com.example.pokedex.model.Pokemon;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PKService {

  @GET("pokemons/tipo")
  Call<List<Pokemon>> getPokemonTipo(@Query("tipo") String tipo);

  @GET("pokemons/habilidade")
  Call<List<Pokemon>> getPokemonHabilidade(@Query("habilidade") String hab);

  @GET("pokemons")
  Call<List<Pokemon>> getPokemonList();

  @GET("pokemons/{id}")
  Call<Pokemon> getPokemonById(@Path("id") long id);

  @DELETE("pokemons/{id}")
  Call<Void> deletePokemon(@Path("id") long id);

  @POST("pokemons")
  Call<Void> createPokemon(@Body Pokemon pokemon);

  @PUT("pokemons/{id}")
  Call<Void> updatePokemon(@Path("id") long id, @Body Pokemon pokemon);
}
