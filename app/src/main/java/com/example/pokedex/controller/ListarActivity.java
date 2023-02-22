package com.example.pokedex.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.example.pokedex.R;
import com.example.pokedex.adapter.AdapterListaTodos;
import com.example.pokedex.apiPokemon.RetrofitConfig;
import com.example.pokedex.model.Pokemon;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListarActivity extends AppCompatActivity {

    RecyclerView recyclerViewListar;
    List<Pokemon> list = new ArrayList<>();
    AdapterListaTodos adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
        listarPokemons();
    }

    public void listarPokemons() {
        Call<List<Pokemon>> call = new RetrofitConfig().getPKService().getPokemonList();
        call.enqueue(new Callback<List<Pokemon>>() {
            @Override
            public void onResponse(Call<List<Pokemon>> call, Response<List<Pokemon>> response) {
                if (response.isSuccessful()) {
                    recyclerViewListar = findViewById(R.id.recyclerViewListaTodos);
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                    list = response.body();
                    adapter = new AdapterListaTodos(list);
                    recyclerViewListar.setLayoutManager(layoutManager);
                    recyclerViewListar.setHasFixedSize(true);
                    recyclerViewListar.addItemDecoration(new DividerItemDecoration(ListarActivity.this, LinearLayout.VERTICAL));
                    recyclerViewListar.setAdapter(adapter);

                    recyclerViewListar.addOnItemTouchListener(new RecyclerItemClickListener(
                            getApplicationContext(),
                            recyclerViewListar,
                            new RecyclerItemClickListener.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    Pokemon pokemon = list.get(position);
                                    //Log.i("pokemon", String.valueOf(pokemon.getId()));
                                    //passar o id para intent
                                    Intent i = new Intent(ListarActivity.this, ListarDetalhes.class);
                                    Bundle params = new Bundle();
                                    params.putString("id", String.valueOf(pokemon.getId()));
                                    i.putExtras(params);
                                    startActivity(i);
                                    finish();
                                }

                                @Override
                                public void onItemLongClick(View view, int position) {
                                    Pokemon pokemon = list.get(position);
                                    //Log.i("pokemon", String.valueOf(pokemon.getId()));
                                    //passar o id para intent
                                    Intent i = new Intent(ListarActivity.this, ListarDetalhes.class);
                                    Bundle params = new Bundle();
                                    params.putString("id", String.valueOf(pokemon.getId()));
                                    i.putExtras(params);
                                    startActivity(i);
                                    finish();
                                }
                            }));
                }
            }

            @Override
            public void onFailure(Call<List<Pokemon>> call, Throwable t) {
                Log.e("ERROR", "Activity Listar Pokemons: " + t.getMessage());
            }
        });

    }
}