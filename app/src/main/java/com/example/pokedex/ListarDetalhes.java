package com.example.pokedex;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class ListarDetalhes extends AppCompatActivity {

  EditText nome, tipo, habilidade;
  ImageView imagem;

  @SuppressLint("MissingInflatedId")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_listar_detalhes);

    nome = findViewById(R.id.editTextNome);
    tipo = findViewById(R.id.editTextTipo);
    habilidade = findViewById(R.id.editTextHabilidade);
    imagem = findViewById(R.id.imageViewPhoto);

    nome.setText("nome");
    tipo.setText("tipo");
    habilidade.setText("habildadessss");
  }

  public void save(View view){
    Intent i = new Intent(ListarDetalhes.this, ListarActivity.class);
    startActivity(i);
    finish();
  }

  public void delete(View view){
    Intent i = new Intent(ListarDetalhes.this, ListarActivity.class);
    startActivity(i);
    finish();
  }
}