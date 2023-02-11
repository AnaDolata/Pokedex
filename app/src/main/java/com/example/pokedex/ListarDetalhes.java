package com.example.pokedex;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.pokedex.apiPokemon.RetrofitConfig;
import com.example.pokedex.model.Pokemon;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListarDetalhes extends AppCompatActivity {

  EditText nome, tipo, habilidade;
  ImageView imagem;
  Bitmap bitmap;

  @SuppressLint({"MissingInflatedId", "ResourceType"})
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_listar_detalhes);

    nome = findViewById(R.id.editTextNome);
    tipo = findViewById(R.id.editTextTipo);
    habilidade = findViewById(R.id.editTextHabilidade);
    imagem = findViewById(R.id.imageViewPhoto);

    imagem.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.setType("image/*");
        i.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(i, 1);
      }
    });

    Intent it = getIntent();
    if(it != null){
      Bundle params = it.getExtras();
      if(params != null) {
        String pokId = params.getString("id");
        Log.i("pokemon id", pokId);

        Call<Pokemon> call = new RetrofitConfig().getPKService().getPokemonById(Long.parseLong(pokId));
        call.enqueue(new Callback<Pokemon>() {
          @Override
          public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
            if(response.isSuccessful()) {
              Pokemon pokemon = response.body();
              nome.setText(pokemon.getNome());
              tipo.setText(pokemon.getTipo());
              habilidade.setText(pokemon.getHabilidades());
              imagem.setImageResource(R.drawable.img);
            }
          }

          @Override
          public void onFailure(Call<Pokemon> call, Throwable t) {

          }
        });
      }
    }
  }

  public void save(View view){
    //atualizar a imagem tbmm - no método onActivityResult
    Intent i = new Intent(ListarDetalhes.this, ListarActivity.class);
    startActivity(i);
    finish();
  }

  public void delete(View view){
    //deletar da base
    Intent i = new Intent(ListarDetalhes.this, ListarActivity.class);
    startActivity(i);
    finish();
  }

  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    InputStream stream = null;
    if (requestCode == 1 && resultCode == RESULT_OK) {
      try {
        if (bitmap != null) {
          bitmap.recycle();
        }
        stream = getContentResolver().openInputStream(data.getData());
        bitmap = BitmapFactory.decodeStream(stream);
        imagem.setImageBitmap(bitmap);
        //salvar no banco aqui
      }
      catch(FileNotFoundException e) {
        e.printStackTrace();
      } finally {
        if (stream != null)
          try {
            stream.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
      }
    }
  }
}