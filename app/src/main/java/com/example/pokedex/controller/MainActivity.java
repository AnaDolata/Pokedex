package com.example.pokedex.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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

import java.io.IOException;
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
    TopPokemons quantidade = new TopPokemons();

    int quantidadePokemons;
    String usuario;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_cadastro);
        Bundle bundle = getIntent().getExtras();
        usuario = bundle.getString("usuario");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.createPokemonType();
        this.countPokemon();
        this.createPokemonHability();

        total = findViewById(R.id.txtNumeroPokemons);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.createPokemonType();
        this.countPokemon();
        this.createPokemonHability();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add) {
            Intent intent = new Intent(getApplicationContext(), CadastroActivity.class);
            Bundle params = new Bundle();
            params.putString("usuario", String.valueOf(usuario));
            intent.putExtras(params);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.listar) {
            Intent intent = new Intent(getApplicationContext(), ListarActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.pesquisaTipo) {
            Intent intent = new Intent(getApplicationContext(), PesquisaTipoActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.pesquisaHabilidade) {
            Intent intent = new Intent(getApplicationContext(), PesquisaHabilidadeActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.sair) {
            finishAffinity();
        }
        return super.onOptionsItemSelected(item);
    }

    public void countPokemon() {
        Log.i("debug", "test4");
        Call<TopPokemons> call = new RetrofitConfig().getPKService().getPokemonQuantidade();
        call.enqueue(new Callback<TopPokemons>() {
            @Override
            public void onResponse(Call<TopPokemons> call, Response<TopPokemons> response) {
                if (response.isSuccessful()) {
                    quantidade = response.body();
                    quantidadePokemons = quantidade.getQuantidade();
                    Log.i("debug", "Quantidade: " + quantidade.getQuantidade());
                    total.setText(Integer.toString(quantidadePokemons));
                }
            }

            @Override
            public void onFailure(Call<TopPokemons> call, Throwable t) {
            }
        });
    }

    public void createPokemonType() {
        Call<List<TopPokemons>> call = new RetrofitConfig().getPKService().getPokemonTipoTop();
        call.enqueue(new Callback<List<TopPokemons>>() {
            @Override
            public void onResponse(Call<List<TopPokemons>> call, Response<List<TopPokemons>> response) {
                if (response.isSuccessful()) {
                    listTopTipo = response.body();
                    recyclerViewTipo = findViewById(R.id.recyclerViewTopTipos);
                    AdapterListaTopTipos adapterTipo = new AdapterListaTopTipos(listTopTipo);
                    RecyclerView.LayoutManager layoutManager1 =
                            new LinearLayoutManager(getApplicationContext());
                    recyclerViewTipo.setLayoutManager(layoutManager1);
                    recyclerViewTipo.setHasFixedSize(true);
                    recyclerViewTipo.addItemDecoration(
                            new DividerItemDecoration(MainActivity.this, LinearLayout.VERTICAL));
                    recyclerViewTipo.setAdapter(adapterTipo);
                }
            }

            @Override
            public void onFailure(Call<List<TopPokemons>> call, Throwable t) {

            }
        });
    }

    public void createPokemonHability() {
        Call<List<TopPokemons>> call = new RetrofitConfig().getPKService().getPokemonHabilidadeTop();
        call.enqueue(new Callback<List<TopPokemons>>() {
            @Override
            public void onResponse(Call<List<TopPokemons>> call, Response<List<TopPokemons>> response) {
                if (response.isSuccessful()) {
                    listTopHab = response.body();
                    recyclerViewHabilidade = findViewById(R.id.recyclerViewTopHabilidades);
                    AdapterListaTopHabilidades adapterHabilidade = new AdapterListaTopHabilidades(listTopHab);
                    RecyclerView.LayoutManager layoutManager2 =
                            new LinearLayoutManager(getApplicationContext());
                    recyclerViewHabilidade.setLayoutManager(layoutManager2);
                    recyclerViewHabilidade.setHasFixedSize(true);
                    recyclerViewHabilidade.addItemDecoration(
                            new DividerItemDecoration(MainActivity.this, LinearLayout.VERTICAL));
                    recyclerViewHabilidade.setAdapter(adapterHabilidade);
                }
            }

            @Override
            public void onFailure(Call<List<TopPokemons>> call, Throwable t) {

            }
        });
    }
}