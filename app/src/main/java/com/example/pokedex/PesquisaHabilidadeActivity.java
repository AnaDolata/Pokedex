package com.example.pokedex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.pokedex.adapter.AdapterHabilidade;
import com.example.pokedex.apiPokemon.RetrofitConfig;
import com.example.pokedex.model.Pokemon;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PesquisaHabilidadeActivity extends AppCompatActivity {

  RecyclerView recyclerViewHabilidade;
  EditText input;
  List<Pokemon> pokemonList = new ArrayList<>();
  AdapterHabilidade adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_pesquisa_habilidade);

  }

  public void search(View view) {
    input = findViewById(R.id.editTextAbility);

    if (input.length() == 0) {
      Toast.makeText(this, "Digite uma habilidade", Toast.LENGTH_SHORT).show();
    } else {

      recyclerViewHabilidade = findViewById(R.id.recyclerViewAbility);
      RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

      Pokemon pokemon = new Pokemon();
      pokemon.setHabilidades(input.getText().toString());

      Call<List<Pokemon>> call = new RetrofitConfig().getPKService().getPokemonHabilidade(pokemon);
      call.enqueue(new Callback<List<Pokemon>>() {
        @Override
        public void onResponse(Call<List<Pokemon>> call, Response<List<Pokemon>> response) {
          if (response.isSuccessful()) {
            pokemonList = response.body();
            adapter = new AdapterHabilidade(pokemonList);
            recyclerViewHabilidade.setLayoutManager(layoutManager);
            recyclerViewHabilidade.setHasFixedSize(true);
            recyclerViewHabilidade.addItemDecoration(new DividerItemDecoration(PesquisaHabilidadeActivity.this, LinearLayout.VERTICAL));
            recyclerViewHabilidade.setAdapter(adapter);
          } else {
            Toast.makeText(PesquisaHabilidadeActivity.this, "Nenhum Pokemon encontrado", Toast.LENGTH_SHORT).show();
          }
        }

        @Override
        public void onFailure(Call<List<Pokemon>> call, Throwable t) {

        }
      });

      input.setText("");
    }
  }
}