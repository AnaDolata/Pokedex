package com.example.pokedex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.pokedex.adapter.AdapterTipo;
import com.example.pokedex.apiPokemon.RetrofitConfigAuth;
import com.example.pokedex.model.Pokemon;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PesquisaTipoActivity extends AppCompatActivity {

  RecyclerView recyclerViewTipo;
  EditText input;
  List<Pokemon> pokemonList = new ArrayList<>();
  AdapterTipo adapter;

  @SuppressLint("MissingInflatedId")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_pesquisa_tipo);

  }

  public void search(View view){
    input = findViewById(R.id.editTextType);

    recyclerViewTipo = findViewById(R.id.recyclerViewType);
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

    Call<List<Pokemon>> call = new RetrofitConfigAuth().getPKService().getPokemonTipo();
    call.enqueue(new Callback<List<Pokemon>>() {
      @Override
      public void onResponse(Call<List<Pokemon>> call, Response<List<Pokemon>> response) {
        if(response.isSuccessful()){
          pokemonList = response.body();
          adapter = new AdapterTipo(pokemonList);
          recyclerViewTipo.setLayoutManager(layoutManager);
          recyclerViewTipo.setHasFixedSize(true);
          recyclerViewTipo.addItemDecoration(new DividerItemDecoration(PesquisaTipoActivity.this, LinearLayout.VERTICAL));
          recyclerViewTipo.setAdapter(adapter);
        }
      }

      @Override
      public void onFailure(Call<List<Pokemon>> call, Throwable t) {

      }
    });

    input.setText("");
  }
}