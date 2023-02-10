package com.example.pokedex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

  TextView numero;

  @SuppressLint("MissingInflatedId")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    //numero = findViewById(R.id.textViewNumero);
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
    return super.onOptionsItemSelected(item);
  }
}