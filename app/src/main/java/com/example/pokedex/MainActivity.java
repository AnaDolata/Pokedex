package com.example.pokedex;

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

import com.example.pokedex.adapter.AdapterHabilidade;
import com.example.pokedex.adapter.AdapterTipo;
import com.example.pokedex.model.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  TextView numero;
  RecyclerView recyclerViewTipo;
  RecyclerView recyclerViewHabilidade;

  List<Pokemon> listPokemon = new ArrayList<>();

  @SuppressLint("MissingInflatedId")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    recyclerViewTipo = findViewById(R.id.viewTiposPokemon);
    recyclerViewHabilidade = findViewById(R.id.viewHobilidadesPokemon);

    this.createPokemonType();
    AdapterTipo adapter = new AdapterTipo(listPokemon);
    recyclerViewTipo.setHasFixedSize(true);
    recyclerViewTipo.setAdapter(adapter);

    this.createPokemonHability();
    AdapterHabilidade adapterHabilidade = new AdapterHabilidade(listPokemon);
    recyclerViewTipo.setHasFixedSize(true);
    recyclerViewTipo.setAdapter(adapterHabilidade);

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

  public void createPokemonType(){

  }

  public void createPokemonHability(){

  }
}