package com.example.pokedex.apiPokemon;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {
  private final Retrofit retrofit;

  public RetrofitConfig(){
    this.retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.0.186:5003/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
  }

  public AuthService getAuthService(){
    return this.retrofit.create(AuthService.class);
  }

  public PKService getPKService(){
    return this.retrofit.create(PKService.class);
  }
}
