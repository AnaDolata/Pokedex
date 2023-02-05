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

import com.example.pokedex.adapter.AdapterTipo;

public class PesquisaTipoActivity extends AppCompatActivity {

  RecyclerView recyclerViewTipo;
  EditText input;

  @SuppressLint("MissingInflatedId")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_pesquisa_tipo);

  }

  public void search(View view){
    input = findViewById(R.id.editTextType);

    recyclerViewTipo = findViewById(R.id.recyclerViewType);

    AdapterTipo adapter = new AdapterTipo();

    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
    recyclerViewTipo.setLayoutManager(layoutManager);
    recyclerViewTipo.setHasFixedSize(true);
    recyclerViewTipo.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
    recyclerViewTipo.setAdapter(adapter);

    input.setText("");
  }
}