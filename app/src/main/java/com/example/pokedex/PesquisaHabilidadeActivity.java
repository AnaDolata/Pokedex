package com.example.pokedex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.pokedex.adapter.AdapterHabilidade;

public class PesquisaHabilidadeActivity extends AppCompatActivity {

  RecyclerView recyclerViewHabilidade;
  EditText input;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_pesquisa_habilidade);

  }

  public void search(View view){
    input = findViewById(R.id.editTextAbility);

    recyclerViewHabilidade = findViewById(R.id.recyclerViewAbility);

    AdapterHabilidade adapter = new AdapterHabilidade();

    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
    recyclerViewHabilidade.setLayoutManager(layoutManager);
    recyclerViewHabilidade.setHasFixedSize(true);
    recyclerViewHabilidade.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
    recyclerViewHabilidade.setAdapter(adapter);

    input.setText("");
  }
}