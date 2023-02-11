package com.example.pokedex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.pokedex.adapter.AdapterTipo;
import com.example.pokedex.apiPokemon.RetrofitConfig;
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

  public void search(View view) {
    input = findViewById(R.id.editTextType);

    if (input.length() == 0) {
      Toast.makeText(this, "Digite um tipo", Toast.LENGTH_SHORT).show();
    } else {

      recyclerViewTipo = findViewById(R.id.recyclerViewType);
      RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

      Call<List<Pokemon>> call = new RetrofitConfig().getPKService().getPokemonTipo(input.getText().toString());
      call.enqueue(new Callback<List<Pokemon>>() {
        @Override
        public void onResponse(Call<List<Pokemon>> call, Response<List<Pokemon>> response) {
          if (response.isSuccessful()) {
            pokemonList = response.body();
            adapter = new AdapterTipo(pokemonList);
            recyclerViewTipo.setLayoutManager(layoutManager);
            recyclerViewTipo.setHasFixedSize(true);
            recyclerViewTipo.addItemDecoration(new DividerItemDecoration(PesquisaTipoActivity.this, LinearLayout.VERTICAL));
            recyclerViewTipo.setAdapter(adapter);
          } else {
            Toast.makeText(PesquisaTipoActivity.this, "Nenhum Pokemon encontrado", Toast.LENGTH_SHORT).show();
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