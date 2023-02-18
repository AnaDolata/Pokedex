package com.example.pokedex.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pokedex.R;
import com.example.pokedex.adapter.AdapterHabilidade;
import com.example.pokedex.adapter.AdapterListaTopHabilidades;
import com.example.pokedex.adapter.AdapterListaTopTipos;
import com.example.pokedex.adapter.AdapterTipo;
import com.example.pokedex.apiPokemon.RetrofitConfig;
import com.example.pokedex.model.Pokemon;
import com.example.pokedex.model.TopPokemons;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

  TextView total;
  RecyclerView recyclerViewTipo;
  RecyclerView recyclerViewHabilidade;

  List<TopPokemons> listTopTipo = new ArrayList<>();
  List<TopPokemons> listTopHab = new ArrayList<>();

  List<Pokemon> listPokemon = new ArrayList<>();
  List<Pokemon> listPokemonTipo = new ArrayList<>();
  List<Pokemon> listPokemonHab = new ArrayList<>();

  int quantidadePokemons = 0;

  @SuppressLint("MissingInflatedId")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    total = findViewById(R.id.txtNumeroPokemons);

    recyclerViewTipo = findViewById(R.id.recyclerViewTopTipos);
    recyclerViewHabilidade = findViewById(R.id.recyclerViewTopHabilidades);

    //this.countPokemon();

    this.createPokemonType();

    AdapterListaTopTipos adapterTipo = new AdapterListaTopTipos(listTopTipo);

    RecyclerView.LayoutManager layoutManager1 =
            new LinearLayoutManager(getApplicationContext());

    recyclerViewTipo.setLayoutManager(layoutManager1);
    recyclerViewTipo.setHasFixedSize(true);
    recyclerViewTipo.addItemDecoration(
            new DividerItemDecoration(this, LinearLayout.VERTICAL));
    recyclerViewTipo.setAdapter(adapterTipo);

    this.createPokemonHability();
    AdapterListaTopHabilidades adapterHabilidade = new AdapterListaTopHabilidades(listTopHab);

    RecyclerView.LayoutManager layoutManager2 =
            new LinearLayoutManager(getApplicationContext());

    recyclerViewHabilidade.setLayoutManager(layoutManager2);
    recyclerViewHabilidade.setHasFixedSize(true);
    recyclerViewHabilidade.addItemDecoration(
            new DividerItemDecoration(this, LinearLayout.VERTICAL));
    recyclerViewHabilidade.setAdapter(adapterHabilidade);

    total.setText(Integer.toString(quantidadePokemons));

  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu){
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item){
    if(item.getItemId() == R.id.add){
      Intent intent = new Intent(getApplicationContext(),CadastroActivity.class);
      startActivity(intent);
    }
    if(item.getItemId() == R.id.listar){
      Intent intent = new Intent(getApplicationContext(),ListarActivity.class);
      startActivity(intent);
    }
    if(item.getItemId() == R.id.pesquisaTipo){
      Intent intent = new Intent(getApplicationContext(),PesquisaTipoActivity.class);
      startActivity(intent);
    }
    if(item.getItemId() == R.id.pesquisaHabilidade){
      Intent intent = new Intent(getApplicationContext(),PesquisaHabilidadeActivity.class);
      startActivity(intent);
    }
    if(item.getItemId() == R.id.sair){
      finish();
    }
    return super.onOptionsItemSelected(item);
  }

//  public void countPokemon(){
//    Call<List<Pokemon>> call = new RetrofitConfig().getPKService().getPokemonList();
//    call.enqueue(new Callback<List<Pokemon>>() {
//      @Override
//      public void onResponse(Call<List<Pokemon>> call, Response<List<Pokemon>> response) {
//        if (response.isSuccessful()) {
//          listPokemon = response.body();
//        }
//        if(!listPokemon.isEmpty()) {
//          quantidadePokemons = 0;
//          for (Pokemon pok : listPokemon) {
//            quantidadePokemons++;
//          }
//        }
//      }
//      @Override
//      public void onFailure(Call<List<Pokemon>> call, Throwable t) {
//
//      }
//    });
//  }

  public void createPokemonType(){
    Call<List<Pokemon>> call = new RetrofitConfig().getPKService().getPokemonList();
    call.enqueue(new Callback<List<Pokemon>>() {
      @Override
      public void onResponse(Call<List<Pokemon>> call, Response<List<Pokemon>> response) {
        if (response.isSuccessful()) {
          listPokemonTipo = response.body();
        }
        if(!listPokemonTipo.isEmpty()) {
          for (Pokemon pok : listPokemonTipo) {
            listTopTipo.add(new TopPokemons(pok.getTipo(), 1));
            quantidadePokemons++;
          }
        }
      }

      @Override
      public void onFailure(Call<List<Pokemon>> call, Throwable t) {

      }
    });
  }

  public void createPokemonHability(){
    Call<List<Pokemon>> call = new RetrofitConfig().getPKService().getPokemonList();
    call.enqueue(new Callback<List<Pokemon>>() {
      @Override
      public void onResponse(Call<List<Pokemon>> call, Response<List<Pokemon>> response) {
        if (response.isSuccessful()) {
          listPokemonHab = response.body();
        }
        if(!listPokemonHab.isEmpty()) {
          for (Pokemon pok : listPokemonHab) {
            listTopHab.add(new TopPokemons(pok.getHabilidades(), 1));
          }
        }
      }

      @Override
      public void onFailure(Call<List<Pokemon>> call, Throwable t) {

      }
    });
  }
}