package com.example.pokedex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.pokedex.adapter.AdapterListaTodos;

public class ListarActivity extends AppCompatActivity {

  RecyclerView recyclerViewListar;

  @SuppressLint("MissingInflatedId")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_listar);

    recyclerViewListar = findViewById(R.id.recyclerViewListaTodos);

    AdapterListaTodos adapter = new AdapterListaTodos();

    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
    recyclerViewListar.setLayoutManager(layoutManager);
    recyclerViewListar.setHasFixedSize(true);
    recyclerViewListar.setAdapter(adapter);

    recyclerViewListar.addOnItemTouchListener(new RecyclerItemClickListener(
            getApplicationContext(),
            recyclerViewListar,
            new RecyclerItemClickListener.OnItemClickListener() {
              @Override
              public void onItemClick(View view, int position) {
                Intent i = new Intent(ListarActivity.this, ListarDetalhes.class);
                startActivity(i);
              }

              @Override
              public void onItemLongClick(View view, int position) {
                Intent i = new Intent(ListarActivity.this, ListarDetalhes.class);
                startActivity(i);
                finish();
              }
            }));
  }
}